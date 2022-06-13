package src.sorting;
import src.tools.*;


public class BUMergeSort {
    
 

    public static void sort(Comparable[] a){
        Comparable[] aux=new Comparable[a.length];
        int N=a.length;
        for(int sz=1;sz<N;sz+=sz){
            for(int lo=0;lo<N-sz;lo+=sz+sz){
                merge(a,aux,lo,lo+sz-1,Math.min(lo+sz+sz-1,N-1));
            }
        }
    }

    public static void merge(Comparable[] a,Comparable[] aux, int lo, int mid, int hi) {
        for (int k = lo; k <=hi; k++) {
            aux[k] = a[k];
        }
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {//if left run is finished copy right
                while(j<=hi){a[k++] = aux[j++];}
            }
            else if (j > hi){
                while(i<=mid){a[k++] = aux[i++];} //if right run is finished copy left//make this a while/for loop
            }
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
       /*for(int i=0;i<test.length;i++){
        System.out.println(test[i]);
    }*/
       System.out.println(isSorted(test));
       
   }
}
