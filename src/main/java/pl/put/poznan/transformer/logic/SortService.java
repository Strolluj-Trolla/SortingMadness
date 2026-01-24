package pl.put.poznan.transformer.logic;

import pl.put.poznan.transformer.rest.dto.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Logic handler for the REST service
 */
public class SortService {
    /**
     * The request to be handled by this service.
     */
    private final SortRequestDTO sortRequest;

    /**
     * Constructor for receiving a request.
     * @param request the request to be handled.
     */
    public SortService(SortRequestDTO request){
        this.sortRequest = request;
    }

    /**
     * Main logic of handling the request. Processes the input using {@link CellMapper},
     * runs desired tests using {@link Measurer}, then packs results to form a reply.
     * @return a {@link SortResponseDTO} containing results of performed tests.
     */
    public SortResponseDTO handleRequest(){
        SortRequestDTO request =this.sortRequest;
        Cell[][] data = CellMapper.toCells(request.getData());

        SortAlgorithm.Order order = SortAlgorithm.Order.RISING;
        if (request.getOrder() != null && request.getOrder().equalsIgnoreCase("FALLING")) {
            order = SortAlgorithm.Order.FALLING;
        }

        Scrambler.ScrambleType scrambleType=Scrambler.ScrambleType.NONE;
        if(request.getScramble()!=null){
            if(request.getScramble().equals("RANDOM"))scrambleType = Scrambler.ScrambleType.RANDOM;
            else if(request.getScramble().equals("REVERSE")){
                if(order==SortAlgorithm.Order.FALLING) scrambleType = Scrambler.ScrambleType.RISING;
                else scrambleType = Scrambler.ScrambleType.FALLING;
            }
        }

        Scrambler scrambler = new Scrambler();
        scrambler.scramble(data, request.getColumn(), scrambleType);

        Measurer measurer = new Measurer();

        int maxIter = request.getMaxIter() != null ? request.getMaxIter() : -1;

        List<Result> results = measurer.measure(
                data,
                request.getColumn(),
                request.getAlgorithms(),
                maxIter,
                order
        );

        List<SortResultDTO> dtoResults = new ArrayList<>();
        for (Result r : results) {
            SortResultDTO dto = new SortResultDTO();
            dto.setAlgorithm(r.getName());
            if (r.isError()) {
                dto.setError(r.getErrMessage());
            } else {
                dto.setTimeNs((long) r.getTime());
                dto.setComplete(r.isComplete());
                dto.setData(mapResultData(r.getData()));
            }
            dtoResults.add(dto);
        }

        return new SortResponseDTO(dtoResults);
    }

    /**
     * Helper function for converting 2-D arrays of type {@link Cell} used in logic to output format.
     * @param data a 2-D array of type {@link Cell}.
     * @return a {@link List} of {@link List} {@link Object} to be used in output.
     */
    private List<List<Object>> mapResultData(Cell[][] data) {
        List<List<Object>> out = new ArrayList<>();
        if (data == null) return out;

        for (Cell[] row : data) {
            List<Object> r = new ArrayList<>();
            for (Cell c : row) {
                r.add(c.getNum() != null ? c.getNum() : c.getStr());
            }
            out.add(r);
        }
        return out;
    }
}
