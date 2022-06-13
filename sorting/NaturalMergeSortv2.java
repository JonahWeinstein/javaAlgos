package src.sorting;
import  src.tools.*;

public class NaturalMergeSortv2 {
        private static Comparable[] b;      // temp array
        private static Comparable[] o;      // original array reference
        private static Comparable[] t;      // used to swap a, b
    
        public static void sort(Comparable[] a) {
            o = a;                          // save ref to a
            b = new Comparable[a.length];   // allocate temp array
            int i;
            int j;
            int k;
            while (true) {                  // merge pass
                i = 0;
                while(true) {               // find, merge pair of runs
                    j = i;                  // find left run
                    while (++j < a.length) {
                        if (a[j-1].compareTo(a[j]) > 0)
                            break;
                    }
                    if(j == a.length){      // if only one run left
                        if(i != 0){         //   if not done
                            while(i < j){   //     copy run to b
                                b[i] = a[i];
                                i++;
                            }
                            break;          //   break to end merge pass
                        } else {            // else sort done
                            if(a != o){     //   if a not original a, copy
                                for(i = 0; i < a.length; i++)
                                    b[i] = a[i];
                            }
                            return;
                        }
                    }
                    k = j;                  // find right run
                    while (++k < a.length) {
                        if (a[k-1].compareTo(a[k]) > 0){
                            break;
                        }
                    }
                    Merge(a, b, i, j, k);   // merge left, right into b
                    i = k;
                    if(i == a.length)       // break if end pass
                        break;
                }
                t = a;                      // swap a and b (references)
                a = b;
                b = t;
            }
        }
    
        // merge left and right runs from a[] to b[]
        // ll = start of left run
        // rr = start of right run == end of left run
        // ee = end of right run
        public static void Merge(Comparable[] a, Comparable[] b, int ll, int rr, int ee) {
            int i = ll;
            int j = rr;
            int k = ll;
            while(true){
                // if left element <= right element
                if (a[i].compareTo(a[j]) <= 0) {
                    b[k++] = a[i++];        // copy left element
                    if(i == rr){            // if end of left run
                        while(j < ee)       //   copy rest of right run
                            b[k++] = a[j++];
                    return;                 //   and return
                    }
                } else {
                    b[k++] = a[j++];        // copy right element
                    if(j == ee){            // if end of right run
                        while(i < rr){      //   copy rest of left run
                            b[k++] = a[i++];
                        }
                    return;                 //   and return
                    }
                }}
            }
            

        public static boolean isSorted(Comparable[] a) { // Test whether the array entries are in order.
            for (int i = 1; i < a.length; i++)
                if (a[i - 1].compareTo(a[i])>0)
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
        }}
        


