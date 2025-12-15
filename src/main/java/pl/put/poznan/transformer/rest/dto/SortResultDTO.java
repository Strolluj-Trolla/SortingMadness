package pl.put.poznan.transformer.rest.dto;

import java.util.List;

/**
 * Data Transfer Object for sorting results. Used to form a {@link SortResponseDTO} object.
 */
public class SortResultDTO {
    private String algorithm;
    private Long timeNs;
    private Boolean complete;
    private List<List<Object>> data;
    private String error;

    /**
     * Constructor for creating an empty {@link SortResponseDTO} to be filled out later.
     */
    public SortResultDTO() {
        this.algorithm=null;
        this.timeNs=null;
        this.complete=false;
        this.data=null;
        this.error=null;
    }

    /**
     * A full constructor for filling every member at once.
     *
     * @param algorithm a {@link String} containing the name of the algorithm used.
     * @param timeNs the time taken by the sorting run.
     * @param complete a boolean value of whether sorting was completed in the given number of iterations.
     * @param data a {@link List} of {@link List} of {@link Object} containing sorted data.
     * @param error a {@link String} containing error messages generated during sorting.
     */
    public SortResultDTO(String algorithm, Long timeNs, Boolean complete, List<List<Object>> data, String error) {
        this.algorithm = algorithm;
        this.timeNs = timeNs;
        this.complete = complete;
        this.data = data;
        this.error = error;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public Long getTimeNs() {
        return timeNs;
    }

    public void setTimeNs(Long timeNs) {
        this.timeNs = timeNs;
    }

    public List<List<Object>> getData() {
        return data;
    }

    public void setData(List<List<Object>> data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Boolean getComplete() {
        return complete;
    }

    public void setComplete(Boolean complete) {
        this.complete = complete;
    }
}