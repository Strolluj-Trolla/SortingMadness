package pl.put.poznan.transformer.rest;

import org.springframework.web.bind.annotation.*;
import pl.put.poznan.transformer.logic.Cell;
import pl.put.poznan.transformer.logic.Measurer;
import pl.put.poznan.transformer.logic.Result;
import pl.put.poznan.transformer.logic.SortAlgorithm.Order;
import pl.put.poznan.transformer.rest.dto.SortRequestDTO;
import pl.put.poznan.transformer.rest.dto.SortResponseDTO;
import pl.put.poznan.transformer.rest.dto.SortResultDTO;
import pl.put.poznan.transformer.logic.CellMapper;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/sort")
public class SortingController {

    @PostMapping(consumes = "application/json", produces = "application/json")
    public SortResponseDTO sort(@RequestBody SortRequestDTO request) {

        Cell[][] data = CellMapper.toCells(request.getData());
        Measurer measurer = new Measurer();

        Order order = Order.RISING;
        if (request.getOrder() != null && request.getOrder().equalsIgnoreCase("FALLING")) {
            order = Order.FALLING;
        }

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
                dto.setTimeNs(null);
                dto.setComplete(null);
                dto.setData(null);
            } else {
                dto.setTimeNs((long) r.getTime());
                dto.setComplete(r.isComplete());
                dto.setData(mapResultData(r.getData()));
                dto.setError(null);
            }
            dtoResults.add(dto);
        }

        return new SortResponseDTO(dtoResults);
    }

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
