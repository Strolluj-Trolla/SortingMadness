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

    public static void insertSort(double [] tab) {
        int n=tab.length-1;

        for(int j=n-1;j>=0;j--) {
            double helper=tab[j];
            int i=j+1;
            while((i<=n)&&(helper>tab[i])){
                tab[i-1]=tab[i];
                i++;
            }
            tab[i-1]=helper;
        }
    }

    public static void binaryInsertSort(double [] tab) {
        int n=tab.length-1;

        for(int j=n-1;j>=0;j--) {
            double helper=tab[j];
            int p=j;
            int k=n+1;
            while(k-p>1){
                int i=(p+k)/2;
                if (helper<=tab[i])k=i;
                else p=i;
            }
            for(int i=j; i<p; i++) {
                tab[i]=tab[i+1];
            }
            tab[p]=helper;
        }
    }

    public static void selectionSort(double[] tab) {

        for(int k=tab.length-1; k>0; k--) {
            int max=k;
            for (int i=0; i<k;i++) {
                if(tab[i]>tab[max])max=i;
            }
            double helper=tab[max];
            tab[max]=tab[k];
            tab[k]=helper;
        }

    }

}
