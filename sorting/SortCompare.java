package sorting;
import tools.*;

public class SortCompare {
    
   
   
   public static double time(String alg, Comparable[] a)
   {
      Stopwatch timer = new Stopwatch();
      
      if (alg.equals("BUMerge")) BUMergeSort.sort(a);
      if (alg.equals("NatMerge")) NaturalMergeSort.sort(a);
      if (alg.equals("TDMergev2"))     TDMergeSortv2.sort(a);
      if (alg.equals("NatMergev2"))     NaturalMergeSortv2.sort(a);
      if (alg.equals("NatMerge"))     NaturalMergeSort.sort(a);
      if (alg.equals("Quick"))      QuickSort.sort(a);
      if (alg.equals("Quick3WayPart"))      Quick3WayPart.sort(a);
      if (alg.equals("DualPivotQuickSort"))  DualPivotQuickSort.sort(a);
      if (alg.equals("Heap"))  HeapSort.sort(a);
      return timer.elapsedTime();
   }
   
   
    public static double timeRandomInput(String alg, int N, int T)
      {  // Use alg to sort T random arrays of length N.
         double total = 0.0;
         Integer[] a = new Integer[N];
         for (int t = 0; t < T; t++)
         {  // Perform one experiment (generate and sort an array).
            for (int i = 0; i < N; i++)
               a[i] = StdRandom.uniform(0, N); //generally there won't be N distinct values
            total += time(alg, a);
         }
         return total;
      }
   
      public static void main(String[] args)
        {
          int N=1000000;
          int T=100;
          double t1 = timeRandomInput("DualPivotQuickSort", N, T); // total for alg1
          double t2 = timeRandomInput("Quick3WayPart", N, T); // total for alg2
          StdOut.printf("For %d random Doubles\n    %s is", N, "DualPivotQuickSort");
          StdOut.printf(" %.1f times faster than %s\n", t2/t1, "Quick3WayPart");
       }
   
   
}



    
     
     
    

