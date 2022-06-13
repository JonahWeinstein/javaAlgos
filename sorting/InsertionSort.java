package src.sorting;
import src.tools.*;

public class InsertionSort {

    public static void sort(Comparable[] a,int lo,int hi)
   {  // Sort a[] from smallest to largest.
    
    for(int i=lo+1;i<hi;i++){
        for(int j=i; j>0 && less(a[j-1],a[j]);j--){
            exch(a,j,j-1);
        }  
    }
   }

   public static boolean less(Comparable v, Comparable w)
   {
    //returns true if w<v
    return v.compareTo(w)>0;
    }

    private static void exch(Comparable[] a, int i, int j)
    {  Comparable t = a[i]; a[i] = a[j]; a[j] = t;  }


    public static boolean isSorted(Comparable[] a)
    {  // Test whether the array entries are in order.
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i-1]))  return false;
        return true;
    }
    private static void show(Comparable[] a)
   {  // draw the array, in a feild.
      StdDraw.clear();
      StdDraw.setXscale(-1,a.length+1);
      StdDraw.setYscale(0,51);

      for (int i = 0; i < a.length; i++){
          double index=i;
          StdDraw.line(index,0.0,index,(Double) a[i]);
      }
   }
    public static void main(String[] args)
    {

        int N=10;
        Integer[] test=new Integer[N];
        for(int i=0;i<N;i++){
            test[i]=StdRandom.uniform(0,50);
        }
        sort(test,0,test.length);
        for(int i=0;i<N;i++){
            System.out.println(test[i]);
        }
        
    }
}
