package pl.put.poznan.transformer.rest.dto;

import java.util.List;

public class SortResultDTO {
    private String algorithm;
    private Long timeNs;
    private Boolean complete;
    private List<List<Object>> data;
    private String error;

    public SortResultDTO() { }

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