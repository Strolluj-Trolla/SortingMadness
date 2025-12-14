package pl.put.poznan.transformer.logic;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import pl.put.poznan.transformer.logic.SortAlgorithm.Order;
import static java.util.Objects.isNull;

public class Measurer {
    private final Sorter sorter;

    public Measurer() {
        this.sorter = new Sorter();
    }

    public List<Result> measure(Cell[][] data, int column, List<String> names) {
        return measure(data, column, names, -1, Order.RISING);
    }

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
                    Sorter.countingSort(convData, order);
                    for (int i = 0; i < data.length; i++) {
                        sorted[i][0] = new Cell(convData[i]);
                    }
                } else {
                    sorter.sort(sorted, column, maxIter, order);
                }

                Instant end = Instant.now();
                long timeNs = Duration.between(start, end).toNanos();
                results.add(new Result(name, timeNs, sorted));

            } catch (IllegalArgumentException ex) {
                results.add(new Result(name, ex.getMessage()));
            } catch (Exception ex) {
                results.add(new Result(name, "Error while sorting: " + ex.getMessage()));
            }
        }

        return results;
    }
}