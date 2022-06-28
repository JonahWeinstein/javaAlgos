package sorting;
import tools.*;

public class Quick3WayPart {
    //Worst case nlogn for all n, NO REAL

    public static void sort(Comparable[] a){
        sort(a,0,a.length-1);
    }

    public static void sort(Comparable[] a,int lo,int hi){
        int M=17;
        if(hi<=lo+M){
            InsertionSort.sort(a,lo,hi+1);
            return;
        }
        int pivotVal=StdRandom.uniform(lo,hi);  //choose a random index as pivot
        exch(a,hi,pivotVal);   //exchange with last index
        Comparable v=a[hi];
        int i=lo-1,j=hi,lt=lo-1,gt=hi;  //lt and gt are pointers
        while(true){
            while(a[++i].compareTo(v)<0){
                if(i==hi)break;
            }
            while(a[--j].compareTo(v)>0){
                if(j==lo)break;
            }
            if(i>=j)break;
            exch(a,i,j);
            if(a[i]==v)exch(a,++lt,i);
            if(a[j]==v)exch(a,--gt,j);
        }
        exch(a,hi,i);
        j=i-1;
        i=i+1;
        for(int k=lo;k<lt;k++,j--)exch(a,k,j);
        for(int k=hi-1;k>gt;k--,i++)exch(a,k,i);
        sort(a,lo,j);
        sort(a,i,hi);
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
        
 
      /*  for(int i=0;i<19;i++){
         System.out.println(test[i]);
     }*/
    }
}
