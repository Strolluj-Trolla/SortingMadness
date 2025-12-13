package pl.put.poznan.transformer.logic;

import static java.util.Objects.isNull;

public class SelectionSort implements SortAlgorithm {
    public void sort(Cell[][] tab, int column, int maxIter, Order order) {
        int curIter=0;
        int sign=1;
        if(order==Order.FALLING)sign=-1;

        if(isNull(tab[0][column].str)) {
            for (int k = tab.length - 1; k > 0; k--) {
                if((curIter>=maxIter)&&(maxIter!=-1))return;
                curIter++;
                int selected = k;
                for (int i = 0; i < k; i++) {
                    if (sign*tab[i][column].num > sign*tab[selected][column].num) selected = i;
                }
                Cell[] helper = tab[selected];
                tab[selected] = tab[k];
                tab[k] = helper;
            }
        }
        else{
            for (int k = tab.length - 1; k > 0; k--) {
                if((curIter>=maxIter)&&(maxIter!=-1))return;
                curIter++;
                int selected = k;
                for (int i = 0; i < k; i++) {
                    if (sign*tab[i][column].str.compareTo(tab[selected][column].str)>0) selected = i;
                }
                Cell[] helper = tab[selected];
                tab[selected] = tab[k];
                tab[k] = helper;
            }
        }
    }
}
