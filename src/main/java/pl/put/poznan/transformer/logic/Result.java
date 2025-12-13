package pl.put.poznan.transformer.logic;

import java.util.List;

import static java.util.Objects.isNull;

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

    public boolean isError() {
        return this.errMessage != null;
    }

    public String getErrMessage() {
        return this.errMessage;
    }

    public static void printResults(List<Result> results) {
        for(Result res:results) {
            System.out.println("Results of algorithm "+res.getName()+":");
            if(res.isError())System.out.println(res.getErrMessage());
            else{
                System.out.println("Time: "+res.getTime()+"ns");
                System.out.println("Sorted data:");
                Cell[][] sorted=res.getData();
                for (Cell[] cells : sorted) {
                    for (Cell cell : cells) {
                        if(isNull(cell.str))System.out.print(cell.num + ", ");
                        else System.out.print(cell.str + ", ");
                    }
                    System.out.println(";");
                }
            }
        }
    }
}
