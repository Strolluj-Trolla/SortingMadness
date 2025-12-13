package pl.put.poznan.transformer.logic;

public class Sorter {

    private SortAlgorithm algo;

    public Sorter(){
        this.algo=new QuickSort();
    }

    public void setStrategy(SortAlgorithm algorithm){
        this.algo=algorithm;
    }

    public void sort(Cell[][] tab, int column){
        algo.sort(tab, column);
    }

    //only for 1-dim int arrays
    public static void countingSort(int[] tab) {
        int max = tab[0];
        int min = tab[0];
        for (int item : tab) {
            if (item > max) max = item;
            if (item < min) min = item;
        }
        if (min < 0) throw new IllegalArgumentException("Array contains negative values");

        int[] help = new int[max + 1];
        for (int value : tab) help[value]++;

        int i = 0;
        for (int j = 0; j < max + 1; j++) {
            for (int k = 0; k < help[j]; k++) {
                tab[i] = j;
                i++;
            }
        }

    }


}
