package sorting;

import tools.*;

public class HeapSort {

    //INDICES MAY SEEM WEIRD TO HANDLE HEAPS 1-BASED INDEXING
    //see helper functions to clarify what's going on

    public static void sort(Comparable[] a){
        int N=a.length;
        for(int k=N/2;k>=1;k--){
            sink(a,k,N);
        }
        while(N>1){
            exch(a,1,N--);
            sink(a,1,N);
        }
    }
    static void sink(Comparable[] a,int k,int size) {  //used to reheapify when new key could be less than children
        while (2 * k <= size) {
            int j = 2 * k;
            if (j < size && a[j-1].compareTo(a[j])<0)
                j++;
            if (a[k-1].compareTo(a[j-1])>0)
                break;
            exch(a,k, j);
            k = j;
        }
    }

    //INDICES ARE OFF BY ONE 
    static void exch(Comparable[] a,int i, int j) {
        Comparable t = a[i-1];
        a[i-1] = a[j-1];
        a[j-1] = t;
    }
    private static boolean isSorted(Comparable[] a){
        for(int i=1;i<a.length;i++){
            if(a[i].compareTo(a[i-1])<0)return false;
        }
        return true;         
    }

    public static void main(String[] args)
    {
        int N=1000000;
        //Integer[] test={2,3,8,1,30,24,40,3,1,5,6,3,2,1,4,5,6,8,50};
        Integer[] test=new Integer[N];

        for(int i=0;i<N;i++){
            test[i]=StdRandom.uniform(0,N);
        }

        sort(test);
        System.out.println(isSorted(test));

        /*for(int i=0;i<19;i++){
         System.out.println(test[i]);
     }*/
    } 
}
