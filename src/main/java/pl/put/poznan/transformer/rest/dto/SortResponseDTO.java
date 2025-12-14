package pl.put.poznan.transformer.rest.dto;

import java.util.List;

public class SortResponseDTO {
    private List<SortResultDTO> results;

    public SortResponseDTO() { }

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