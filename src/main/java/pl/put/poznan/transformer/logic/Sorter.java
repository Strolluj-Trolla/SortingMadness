package pl.put.poznan.transformer.logic;

public class Sorter {

    public static void bubbleSort(double [] tab) {
        boolean changed=false;
        for(int i=tab.length-1;i>0;i--) {
            for(int j=0; j<i; j++) {
                if(tab[j]>tab[j+1]) {
                    double helper=tab[j+1];
                    tab[j+1]=tab[j];
                    tab[j]=helper;
                    changed=true;
                }
            }
            if(!changed)return;
        }
    }

}
