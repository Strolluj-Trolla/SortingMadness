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
            double x=tab[j];
            int i=j+1;
            while((i<=n)&&(x>tab[i])){
                tab[i-1]=tab[i];
                i++;
            }
            tab[i-1]=x;
        }
    }

    public static void binaryInsertSort(double [] tab) {
        int n=tab.length-1;

        for(int j=n-1;j>=0;j--) {
            double x=tab[j];
            int p=j;
            int k=n+1;
            while(k-p>1){
                int i=(p+k)/2;
                if (x<=tab[i])k=i;
                else p=i;
            }
            for(int i=j; i<p; i++) {
                tab[i]=tab[i+1];
            }
            tab[p]=x;
        }
    }

}
