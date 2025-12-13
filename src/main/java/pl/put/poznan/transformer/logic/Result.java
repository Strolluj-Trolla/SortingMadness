package pl.put.poznan.transformer.logic;

public class Result {
    final private String name;
    final private double time;
    final private String errMessage;
    final private Cell[][] data;

    public Result(String name, double time, Cell[][] data) {
        this.name = name;
        this.time = time;
        this.data = data;
        this.errMessage = null;
    }

    public Result(String name, String errMessage) {
        this.name = name;
        this.time = -1;
        this.data = null;
        this.errMessage = errMessage;
    }

    public String getName() {
        return this.name;
    }

    public double getTime() {
        return this.time;
    }

    public Cell[][] getData() {
        return this.data;
    }

    public String getErrMessage() {
        return this.errMessage;
    }
}
