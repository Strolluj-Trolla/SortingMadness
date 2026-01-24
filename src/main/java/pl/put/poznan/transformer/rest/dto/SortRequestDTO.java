package pl.put.poznan.transformer.rest.dto;

import java.util.List;

/**
 * Data Transfer Object for a sorting request. Processed by {@link pl.put.poznan.transformer.logic.SortService}.
 */
public class SortRequestDTO {
    /**
     * Data to be sorted.
     */
    private List<List<Object>> data;
    /**
     * Index of the column to be used as sorting criteria.
     */
    private int column;
    /**
     * List of names of algorithms to be tested.
     */
    private List<String> algorithms;
    /**
     * Direction in which data will be sorted.
     */
    private String order;
    /**
     * Maximum number of iterations.
     */
    private Integer maxIter;
    /**
     * Which way to scramble given data
     */
    private String scramble;

    /**
     * Constructor for making an empty {@link SortRequestDTO} object to be filled later.
     */
    public SortRequestDTO() {
        this.data=null;
        this.column=0;
        this.order=null;
        this.maxIter=null;
        this.algorithms=null;
    }

    /**
     * Constructor for making a {@link SortRequestDTO} object ready to be processed.
     *
     * @param data a {@link List} of {@link List} of {@link Object} containing data to be sorted.
     * @param column index of the column to be used as sorting criteria.
     * @param algorithms a {@link List} of {@link String} names of algorithms to be tested.
     * @param order a {@link String} containing the direction in which data will be sorted.
     * @param maxIter maximum number of iterations.
     * @param scramble how to scramble the data.
     */
    public SortRequestDTO(List<List<Object>> data, int column, List<String> algorithms, String order, Integer maxIter, String scramble) {
        this.data = data;
        this.column = column;
        this.algorithms = algorithms;
        this.order = order;
        this.maxIter = maxIter;
        this.scramble = scramble;
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

    public String getScramble() {
        return scramble;
    }

    public void setScramble(String scramble) {
        this.scramble = scramble;
    }
}