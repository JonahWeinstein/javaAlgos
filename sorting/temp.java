package src.sorting;
import src.tools.*;

public class temp {

        // quicksort the array a[] using dual-pivot quicksort
        public static void sort(Comparable[] a) {
            StdRandom.shuffle(a);
            sort(a, 0, a.length - 1);
           
        }
    
        // quicksort the subarray a[lo .. hi] using dual-pivot quicksort
        private static void sort(Comparable[] a, int lo, int hi) { 
            if (hi <= lo) return;
    
            // make sure a[lo] <= a[hi]
            if (less(a[hi], a[lo])) exch(a, lo, hi);
    
            int lt = lo + 1, gt = hi - 1;
            int i = lo + 1;
            while (i <= gt) {
                if       (less(a[i], a[lo])) exch(a, lt++, i++);
                else if  (less(a[hi], a[i])) exch(a, i, gt--);
                else                         i++;
            }
            exch(a, lo, --lt);
            exch(a, hi, ++gt);
    
            // recursively sort three subarrays
            sort(a, lo, lt-1);
            if (less(a[lt], a[gt])) sort(a, lt+1, gt-1);
            sort(a, gt+1, hi);
    
           
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
 
        int N=100;
        //Integer[] test={2,3,8,1,30,24,40,3,1,5,6,3,2,1,4,5,6,8,50};
        Integer[] test=new Integer[N];
     
        for(int i=0;i<N;i++){
            test[i]=StdRandom.uniform(0,N);
        }
 
        
        sort(test);
        System.out.println(isSorted(test));
        
 
      /*  for(int i=0;i<19;i++){
         System.out.println(test[i]);
     }*/
    }
    
}
