package pl.put.poznan.transformer.rest.dto;

import java.util.List;

/**
 * Data Transfer Object containing the response from the application.
 */
public class SortResponseDTO {
    private List<SortResultDTO> results;

    /**
     * Constructor for making an empty {@link SortResponseDTO} object.
     */
    public SortResponseDTO() { }

    /**
     * Constructor for making a ready {@link SortResponseDTO} object ready to be sent to the client.
     * @param results a {@link List} of {@link SortResponseDTO} object containing results of performed tests.
     */
    public SortResponseDTO(List<SortResultDTO> results) {
        this.results = results;
    }

    public List<SortResultDTO> getResults() {
        return results;
    }

    public void setResults(List<SortResultDTO> results) {
        this.results = results;
    }
}