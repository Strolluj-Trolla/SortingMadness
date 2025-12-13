package pl.put.poznan.transformer.logic;

import static java.util.Objects.isNull;

public class SelectionSort implements SortAlgorithm {
    public void sort(Cell[][] tab, int column, int maxIter, Order order) {
        if(isNull(tab[0][column].str)) {
            for (int k = tab.length - 1; k > 0; k--) {
                int max = k;
                for (int i = 0; i < k; i++) {
                    if (tab[i][column].num > tab[max][column].num) max = i;
                }
                Cell[] helper = tab[max];
                tab[max] = tab[k];
                tab[k] = helper;
            }
        }
        else{
            for (int k = tab.length - 1; k > 0; k--) {
                int max = k;
                for (int i = 0; i < k; i++) {
                    if (tab[i][column].str.compareTo(tab[max][column].str)>0) max = i;
                }
                Cell[] helper = tab[max];
                tab[max] = tab[k];
                tab[k] = helper;
            }
        }
    }
}
