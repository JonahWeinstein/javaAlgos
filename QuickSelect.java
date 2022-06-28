package src;

import tools.*;
import sorting.QuickSort;

public class QuickSelect {

    public static Comparable select(Comparable[] a,int k){
        int lo=0,hi=a.length-1;
        while(hi>lo){
            int j=partition(a,lo,hi);
            if(j<k)lo=j+1;
            else if(j>k)hi=j-1;
            else{return a[k];}
        }
        return a[k];
    }

    private static int partition(Comparable[] a,int lo,int hi){
        //int[] med=new int[3];
        //for(int i=0;i<3;i++)med[i]=StdRandom.uniform(lo,hi+1);
        int pivotVal=StdRandom.uniform(lo,hi);  
        exch(a,lo,pivotVal);
        //choose sample median as the partitioning item
        Comparable v=a[lo];
        int i=lo,j=hi+1;
        while(true){
            while(a[++i].compareTo(v)<=0){
                if(i==hi)break;
            }
            while(a[--j].compareTo(v)>=0){
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
        QuickSort.sort(values);
        for(int i = 0;i < 3;i++){
            if(a[index[i]]==values[1])output=index[i];
        }
        return output;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
    
    public static void main(String[] args){
        int N=1000000;
        
        Integer[] test=new Integer[N];
        for(int i=0;i<N;i++){
            test[i]=i;
        }
        
        System.out.println(select(test,300742));
    }
}
