package pl.put.poznan.transformer.logic;

import java.util.ArrayList;
import java.util.List;

public class Sorter {

    public static class Result{
        final private String name;
        final private double time;
        final private String errMessage;
        final private double[] numericData;
        final private String[] stringData;
        public Result(String name, double time, double[] data){
            this.name=name;
            this.time=time;
            this.numericData =data;
            this.stringData=null;
            this.errMessage=null;
        }
        public Result(String name, double time, String[] data){
            this.name=name;
            this.time=time;
            this.numericData=null;
            this.stringData=data;
            this.errMessage=null;
        }
        public Result(String name, String errMessage){
            this.name=name;
            this.time=-1;
            this.numericData =null;
            this.stringData=null;
            this.errMessage=errMessage;
        }
        public String getName(){
            return this.name;
        }
        public double getTime(){
            return this.time;
        }
        public double[] getNumericData(){
            return this.numericData;
        }
        public String[] getStringData(){
            return this.stringData;
        }
        public String getErrMessage(){
            return this.errMessage;
        }
    }

    //numeric data
    public List<Result> measure (double[] data, ArrayList<String > names){
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
                        double d =convData[i];
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

    public void bubbleSort(double[] sorted){}
    public void bubbleSort(String[] sorted){}

    public static void insertSort ( double[] tab){

    }

    public static void binaryInsertSort ( double[] tab){
        int n = tab.length - 1;

        for (int j = n - 1; j >= 0; j--) {
            double helper = tab[j];
            int p = j;
            int k = n + 1;
            while (k - p > 1) {
                int i = (p + k) / 2;
                if (helper <= tab[i]) k = i;
                else p = i;
            }
            for (int i = j; i < p; i++) {
                tab[i] = tab[i + 1];
            }
            tab[p] = helper;
        }
    }

    public static void selectionSort ( double[] tab){
        for (int k = tab.length - 1; k > 0; k--) {
            int max = k;
            for (int i = 0; i < k; i++) {
                if (tab[i] > tab[max]) max = i;
            }
            double helper = tab[max];
            tab[max] = tab[k];
            tab[k] = helper;
        }

    }

    private static void merge ( double[] tab, int start, int end){

        double[] helper = new double[end - start + 1];
        if (end - start >= 0) System.arraycopy(tab, start, helper, 0, end - start + 1);

        int middle = (start + end) / 2;
        int i = start;
        int i_left = start;
        int i_right = middle + 1;
        while (i_left <= middle && i_right <= end) {
            if (helper[i_left - start] < helper[i_right - start]) {
                tab[i] = helper[i_left - start];
                i_left++;
            } else {
                tab[i] = helper[i_right - start];
                i_right++;
            }
            i++;
        }
        while (i_left <= middle) {
            tab[i] = helper[i_left - start];
            i_left++;
            i++;
        }
        while (i_right <= end) {
            tab[i] = helper[i_right - start];
            i_right++;
            i++;
        }

    }

    private static void mergeSort ( double[] tab, int start, int end){

        int middle = (start + end) / 2;
        if (start < middle) mergeSort(tab, start, middle);
        if (middle + 1 < end) mergeSort(tab, middle + 1, end);
        merge(tab, start, end);

    }

    public static void mergeSort ( double[] tab){
        mergeSort(tab, 0, tab.length - 1);
    }

    private static void quickSort ( double[] tab, int start, int end){

        double pivot = tab[(start + end) / 2];
        int i = start;
        int j = end;
        double helper;

        do {
            while (tab[i] < pivot) i++;
            while (tab[j] > pivot) j--;
            if (i <= j) {
                helper = tab[i];
                tab[i] = tab[j];
                tab[j] = helper;
                i++;
                j--;
            }
        } while (i <= j);

        if (i < end) quickSort(tab, i, end);
        if (j > start) quickSort(tab, start, j);

    }

    public static void quickSort ( double[] tab){
        quickSort(tab, 0, tab.length - 1);
    }

    //Only for non-negative integer arrays
    public static void countingSort ( int[] tab){
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

    //text data
    public ArrayList<Result> measure (String[] data, ArrayList<String > names){
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

    public static void insertSort (String[] tab){

    }

    public static void binaryInsertSort (String[] tab){
        int n = tab.length - 1;

        for (int j = n - 1; j >= 0; j--) {
            String helper = tab[j];
            int p = j;
            int k = n + 1;
            while (k - p > 1) {
                int i = (p + k) / 2;
                if (helper.compareTo(tab[i])<=0) k = i;
                else p = i;
            }
            for (int i = j; i < p; i++) {
                tab[i] = tab[i + 1];
            }
            tab[p] = helper;
        }
    }

    public static void selectionSort (String[] tab){
        for (int k = tab.length - 1; k > 0; k--) {
            int max = k;
            for (int i = 0; i < k; i++) {
                if (tab[i].compareTo(tab[max])>0) max = i;
            }
            String helper = tab[max];
            tab[max] = tab[k];
            tab[k] = helper;
        }

    }

    private static void merge (String[] tab, int start, int end){

        String[] helper = new String[end - start + 1];
        if (end - start >= 0) System.arraycopy(tab, start, helper, 0, end - start + 1);

        int middle = (start + end) / 2;
        int i = start;
        int i_left = start;
        int i_right = middle + 1;
        while (i_left <= middle && i_right <= end) {
            if (helper[i_left - start].compareTo(helper[i_right - start])<0) {
                tab[i] = helper[i_left - start];
                i_left++;
            } else {
                tab[i] = helper[i_right - start];
                i_right++;
            }
            i++;
        }
        while (i_left <= middle) {
            tab[i] = helper[i_left - start];
            i_left++;
            i++;
        }
        while (i_right <= end) {
            tab[i] = helper[i_right - start];
            i_right++;
            i++;
        }

    }

    private static void mergeSort ( String[] tab, int start, int end){

        int middle = (start + end) / 2;
        if (start < middle) mergeSort(tab, start, middle);
        if (middle + 1 < end) mergeSort(tab, middle + 1, end);
        merge(tab, start, end);

    }

    public static void mergeSort (String[] tab){
        mergeSort(tab, 0, tab.length - 1);
    }

    private static void quickSort (String[] tab, int start, int end){

        String pivot = tab[(start + end) / 2];
        int i = start;
        int j = end;
        String helper;

        do {
            while (tab[i].compareTo(pivot)<0) i++;
            while (tab[j].compareTo(pivot)>0) j--;
            if (i <= j) {
                helper = tab[i];
                tab[i] = tab[j];
                tab[j] = helper;
                i++;
                j--;
            }
        } while (i <= j);

        if (i < end) quickSort(tab, i, end);
        if (j > start) quickSort(tab, start, j);

    }

    public static void quickSort (String[] tab){
        quickSort(tab, 0, tab.length - 1);
    }
}
