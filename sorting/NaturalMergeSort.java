package src.sorting;

import src.tools.*;

public class NaturalMergeSort {

    public static void sort(Comparable[] a) {
        int i;
        int j; // pointer for left run
        int k; // pointer for right run
        Comparable[] aux = new Comparable[a.length];
        while (true) { // merge pass
            i = 0;
            while (true) { // find, merge pair of runs
                // System.out.println("outer");
                j = i;
                while (++j < a.length) { // find left run
                    if (a[j - 1].compareTo(a[j]) > 0) {
                        break;
                    }
                    // System.out.println("left");
                }

                if (j == a.length) { // if only one run left
                    if (i == 0) // if done return
                        return;
                    else {// else end of merge pass
                          // System.out.println("break1");
                        break;
                    }
                }
                k = j;
                while (++k < a.length) { // find right run
                    if (a[k - 1].compareTo(a[k]) > 0) {
                        // System.out.println("break2");
                        break;
                    }
                    // System.out.println("right");
                }
                merge(a, aux, i, j, k);
                i = k;
                if (i == a.length) {
                    // System.out.println("break3");
                    break;
                }
            }
        }
    }

    public static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        for (int k = lo; k < hi; k++) {

            aux[k] = a[k];
        }
        int i = lo, j = mid;
        for (int k = lo; k < hi; k++) {
            if (i == mid) { // when left half is done add rest of right half
                while (j < hi) {
                    a[k++] = aux[j++];
                }
            } else if (j == hi) { // when right run is done add rest of left run
                while (i < mid) {
                    a[k++] = aux[i++];
                }
            } else if (aux[i].compareTo(aux[j]) > 0)
                a[k] = aux[j++];
            else
                a[k] = aux[i++];
        }
    }

    public static boolean isSorted(Comparable[] a) { // Test whether the array entries are in order.
        for (int i = 1; i < a.length; i++)
            if (a[i - 1].compareTo(a[i]) > 0)
                return false;
        return true;
    }

    public static void main(String[] args) {

        int N = 1000000;
        Integer[] test = new Integer[N];
        for (int i = 0; i < N; i++) {
            test[i] = StdRandom.uniform(0, 50);
        }
        sort(test);
        System.out.println(isSorted(test));
        /*
         * for(int i=0;i<N;i++){ System.out.println(test[i]); }
         */
    }

}
