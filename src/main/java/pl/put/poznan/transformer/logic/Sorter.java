package pl.put.poznan.transformer.logic;

import java.util.ArrayList;
import java.util.List;

public class Sorter {

    public static class Result {
        final private String name;
        final private double time;
        final private String errMessage;
        final private double[] numericData;
        final private String[] stringData;

        public Result(String name, double time, double[] data) {
            this.name = name;
            this.time = time;
            this.numericData = data;
            this.stringData = null;
            this.errMessage = null;
        }

        public Result(String name, double time, String[] data) {
            this.name = name;
            this.time = time;
            this.numericData = null;
            this.stringData = data;
            this.errMessage = null;
        }

        public Result(String name, String errMessage) {
            this.name = name;
            this.time = -1;
            this.numericData = null;
            this.stringData = null;
            this.errMessage = errMessage;
        }

        public String getName() {
            return this.name;
        }

        public double getTime() {
            return this.time;
        }

        public double[] getNumericData() {
            return this.numericData;
        }

        public String[] getStringData() {
            return this.stringData;
        }

        public String getErrMessage() {
            return this.errMessage;
        }
    }

    //numeric data
    public List<Result> measure(double[] data, ArrayList<String> names) {
        ArrayList<Result> results = new ArrayList<>();
        int[] convData;
        convData = new int[data.length];
        boolean possible = true;
        for (int i = 0; i < data.length; i++) {
            int integ = (int) data[i];
            if (integ != data[i]) {
                possible = false;
                break;
            }
            convData[i] = integ;
        }
        for (String name : names) {
            try {
                double[] sorted = new double[data.length];
                System.arraycopy(data, 0, sorted, 0, data.length);
                long time = System.currentTimeMillis();
                switch (name) {
                    case "bubble":
                        bubbleSort(sorted);
                    case "insertion":
                        insertSort(sorted);
                    case "binaryInsertion":
                        binaryInsertSort(sorted);
                    case "selection":
                        selectionSort(sorted);
                    case "merge":
                        mergeSort(sorted);
                    case "quick":
                        quickSort(sorted);
                    case "counting":
                        if (!possible) {
                            throw new IllegalArgumentException("Array contains non-integer values");
                        }
                        countingSort(convData);
                }
                time = System.currentTimeMillis() - time;
                if (name.equals("counting")) {
                    for (int i = 0; i < data.length; i++) {
                        double d = convData[i];
                        sorted[i] = d;
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

    public void bubbleSort(double[] sorted) {
    }

    public void bubbleSort(String[] sorted) {
    }

    public static void insertSort(double[] tab) {
    }

    public static void insertSort(String[] tab) {
    }

    public static void binaryInsertSort(String[] tab) {
    }

    public static void binaryInsertSort(double[] tab) {
    }

    public static void selectionSort(double[] tab) {
    }

    public static void selectionSort(String[] tab) {
    }

    public static void mergeSort(double[] tab) {
    }

    public static void mergeSort(String[] tab) {
    }
    public static void quickSort(double[] tab) {}
    public static void quickSort (String[] tab){}

    //only for 1-dim int arrays
    public static void countingSort(int[] tab) {
        int max = tab[0];
        int min = tab[0];
        for (int item : tab) {
            if (item > max) max = item;
            if (item < min) min = item;
        }
        if (min < 0) throw new IllegalArgumentException("Array contains negative values");

        int[] help = new int[max + 1];
        for (int value : tab) help[value]++;

        int i = 0;
        for (int j = 0; j < max + 1; j++) {
            for (int k = 0; k < help[j]; k++) {
                tab[i] = j;
                i++;
            }
        }

    }

    public ArrayList<Result> measure(String[] data, ArrayList<String> names) {
        ArrayList<Result> results = new ArrayList<>();
        for (String name : names) {
            try {
                String[] sorted = new String[data.length];
                System.arraycopy(data, 0, sorted, 0, data.length);
                long time = System.currentTimeMillis();
                switch (name) {
                    case "bubble":
                        bubbleSort(sorted);
                    case "insertion":
                        insertSort(sorted);
                    case "binaryInsertion":
                        binaryInsertSort(sorted);
                    case "selection":
                        selectionSort(sorted);
                    case "merge":
                        mergeSort(sorted);
                    case "quick":
                        quickSort(sorted);
                }
                time = System.currentTimeMillis() - time;
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
