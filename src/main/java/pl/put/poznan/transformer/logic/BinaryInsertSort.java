package pl.put.poznan.transformer.logic;

/**
 * A class containing an implementation of binary insertion sort. Implements the {@link SortAlgorithm} interface.
 */
public class BinaryInsertSort implements SortAlgorithm{
    /**
     * An in-place implementation of the
     * <a href="https://web.archive.org/web/20120224225904/http://www.pathcom.com/~vadco/binary.html">binary insertion sort algorithm</a>
     *
     * @param tab a 2-D array of type {@link Cell} to be sorted
     * @param column the number of the column which will be the sorting criteria.
     * @param maxIter maximum number of iterations. Value of {@code -1} means unlimited iterations,
     * {@code <-1} means none.
     * @param order an enum Order value determining the sorting direction
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
            int p = j;
            int k = n + 1;
            while (k - p > 1) {
                int i = (p + k) / 2;
                if (sign*helper[column].compareTo(tab[i][column])<=0) k = i;
                else p = i;
            }
            for (int i = j; i < p; i++) {
                tab[i] = tab[i + 1];
            }
            tab[p] = helper;
        }
        return true;
    }
}
