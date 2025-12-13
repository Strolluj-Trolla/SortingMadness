package pl.put.poznan.transformer.logic;

import static java.util.Objects.isNull;

public class MergeSort implements SortAlgorithm{
    public void sort(Cell[][] tab, int column) {
        mergeSort(tab, column, 0, tab.length);
    }

    private static void merge(Cell[][] tab, int column, int start, int end){
        Cell[][] helper = new Cell[end - start + 1][];
        if (end - start >= 0) System.arraycopy(tab, start, helper, 0, end - start + 1);

        int middle = (start + end) / 2;
        int i = start;
        int i_left = start;
        int i_right = middle + 1;
        if(isNull(tab[0][column].str)) {
            while (i_left <= middle && i_right <= end) {
                if (helper[i_left - start][column].num < helper[i_right - start][column].num) {
                    tab[i] = helper[i_left - start];
                    i_left++;
                } else {
                    tab[i] = helper[i_right - start];
                    i_right++;
                }
                i++;
            }
        }
        else{
            while (i_left <= middle && i_right <= end) {
                if (helper[i_left - start][column].str.compareTo(helper[i_right - start][column].str)<0) {
                    tab[i] = helper[i_left - start];
                    i_left++;
                } else {
                    tab[i] = helper[i_right - start];
                    i_right++;
                }
                i++;
            }
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

    private static void mergeSort(Cell[][] tab, int column, int start, int end){

        int middle = (start + end) / 2;
        if (start < middle) mergeSort(tab, column, start, middle);
        if (middle + 1 < end) mergeSort(tab, column, middle + 1, end);
        merge(tab, column, start, end);

    }
}
