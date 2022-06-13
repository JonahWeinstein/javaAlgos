package sorting;


/* To sort an array a[] of strings, we 3-way partition them on their first 
character, then (recursively) sort the three resulting subarrays: 
the strings whose first character is less than the partitioning character, 
the strings whose first character is equal to the partitioning character 
(excluding their first character in the sort), and the strings whose first 
character is greater than the partitioning character. */ 


public class StringQuickSort {

    private static int charAt(String s, int d) {
        if(d < s.length()) {
            return s.charAt(d);
        }
        else {
            return -1;
        }
    }
    // used for swapping (exchanging) array values
    private static void exch(String[] a, int i, int j) {
        String t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void sort(String[] a) {
        sort(a, 0, a.length - 1, 0);
    }

    public static void sort(String[] a, int lo, int hi, int d) {
        if (hi <= lo) return;

        int lt = lo, gt = hi;
        int pivot = charAt(a[lo], d);
        int i = lo + 1;
        // partition elements in 3 way quicksort fasion
        // a[lo... lt - 1] < pivot = a[lt... gt] < a[gt + 1... hi]
        while(i <= gt) {
            int current = charAt(a[i], d);
            // if current is less than pivot swap it with lt and incremenet both
            if (current < pivot) exch(a, lt++, i++);
            // if current is less than pivot swap it with gt (do not need to increment i)
            else if (current > pivot) exch(a, i, gt--);
            // increment i to form middle of partitioned array equal to pivot val
            else i++;
        }
        // recursively sort first char of first third
        sort(a, lo, lt-1, d);
        // sort next char for equal values
        if (pivot >= 0) sort(a, lt, gt, d+1);
        // sort last third 
        sort(a, gt+1, hi, d);


    }
    public static void main(String[] args)
    {
 
        String[] test = {"hello", "1bstdf", "goodbye", "1afive", "bar", "have", "godly"};
        sort(test);
        
        for (int i = 0; i< test.length; i++){
            System.out.println(test[i]);
        }
           
        
    }
}
