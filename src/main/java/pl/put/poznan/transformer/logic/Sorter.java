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

    private static void merge(double [] tab, int start, int end) {

        double [] helper=new double[end-start+1];
        if(end-start>=0)System.arraycopy(tab, start, helper, 0, end-start+1);

        int middle=(start+end)/2;
        int i=start;
        int i_left=start;
        int i_right=middle+1;
        while(i_left<=middle && i_right<=end) {
            if(helper[i_left-start]<helper[i_right-start]) {
                tab[i]=helper[i_left-start];
                i_left++;
            }
            else {
                tab[i]=helper[i_right-start];
                i_right++;
            }
            i++;
        }
        while(i_left<=middle) {
            tab[i]=helper[i_left-start];
            i_left++;
            i++;
        }
        while(i_right<=end) {
            tab[i]=helper[i_right-start];
            i_right++;
            i++;
        }

    }

    private static void mergeSort(double [] tab, int start, int end) {

        int middle=(start+end)/2;
        if(start<middle) mergeSort(tab, start, middle);
        if(middle+1<end) mergeSort(tab, middle+1, end);
        merge(tab, start, end);

    }

    public static void mergeSort(double[] tab){
        merge(tab, 0, tab.length-1);
    }

}
