package pl.put.poznan.transformer.logic;

/**
 * A class containing an implementation of quicksort. Implements the {@link SortAlgorithm} interface.
 */
public class QuickSort implements SortAlgorithm{
    /**
     * A wrapper for {@link #quickSort(Cell[][], int, int, int, int, Order)} compatible the {@link SortAlgorithm} interface.
     *
     * @param tab a 2-D array of type {@link Cell} to be sorted.
     * @param column the index of the column which will be the sorting criteria.
     * @param maxIter maximum recursion depth. Value of {@code -1} means unlimited iterations,
     * {@code <-1} means none.
     * @param order an enum Order value determining the sorting direction.
     * @return a boolean value of whether sorting could be completed in the given number of iterations.
     */
    public boolean sort(Cell[][] tab, int column, int maxIter, Order order) {
        return quickSort(tab, column, 0, tab.length-1, maxIter, order);
    }

    /**
     * An in-place implementation of the
     * <a href="https://en.wikipedia.org/wiki/Quicksort">quicksort algorithm</a>
     *
     * @param tab a 2-D array of type {@link Cell} to be sorted.
     * @param column the index of the column which will be the sorting criteria.
     * @param start the index of the first value to be included in the sorting.
     * @param end the index of the last value to be included in the sorting.
     * @param maxDepth maximum recursion depth. Value of {@code -1} means unlimited iterations,
     * {@code <-1} means none.
     * @param order an enum Order value determining the sorting direction.
     * @return a boolean value of whether sorting could be completed in the given number of iterations.
     */
    public boolean quickSort(Cell[][] tab, int column, int start, int end, int maxDepth, Order order) {
        if(!((maxDepth>0)||(maxDepth==-1)))return false;
        int newMax = maxDepth;
        if (maxDepth != -1) newMax--;

        Cell[] pivot = tab[(start + end) / 2];
        int i = start;
        int j = end;
        Cell[] helper;
        int sign=1;
        if(order==Order.FALLING)sign=-1;

        do {
            while (sign*tab[i][column].compareTo(pivot[column]) < 0) i++;
            while (sign*tab[j][column].compareTo(pivot[column]) > 0) j--;
            if (i <= j) {
                helper = tab[i];
                tab[i] = tab[j];
                tab[j] = helper;
                i++;
                j--;
            }
        } while (i <= j);

        boolean complete=true;
        if (i < end) complete=quickSort(tab, column, i, end, newMax, order);
        if (j > start) complete=(complete)&(quickSort(tab, column, start, j, newMax, order));
        return complete;
    }
}
