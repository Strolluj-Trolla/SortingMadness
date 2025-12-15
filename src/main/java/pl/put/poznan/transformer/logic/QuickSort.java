package pl.put.poznan.transformer.logic;

import static java.util.Objects.isNull;

public class QuickSort implements SortAlgorithm{
    public boolean sort(Cell[][] tab, int column, int maxIter, Order order) {
        return quickSort(tab, column, 0, tab.length-1, maxIter, order);
    }

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

        if(isNull(tab[0][column].str)) {
            do {
                while (sign*tab[i][column].num < sign*pivot[column].num) i++;
                while (sign*tab[j][column].num > sign*pivot[column].num) j--;
                if (i <= j) {
                    helper = tab[i];
                    tab[i] = tab[j];
                    tab[j] = helper;
                    i++;
                    j--;
                }
            } while (i <= j);
        }
        else{
            do {
                while (sign*tab[i][column].str.compareTo(pivot[column].str) < 0) i++;
                while (sign*tab[j][column].str.compareTo(pivot[column].str) > 0) j--;
                if (i <= j) {
                    helper = tab[i];
                    tab[i] = tab[j];
                    tab[j] = helper;
                    i++;
                    j--;
                }
            } while (i <= j);
        }

        boolean complete=true;
        if (i < end) complete=quickSort(tab, column, i, end, newMax, order);
        if (j > start) complete=(complete)&(quickSort(tab, column, start, j, newMax, order));
        return complete;
    }
}
