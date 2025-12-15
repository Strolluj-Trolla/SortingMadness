package pl.put.poznan.transformer.logic;

/**
 * A class containing an implementation of insertion sort. Implements the {@link SortAlgorithm} interface.
 */
public class InsertSort implements SortAlgorithm{
    /**
     * An in-place implementation of the
     * <a href="https://en.wikipedia.org/wiki/Insertion_sort">insertion sort algorithm</a>
     *
     * @param tab a 2-D array of type {@link Cell} to be sorted.
     * @param column the index of the column which will be the sorting criteria.
     * @param maxIter maximum number of iterations. Value of {@code -1} means unlimited iterations,
     * {@code <-1} means none.
     * @param order an enum Order value determining the sorting direction.
     * @return a boolean value of whether sorting could be completed in the given number of iterations.
     */
    public boolean sort(Cell[][] tab, int column, int maxIter, Order order) {
        int n = tab.length - 1;
        int curIter=0;
        int sign=1;
        if(order==Order.FALLING)sign=-1;

        for (int j = n - 1; j >= 0; j--) {
            if((curIter>=maxIter)&&(maxIter!=-1))return false;
            curIter++;
            Cell[] helper = tab[j];
            int i = j + 1;
            while ((i <= n) && (sign*helper[column].compareTo(tab[i][column]))>0) {
                tab[i - 1] = tab[i];
                i++;
            }
            tab[i - 1] = helper;
        }
        return true;
    }
}
