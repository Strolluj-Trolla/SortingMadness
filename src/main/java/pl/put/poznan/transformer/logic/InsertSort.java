package pl.put.poznan.transformer.logic;

import static java.util.Objects.isNull;

public class InsertSort implements SortAlgorithm{
    public void sort(Cell[][] tab, int column, int maxIter, Order order) {
        int n = tab.length - 1;
        int curIter=0;
        int sign=1;
        if(order==Order.FALLING)sign=-1;

        if(isNull(tab[0][column].str)) {
            for (int j = n - 1; j >= 0; j--) {
                if((curIter>=maxIter)&&(maxIter!=-1))return;
                curIter++;
                Cell[] helper = tab[j];
                int i = j + 1;
                while ((i <= n) && (sign*helper[column].num > sign*tab[i][column].num)) {
                    tab[i - 1] = tab[i];
                    i++;
                }
                tab[i - 1] = helper;
            }
        }
        else{
            for (int j = n - 1; j >= 0; j--) {
                if((curIter>=maxIter)&&(maxIter!=-1))return;
                curIter++;
                Cell[] helper = tab[j];
                int i = j + 1;
                while ((i <= n) && (sign*helper[column].str.compareTo(tab[i][column].str))>0) {
                    tab[i - 1] = tab[i];
                    i++;
                }
                tab[i - 1] = helper;
            }
        }
    }
}
