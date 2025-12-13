package pl.put.poznan.transformer.logic;

import static java.util.Objects.isNull;

public class InsertSort implements SortAlgorithm{
    public void sort(Cell[][] tab, int column) {
        int n = tab.length - 1;

        if(isNull(tab[0][column].str)) {
            for (int j = n - 1; j >= 0; j--) {
                Cell[] helper = tab[j];
                int i = j + 1;
                while ((i <= n) && (helper[column].num > tab[i][column].num)) {
                    tab[i - 1] = tab[i];
                    i++;
                }
                tab[i - 1] = helper;
            }
        }
        else{
            for (int j = n - 1; j >= 0; j--) {
                Cell[] helper = tab[j];
                int i = j + 1;
                while ((i <= n) && (helper[column].str.compareTo(tab[i][column].str))>0) {
                    tab[i - 1] = tab[i];
                    i++;
                }
                tab[i - 1] = helper;
            }
        }
    }
}
