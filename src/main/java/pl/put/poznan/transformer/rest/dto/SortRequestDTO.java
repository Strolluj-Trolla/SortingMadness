package pl.put.poznan.transformer.rest.dto;

import java.util.List;

public class SortRequestDTO {
    private List<List<Object>> data;
    private int column;
    private List<String> algorithms;
    private String order;
    private Integer maxIter;

    public SortRequestDTO() { }

    public SortRequestDTO(List<List<Object>> data, int column, List<String> algorithms, String order, Integer maxIter) {
        this.data = data;
        this.column = column;
        this.algorithms = algorithms;
        this.order = order;
        this.maxIter = maxIter;
    }

    public List<List<Object>> getData() {
        return data;
    }

    public void setData(List<List<Object>> data) {
        this.data = data;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public List<String> getAlgorithms() {
        return algorithms;
    }

    public void setAlgorithms(List<String> algorithms) {
        this.algorithms = algorithms;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public Integer getMaxIter() {
        return maxIter;
    }

    public void setMaxIter(Integer maxIter) {
        this.maxIter = maxIter;
    }
}