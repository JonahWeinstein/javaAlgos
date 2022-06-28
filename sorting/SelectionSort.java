package sorting;
import java.lang.Comparable;

public class SelectionSort {

    public static void sort(Comparable[] a){
        int n =a.length;
        for(int i=0;i<n;i++){
                //index of item to compare against 
                int min=i;
            for(int j=i+1;j<n;j++){
                if(less(a[min],a[j])){
                    min=j;
                }
            }
            exch(a,min,i);
        }
    }
    public static boolean less(Comparable v, Comparable w){
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

   public static void main(String[] args){
        Integer[] test={4,5,6,2,1,8,40,39,50,26};
        SelectionSort.sort(test);
        for(int i=0;i<test.length;i++){
            System.out.println(test[i]);
        }
   }
    
}
