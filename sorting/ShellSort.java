package sorting;

import tools.*;


public class ShellSort{
    
    public static void sort(Comparable[] a){
        int N=a.length;
        int h=1;
        while(h<N/3)h=3*h+1;
        while(h>=1){
            for(int i=h;i<N;i++){
                for(int j=i; j>=h && less(a[j],a[j-h]);j=j-h){
                    exch(a,j,j-h);

                }
            }
            h=h/3;
            show(a);
        }
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


    public static boolean less(Comparable v, Comparable w){
        //returns true if w<v
        return v.compareTo(w)>0;
    }
    private static void exch(Comparable[] a, int i, int j)
   {  Comparable t = a[i]; a[i] = a[j]; a[j] = t;  }


    public static boolean isSorted(Comparable[] a)
   {  // Test whether the array entries are in order.
      for (int i = 1; i < a.length; i++)
         if (less(a[i-1], a[i]))  return false;
      return true;
   }

   
   public static void main(String[] args)
   {

       int N=200;
       Double[] test=new Double[N];
       for(int i=0;i<N;i++){
           test[i]=StdRandom.uniform(0.0,50);
       }
       sort(test);
       
   }



}