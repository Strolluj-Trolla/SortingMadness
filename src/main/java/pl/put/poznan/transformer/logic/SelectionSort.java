package pl.put.poznan.transformer.logic;

/**
 * A class containing an implementation of selection sort. Implements the {@link SortAlgorithm} interface.
 */
public class SelectionSort implements SortAlgorithm {
    /**
     * An in-place implementation of the
     * <a href="https://en.wikipedia.org/wiki/Selection_sort">selection sort algorithm</a>
     *
     * @param tab a 2-D array of type {@link Cell} to be sorted.
     * @param column the number of the column which will be the sorting criteria.
     * @param maxIter maximum number of iterations. Value of {@code -1} means unlimited iterations,
     * {@code <-1} means none.
     * @param order an enum Order value determining the sorting direction.
     * @return a boolean value of whether sorting could be completed in the given number of iterations.
     */
    public boolean sort(Cell[][] tab, int column, int maxIter, Order order) {
        int curIter=0;
        int sign=1;
        if(order==Order.FALLING)sign=-1;

        for (int k = tab.length - 1; k > 0; k--) {
            if((curIter>=maxIter)&&(maxIter!=-1))return false;
            curIter++;
            int selected = k;
            for (int i = 0; i < k; i++) {
                if (sign*tab[i][column].compareTo(tab[selected][column])>0) selected = i;
            }
            Cell[] helper = tab[selected];
            tab[selected] = tab[k];
            tab[k] = helper;
        }
        return true;
    }
}
