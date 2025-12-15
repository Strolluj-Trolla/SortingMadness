package pl.put.poznan.transformer.logic;

public class MergeSort implements SortAlgorithm{
    public boolean sort(Cell[][] tab, int column, int maxIter, Order order) {
        return mergeSort(tab, column, 0, tab.length-1, maxIter, order);
    }

    private static void merge(Cell[][] tab, int column, int start, int end, Order order){
        Cell[][] helper = new Cell[end - start + 1][];
        if (end - start >= 0) System.arraycopy(tab, start, helper, 0, end - start + 1);

        int sign=1;
        if(order==Order.FALLING)sign=-1;
        int middle = (start + end) / 2;
        int i = start;
        int i_left = start;
        int i_right = middle + 1;
        while (i_left <= middle && i_right <= end) {
            if (sign*helper[i_left - start][column].compareTo(helper[i_right - start][column])<0) {
                tab[i] = helper[i_left - start];
                i_left++;
            } else {
                tab[i] = helper[i_right - start];
                i_right++;
            }
            i++;
        }
        while (i_left <= middle) {
            tab[i] = helper[i_left - start];
            i_left++;
            i++;
        }
        while (i_right <= end) {
            tab[i] = helper[i_right - start];
            i_right++;
            i++;
        }

    }

    private static boolean mergeSort(Cell[][] tab, int column, int start, int end, int maxDepth, Order order){
        boolean complete=true;
        if((maxDepth>0)||(maxDepth==-1)){
            int newMax=maxDepth;
            if(maxDepth!=-1)newMax--;
            int middle = (start + end) / 2;
            if (start < middle) complete=mergeSort(tab, column, start, middle, newMax, order);
            if (middle + 1 < end) complete=(complete)&(mergeSort(tab, column, middle + 1, end, newMax, order));
        }
        else complete=false;
        merge(tab, column, start, end, order);
        return complete;
    }
}
