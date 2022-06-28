package sorting;
import tools.*;


public class TDMergeSortv1 {
    private static final int _0 = 0;
    public static void sort(Comparable[] a){
        Comparable[] aux=new Comparable[a.length];
        sort(a,aux, 0, a.length-1);

    }
    public static void sort(Comparable[] a,Comparable[] aux, int lo, int hi){
        if(hi<=lo)return;
        int mid=lo+(hi-lo)/2;
        sort(a,aux,lo,mid);
        sort(a,aux,mid+1,hi);
        merge(a,aux,lo,mid,hi);

    }
    public static void merge(Comparable[] a,Comparable[] aux, int lo, int mid, int hi) {
        if(a[mid].compareTo(a[mid+1])<=0)return;
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }
        for (int k = lo; k <= hi; k++) {
            if (i > mid)
                a[k] = aux[j++];
            else if (j > hi)
                a[k] = aux[i++];
            else if (less(aux[i], aux[j]))
                a[k] = aux[j++];
            else
                a[k] = aux[i++];
        }
    }


    public static boolean less(Comparable v, Comparable w) {
        // returns true if w<v
        return v.compareTo(w) > 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static boolean isSorted(Comparable[] a) { // Test whether the array entries are in order.
        for (int i = 1; i < a.length; i++)
            if (less(a[i - 1], a[i]))
                return false;
        return true;
    }
   public static void main(String[] args)
   {

       int N=1000000;
       Integer[] test=new Integer[N];
       for(int i=0;i<N;i++){
           test[i]=StdRandom.uniform(0,50);
       }
       sort(test);
       System.out.println(isSorted(test));
       /*for(int i=0;i<N;i++){
        System.out.println(test[i]);
    }*/
   }
    
}
