package sorting;

import tools.*;

public class QuickSort {
    
    public static void sort(Comparable[] a){
        sort(a,0,a.length-1) ;
    }
    private static void sort(Comparable[] a,int lo,int hi){
        int M=7;
        if(hi<=lo+M){
            InsertionSort.sort(a,lo,hi+1);
            return;
        }
        int j=partition(a,lo,hi);
        sort(a,lo,j-1);
        sort(a,j+1,hi);
    }

    private static int partition(Comparable[] a,int lo,int hi){
        /*int[] med=new int[3];
        for(int i=0;i<3;i++)med[i]=StdRandom.uniform(lo,hi+1);*/
        int pivotVal=StdRandom.uniform(lo,hi);  
        exch(a,lo,pivotVal);
        //choose sample median as the partitioning item
        Comparable v=a[lo];
        int i=lo,j=hi+1;
        while(true){
            while(a[++i].compareTo(v)<0){
                if(i==hi)break;
            }
            while(a[--j].compareTo(v)>0){
                if(j==lo)break;
            }
            if(i>=j)break;
            exch(a,i,j);
        }
        exch(a,lo,j);
        return j;
    }
    private static int medianOf3(Comparable[] a, int[] index){
        int output=index[0];
        Comparable[] values=new Comparable[3];
        for(int i = 0;i < 3;i++)values[i]=a[index[i]];
        sort(values);
        for(int i = 0;i < 3;i++){
            if(a[index[i]]==values[1])output=index[i];
        }
        return output;
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
       

    /*   for(int i=0;i<19;i++){
        System.out.println(test[i]);
    }*/
   }
}
