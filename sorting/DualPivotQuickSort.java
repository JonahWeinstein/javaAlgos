package sorting;

import tools.*;

public class DualPivotQuickSort {

    public static void sort(Comparable[] a){
        StdRandom.shuffle(a);
        sort(a,0,a.length-1);
    }

    public static void sort(Comparable[] a,int lo,int hi){
        int CUTOFF=17;
        if(hi<=lo+CUTOFF){
            InsertionSort.sort(a,lo,hi+1);
            return;
        }
        //randomly choose pivots
        int p1=StdRandom.uniform(lo+1,hi);
        int p2=StdRandom.uniform(lo+1,hi); 

        
        if(a[p1].compareTo(a[p2])<=0){
            exch(a,lo,p1);
            exch(a,hi,p2);
        } 
        else{                   //should make below comparison unneccessary 
            exch(a,lo,p2);
            exch(a,hi,p1);
        }
        if(a[lo].compareTo(a[hi])>0)exch(a,lo,hi);
             //Why doesn't sort work without this???
        Comparable pivot1=a[lo];
        Comparable pivot2=a[hi];

        int i = lo+1, lt = lo+1, gt = hi-1;
            while(i<=gt){
                if  (a[i].compareTo(pivot1)<0)exch(a,i++,lt++);
                else if(a[i].compareTo(pivot2)>0)exch(a,i,gt--);
                else    i++;
            }
    
            exch(a, lo, --lt);
            exch(a, ++gt, hi);
            //recursion
            sort(a,lo,lt-1);
            sort(a,gt+1,hi);
            //only use recursion on middle values if they arent equal
            if(a[lt].compareTo(a[gt])<0)sort(a,lt+1,gt-1);
    }


    private static boolean isSorted(Comparable[] a){
        for(int i=1;i<a.length;i++){
            if(a[i].compareTo(a[i-1])<0)return false;
        }
        return true;
            
    }
    
    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
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
        
 
       /* for(int i=0;i<19;i++){
         System.out.println(test[i]);
     }*/
    }   
}
