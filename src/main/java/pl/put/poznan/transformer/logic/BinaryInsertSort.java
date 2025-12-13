package pl.put.poznan.transformer.logic;

import static java.util.Objects.isNull;

public class BinaryInsertSort implements SortAlgorithm{
    public void sort(Cell[][] tab, int column, int maxIter, Order order) {
        int n = tab.length - 1;

        if(isNull(tab[0][column].str)) {
            for (int j = n - 1; j >= 0; j--) {
                Cell[] helper = tab[j];
                int p = j;
                int k = n + 1;
                while (k - p > 1) {
                    int i = (p + k) / 2;
                    if (helper[column].num <= tab[i][column].num) k = i;
                    else p = i;
                }
                for (int i = j; i < p; i++) {
                    tab[i] = tab[i + 1];
                }
                tab[p] = helper;
            }
        }
        else{
            for (int j = n - 1; j >= 0; j--) {
                Cell[] helper = tab[j];
                int p = j;
                int k = n + 1;
                while (k - p > 1) {
                    int i = (p + k) / 2;
                    if (helper[column].str.compareTo(tab[i][column].str)<=0) k = i;
                    else p = i;
                }
                for (int i = j; i < p; i++) {
                    tab[i] = tab[i + 1];
                }
                tab[p] = helper;
            }
        }
    }
}
