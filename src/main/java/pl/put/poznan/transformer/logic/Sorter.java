package pl.put.poznan.transformer.logic;

import pl.put.poznan.transformer.logic.SortAlgorithm.Order;

/**
 * Sorting handler used by higher-level logic.
 */
public class Sorter {
    /**
     * A {@link SortAlgorithm} object holding the currently chosen sorting algorithm.
     */
    private SortAlgorithm algo;

    /**
     * A light constructor which initializes {@link #algo} to a {@link QuickSort} object.
     */
    public Sorter(){
        this.algo=new QuickSort();
    }

    /**
     * Method for switching sorting algorithms.
     *
     * @param algorithm a {@link SortAlgorithm} object to assign to {@link #algo} and be used from now on.
     */
    public void setStrategy(SortAlgorithm algorithm){
        this.algo=algorithm;
    }

    /**
     * Wrapper to call {@link SortAlgorithm#sort(Cell[][], int, int, Order)} of the currently chosen
     * {@link SortAlgorithm}. Allows using a single {@link Sorter} object to perform sorting with differnt algorithms
     *
     * @param tab a 2-D array of type {@link Cell} to be sorted.
     * @param column the index of the column which will be the sorting criteria.
     * @param maxIter maximum number of iterations. Value of {@code -1} means unlimited iterations,
     * {@code <-1} means none.
     * @param order an enum Order value determining the sorting direction.
     * @return a boolean value of whether sorting could be completed in the given number of iterations.
     */
    public boolean sort(Cell[][] tab, int column, int maxIter, Order order){
        return algo.sort(tab, column, maxIter, order);
    }

    /**
     * A simple implementation of the <a href="https://en.wikipedia.org/wiki/Counting_sort">counting sort algorithm</a>
     * This implementation only works for 1-D arrays of integers.
     *
     * @param tab a 2-D array of type {@link Cell} to be sorted.
     * @param order an enum Order value determining the sorting direction.
     * @return a boolean value of whether sorting could be completed in the given number of iterations.
     * @throws IllegalArgumentException if given array contains negative values.
     */
    public static boolean countingSort(int[] tab, Order order) {
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
        if(order==Order.RISING){
            for (int j = 0; j < max + 1; j++) {
                for (int k = 0; k < help[j]; k++) {
                    tab[i] = j;
                    i++;
                }
            }
        }
        else{
            for (int j = max; j >= 0; j--) {
                for (int k = 0; k < help[j]; k++) {
                    tab[i] = j;
                    i++;
                }
            }
        }
        return true;
    }
}
