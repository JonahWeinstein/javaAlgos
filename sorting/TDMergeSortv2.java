package sorting;
import tools.*;


public class TDMergeSortv2 {
    public static void sort(Comparable[] a){
        Comparable[] aux=new Comparable[a.length];
        for (int k = 0; k <a.length; k++) {
            aux[k] = a[k];
        }
        sort(aux,a,0,a.length-1);

    }
    public static void sort(Comparable[] a,Comparable[] aux, int lo, int hi){
        if(hi<=lo)return;
        if(a.length<7)InsertionSort.sort(a,lo,hi);
        else{
        int mid=lo+(hi-lo)/2;
        sort(aux,a,lo,mid);
        sort(aux,a,mid+1,hi);
        merge(a,aux,lo,mid,hi);
        }

    }
    public static void merge(Comparable[] a,Comparable[] aux, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;
        if(a[mid].compareTo(a[mid+1])<=0){
            for(int h=lo;h<=hi;h++)aux[h]=a[h];
            return;
        }
        for (int k = lo; k <= hi; k++) {
            if (i > mid){
                while(k<=hi)
                    aux[k++] = a[j++];
                break;
            }
            else if (j > hi){
                while(k<=hi)
                    aux[k++] = a[i++];
                break;  
            }
            else if (less(a[i], a[j]))
                aux[k] = a[j++];
            else
                aux[k] = a[i++];
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
        //Integer[] test={1,2,3,4,4,6,7,8,6,10,0,46};
       sort(test);
       System.out.println(isSorted(test));
       /*for(int i=0;i<12;i++){
        System.out.println(test[i]);
    }*/
   }
    
}
