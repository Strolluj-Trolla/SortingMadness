package pl.put.poznan.transformer.logic;

public class InsertSort implements SortAlgorithm{
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
