package pl.put.poznan.transformer.logic;

/**
 * A class containing an implementation of merge sort. Implements the {@link SortAlgorithm} interface.
 */
public class MergeSort implements SortAlgorithm{
    /**
     * A wrapper for {@link #mergeSort(Cell[][], int, int, int, int, Order)} compatible the {@link SortAlgorithm} interface.
     *
     * @param tab a 2-D array of type {@link Cell} to be sorted
     * @param column the number of the column which will be the sorting criteria.
     * @param maxIter maximum number of iterations. Value of {@code -1} means unlimited iterations,
     * {@code <-1} means none.
     * @param order an enum Order value determining the sorting direction
     * @return a boolean value of whether sorting could be completed in the given number of iterations.
     */
    public boolean sort(Cell[][] tab, int column, int maxIter, Order order) {
        return mergeSort(tab, column, 0, tab.length-1, maxIter, order);
    }

    /**
     * Performs a merge of two sorted parts of an array. Uses a helper array and as such is not performed in-place.
     * @param tab a 2-D array of type {@link Cell} to be sorted
     * @param column the number of the column which will be the sorting criteria.
     * @param start the index of the first value to be included in the sorting.
     * @param end the index of the last value to be included in the sorting.
     * @param order order an enum Order value determining the sorting direction
     */
    private static void merge(Cell[][] tab, int column, int start, int end, Order order){
        Cell[][] helper = new Cell[end - start + 1][];
        if (end - start >= 0) System.arraycopy(tab, start, helper, 0, end - start + 1);

        int sign=1;
        if(order==Order.FALLING)sign=-1;
        int middle = (start + end) / 2;
        int i = start;
        int i_left = start;
        int i_right = middle + 1;
        while (i_left <= middle && i_right <= end) {
            if (sign*helper[i_left - start][column].compareTo(helper[i_right - start][column])<0) {
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

    /**
     * An (almost) in-place implementation of the
     * <a href="https://en.wikipedia.org/wiki/Merge_sort">merge sort algorithm</a>
     *
     * @param tab a 2-D array of type {@link Cell} to be sorted
     * @param column the number of the column which will be the sorting criteria.
     * @param start the index of the first value to be included in the sorting.
     * @param end the index of the last value to be included in the sorting.
     * @param maxDepth maximum number of iterations. Value of {@code -1} means unlimited iterations,
     * {@code <-1} means none.
     * @param order an enum Order value determining the sorting direction
     * @return a boolean value of whether sorting could be completed in the given number of iterations.
     */
    private static boolean mergeSort(Cell[][] tab, int column, int start, int end, int maxDepth, Order order){
        boolean complete=true;
        if((maxDepth>0)||(maxDepth==-1)){
            int newMax=maxDepth;
            if(maxDepth!=-1)newMax--;
            int middle = (start + end) / 2;
            if (start < middle) complete=mergeSort(tab, column, start, middle, newMax, order);
            if (middle + 1 < end) complete=(complete)&(mergeSort(tab, column, middle + 1, end, newMax, order));
        }
        else complete=false;
        merge(tab, column, start, end, order);
        return complete;
    }
}
