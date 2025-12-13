package pl.put.poznan.transformer.logic;

import static java.util.Objects.isNull;

public class BubbleSort implements SortAlgorithm {
    public void sort(Cell[][] tab, int column, int maxIter, Order order) {
        boolean changed = false;
        if(isNull(tab[0][column].str)) {
            for (int i = tab.length - 1; i > 0; i--) {
                for (int j = 0; j < i; j++) {
                    if (tab[j][column].num > tab[j + 1][column].num) {
                        Cell[] helper = tab[j + 1];
                        tab[j + 1] = tab[j];
                        tab[j] = helper;

                        changed = true;
                    }
                }
                if (!changed) return;
            }
        }
        else{
            for (int i = tab.length - 1; i > 0; i--) {
                for (int j = 0; j < i; j++) {
                    if (tab[j][column].str.compareTo(tab[j + 1][column].str)>0) {
                        Cell[] helper = tab[j + 1];
                        tab[j + 1] = tab[j];
                        tab[j] = helper;

                        changed = true;
                    }
                }
                if (!changed) return;
            }
        }
    }
}
