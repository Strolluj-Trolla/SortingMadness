package pl.put.poznan.transformer.logic;

/**
 * Interface defining a sorting algorithm.
 * Concrete implementations of this interface are used to sort data in a number of ways.
 */
public interface SortAlgorithm {
    /**
     * An enumeration type for holding information about sorting direction.
     */
    enum Order{RISING, FALLING}

    /**
     * Method stub for the main sorting methods of {@link SortAlgorithm} implementations.
     * Sorting is performed in-place using the given array of data.
     *
     * @param tab a 2-D array of type {@link Cell} to be sorted
     * @param column the number of the column which will be the sorting criteria.
     * @param maxIter maximum number of iterations. Value of {@code -1} means unlimited iterations,
     * {@code <-1} means none.
     * @param order an enum Order value determining the sorting direction
     * @return a boolean value of whether sorting could be completed in the given number of iterations.
     */
    boolean sort(Cell[][] tab, int column, int maxIter, Order order);
}
