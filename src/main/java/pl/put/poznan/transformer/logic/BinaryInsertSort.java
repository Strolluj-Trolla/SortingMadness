package pl.put.poznan.transformer.logic;

public class BinaryInsertSort implements SortAlgorithm{
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
