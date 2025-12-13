package pl.put.poznan.transformer.logic;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

public class Measurer {
    private final Sorter sorter;

    public Measurer(){
        this.sorter = new Sorter();
    }

    public List<Result> measure(Cell[][] data, int column, List<String> names, int maxIter, SortAlgorithm.Order order) {
        ArrayList<Result> results = new ArrayList<>();
        boolean possible = true;
        int[] convData={};
        if((data[0].length==1)&&(!isNull(data[0][0].num))){
            convData = new int[data.length];
            for (int i = 0; i < data.length; i++) {
                int integ = data[i][0].num.intValue();
                if (integ != data[i][0].num||integ<0) {
                    possible = false;
                    break;
                }
                convData[i] = integ;
            }
        }
        else possible = false;

        for (String name : names) {
            try {
                Cell[][] sorted = new Cell[data.length][];
                for (int i = 0; i < data.length; i++) {
                    sorted[i]=new Cell[data[i].length];
                    System.arraycopy(data[i], 0, sorted[i], 0, data[i].length);
                }
                switch (name) {
                    case "bubble":
                        this.sorter.setStrategy(new BubbleSort());
                    case "insertion":
                        this.sorter.setStrategy(new InsertSort());
                    case "binaryInsertion":
                        this.sorter.setStrategy(new BinaryInsertSort());
                    case "selection":
                        this.sorter.setStrategy(new SelectionSort());
                    case "merge":
                        this.sorter.setStrategy(new MergeSort());
                    case "quick":
                        this.sorter.setStrategy(new QuickSort());
                }
                Instant start = Instant.now();
                if(name.equals("counting")){
                    if (!possible) {
                        throw new IllegalArgumentException("Counting sort not applicable to given data");
                    }
                    Sorter.countingSort(convData);
                }
                else sorter.sort(sorted, column, maxIter, order);
                Instant end = Instant.now();
                long time= Duration.between(start, end).toNanos();
                if ((name.equals("counting"))&&(possible)) {
                    for (int i = 0; i < data.length; i++) {
                        sorted[i][0] = new Cell(convData[i]);
                    }
                }
                results.add(new Result(name, time, sorted));
            } catch (IllegalArgumentException ex) {
                results.add(new Result(name, ex.getMessage()));
            } catch (Exception ex) {
                results.add(new Result(name, "Error while sorting: " + ex.getMessage()));
            }
        }
        return results;
    }

}
