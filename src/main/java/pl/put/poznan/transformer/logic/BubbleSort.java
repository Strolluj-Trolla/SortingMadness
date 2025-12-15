package pl.put.poznan.transformer.logic;

/**
 * A class containing an implementation of bubble sort. Implements the {@link SortAlgorithm} interface.
 */
public class BubbleSort implements SortAlgorithm {
    /**
     * An in-place implementation of the
     * <a href="https://en.wikipedia.org/wiki/Bubble_sort">bubble sort algorithm</a>
     *
     * @param tab a 2-D array of type {@link Cell} to be sorted.
     * @param column the number of the column which will be the sorting criteria.
     * @param maxIter maximum number of iterations. Value of {@code -1} means unlimited iterations,
     * {@code <-1} means none.
     * @param order an enum Order value determining the sorting direction.
     * @return a boolean value of whether sorting could be completed in the given number of iterations.
     */
    public boolean sort(Cell[][] tab, int column, int maxIter, Order order) {
        boolean changed = false;
        int curIter=0;
        int sign=1;
        if(order==Order.FALLING)sign=-1;

        for (int i = tab.length - 1; i > 0; i--) {
            if((curIter>=maxIter)&&(maxIter!=-1))return false;
            curIter++;
            for (int j = 0; j < i; j++) {
                if (sign*tab[j][column].compareTo(tab[j + 1][column])>0) {
                    Cell[] helper = tab[j + 1];
                    tab[j + 1] = tab[j];
                    tab[j] = helper;

                    changed = true;
                }
            }
            if (!changed) return true;
        }
        return true;
    }
}
