package pl.put.poznan.transformer.logic;

public class SelectionSort implements SortAlgorithm {
    public boolean sort(Cell[][] tab, int column, int maxIter, Order order) {
        int curIter=0;
        int sign=1;
        if(order==Order.FALLING)sign=-1;

        for (int k = tab.length - 1; k > 0; k--) {
            if((curIter>=maxIter)&&(maxIter!=-1))return false;
            curIter++;
            int selected = k;
            for (int i = 0; i < k; i++) {
                if (sign*tab[i][column].compareTo(tab[selected][column])>0) selected = i;
            }
            Cell[] helper = tab[selected];
            tab[selected] = tab[k];
            tab[k] = helper;
        }
        return true;
    }
}
