package pl.put.poznan.transformer.logic;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import pl.put.poznan.transformer.logic.SortAlgorithm.Order;
import static java.util.Objects.isNull;

/**
 * Measuring class for managing and obtaining results of sorting runs.
 */
public class Measurer {
    /**
     * A {@link Sorter} object to be used as a sorting engine.
     */
    private final Sorter sorter;

    /**
     * A light constructor which initializes {@link #sorter}.
     */
    public Measurer() {
        this.sorter = new Sorter();
    }

    /**
     * A shorthand for the full definition of {@link #measure(Cell[][], int, List, int, Order)}.
     * Used for testing and debugging.
     * @deprecated
     *
     * @param data a 2-D array of type {@link Cell} to be sorted.
     * @param column the index of the column which will be the sorting criteria.
     * @param names a {@link List} of type {@link String} containing names of algorithms to be run and measured.
     * @return a {@link List} of type {@link Result} holding information about the sorting runs.
     */
    public List<Result> measure(Cell[][] data, int column, List<String> names) {
        return measure(data, column, names, -1, Order.RISING);
    }

    /**
     * Method for running tests for different sorting algorithms. Runs a test for each {@code name} supplied.
     * Possible names are {@code "bubble"}, {@code "insertion"}, {@code "binaryInsertion"}, {@code "selection"},
     * {@code "merge"}, {@code "quick"}, {@code "counting"}.
     *
     * @param data a 2-D array of type {@link Cell} to be sorted.
     * @param column the index of the column which will be the sorting criteria.
     * @param names a {@link List} of type {@link String} containing names of algorithms to be run and measured.
     * @param maxIter maximum number of iterations. Value of {@code -1} means unlimited iterations,
     * {@code <-1} means none.
     * @param order an enum Order (from {@link SortAlgorithm}) value determining the sorting direction.
     * @return a {@link List} of type {@link Result} holding information about the sorting runs.
     */
    public List<Result> measure(Cell[][] data, int column, List<String> names, int maxIter, Order order) {
        List<Result> results = new ArrayList<>();

        if (data == null || data.length == 0) {
            results.add(new Result("all", "Input data is empty"));
            return results;
        }

        boolean countingApplicable = (data[0].length == 1 && !isNull(data[0][0].num));
        int[] convData = new int[data.length];
        if (countingApplicable) {
            for (int i = 0; i < data.length; i++) {
                double val = data[i][0].num;
                long rounded = Math.round(val);
                if (val != rounded || rounded < 0) {
                    countingApplicable = false;
                    break;
                }
                convData[i] = (int) rounded;
            }
        }

        for (String name : names) {
            try {
                Cell[][] sorted = new Cell[data.length][];
                for (int i = 0; i < data.length; i++) {
                    sorted[i] = new Cell[data[i].length];
                    System.arraycopy(data[i], 0, sorted[i], 0, data[i].length);
                }

                boolean complete;
                boolean algorithmRecognized = true;
                switch (name) {
                    case "bubble": sorter.setStrategy(new BubbleSort());
                    break;
                    case "insertion": sorter.setStrategy(new InsertSort());
                    break;
                    case "binaryInsertion": sorter.setStrategy(new BinaryInsertSort());
                    break;
                    case "selection": sorter.setStrategy(new SelectionSort());
                    break;
                    case "merge": sorter.setStrategy(new MergeSort());
                    break;
                    case "quick": sorter.setStrategy(new QuickSort());
                    break;
                    case "counting":
                    break;
                    default:
                        algorithmRecognized = false;
                        results.add(new Result(name, "Algorithm not recognized"));
                        break;
                }

                if (!algorithmRecognized) continue;

                Instant start = Instant.now();

                if ("counting".equals(name)) {
                    if (!countingApplicable) {
                        throw new IllegalArgumentException("Counting sort not applicable to given data");
                    }
                    complete=Sorter.countingSort(convData, order);
                    for (int i = 0; i < data.length; i++) {
                        sorted[i][0] = new Cell(convData[i]);
                    }
                } else {
                    complete=sorter.sort(sorted, column, maxIter, order);
                }

                Instant end = Instant.now();
                long timeNs = Duration.between(start, end).toNanos();
                results.add(new Result(name, timeNs, complete, sorted));

            } catch (IllegalArgumentException ex) {
                results.add(new Result(name, ex.getMessage()));
            } catch (Exception ex) {
                results.add(new Result(name, "Error while sorting: " + ex.getMessage()));
            }
        }

        return results;
    }
}