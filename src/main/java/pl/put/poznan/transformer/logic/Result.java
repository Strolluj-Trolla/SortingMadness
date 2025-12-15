package pl.put.poznan.transformer.logic;

import org.springframework.lang.NonNull;

import java.util.List;

import static java.util.Objects.isNull;

/** A simple class for holding results of a sorting run. Used mainly by the {@link Measurer} class.
 *
 */
public class Result {
    final private String name;
    final private double time;
    final private boolean complete;
    final private String errMessage;
    final private Cell[][] data;

    /**
     * A constructor for storing the {@link Result} of a successful sorting run.
     *
     * @param name a {@link String} with the name of the algorithm used.
     * @param time a double. Length of time taken to perform the sorting.
     * @param complete a boolean value of whether sorting could be completed in the given number of iterations.
     * @param data a 2-D array of type {@link Cell} storing sorted data.
     */
    public Result(String name, double time, boolean complete, Cell[][] data) {
        this.name = name;
        this.time = time;
        this.complete = complete;
        this.data = data;
        this.errMessage = null;
    }

    /**
     * A constructor for storing the {@link Result} of a failed sorting run.
     *
     * @param name a {@link String} with the name of the algorithm used.
     * @param errMessage a {@link String} with the error message generated while sorting.
     */
    public Result(String name, String errMessage) {
        this.name = name;
        this.time = -1;
        this.complete = false;
        this.data = null;
        this.errMessage = errMessage;
    }

    /**
     * Getter for the {@link #name} field
     *
     * @return the value of {@link #name}
     */
    public String getName() {
        return this.name;
    }

    /**
     * Getter for the {@link #time} field
     *
     * @return the value of {@link #time}
     */
    public double getTime() {
        return this.time;
    }

    /**
     * Getter for the {@link #data} field
     *
     * @return the value of {@link #data}
     */
    public Cell[][] getData() {
        return this.data;
    }

    /**
     * A method to determine whether an error occurred.
     *
     * @return {@code true} if there was an error, otherwise {@code false}
     */
    public boolean isError() {
        return this.errMessage != null;
    }

    /**
     * Getter for the {@link #complete} field
     *
     * @return the value of {@link #complete}
     */
    public boolean isComplete() {
        return complete;
    }

    /**
     * Getter for the {@link #errMessage} field
     *
     * @return the value of {@link #errMessage}
     */
    public String getErrMessage() {
        return this.errMessage;
    }

    /**
     * A method to print out results of sorting runs to consoles.
     * For debug and diagnostic use only
     * @deprecated
     * @param results a {@link List} of results to be printed out.
     */
    public static void printResults(@NonNull List<Result> results) {
        for(Result res:results) {
            System.out.println("Results of algorithm "+res.getName()+":");
            if(res.isError())System.out.println(res.getErrMessage());
            else{
                System.out.println("Time: "+res.getTime()+"ns");
                System.out.println("Sorting complete? "+res.isComplete());
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
