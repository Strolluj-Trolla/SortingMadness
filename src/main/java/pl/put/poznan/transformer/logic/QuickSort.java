package pl.put.poznan.transformer.logic;

import static java.util.Objects.isNull;

public class QuickSort implements SortAlgorithm{
    public void sort(Cell[][] tab, int column, int maxIter, Order order) {
        quickSort(tab, column, 0, tab.length-1);
    }

    public void quickSort(Cell[][] tab, int column, int start, int end) {
        Cell[] pivot = tab[(start + end) / 2];
        int i = start;
        int j = end;
        Cell[] helper;

        if(isNull(tab[0][column].str)) {
            do {
                while (tab[i][column].num < pivot[column].num) i++;
                while (tab[j][column].num > pivot[column].num) j--;
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
                while (tab[i][column].str.compareTo(pivot[column].str) < 0) i++;
                while (tab[j][column].str.compareTo(pivot[column].str) > 0) j--;
                if (i <= j) {
                    helper = tab[i];
                    tab[i] = tab[j];
                    tab[j] = helper;
                    i++;
                    j--;
                }
            } while (i <= j);
        }

        if (i < end) quickSort(tab, column, i, end);
        if (j > start) quickSort(tab, column, start, j);
    }
}
