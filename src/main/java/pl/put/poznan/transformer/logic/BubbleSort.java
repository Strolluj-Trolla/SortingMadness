package pl.put.poznan.transformer.logic;

public class BubbleSort implements SortAlgorithm {
    public boolean sort(Cell[][] tab, int column, int maxIter, Order order) {
        boolean changed = false;
        int curIter=0;
        int sign=1;
        if(order==Order.FALLING)sign=-1;

        for (int i = tab.length - 1; i > 0; i--) {
            if((curIter>=maxIter)&&(maxIter!=-1))return false;
            curIter++;
            for (int j = 0; j < i; j++) {
                if (sign*tab[j][column].compareTo(tab[j + 1][column])>0) {
                    Cell[] helper = tab[j + 1];
                    tab[j + 1] = tab[j];
                    tab[j] = helper;

                    changed = true;
                }
            }
            if (!changed) return true;
        }
        return true;
    }
}
