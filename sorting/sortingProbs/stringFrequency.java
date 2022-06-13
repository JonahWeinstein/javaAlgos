package src.sorting.sortingProbs;

import java.util.Arrays;
import java.util.HashMap;

public class stringFrequency {

    public static void frequency(String[] a){
        Arrays.sort(a);
        HashMap<String,Integer> result=new HashMap<String,Integer>();
        for(int i=0; i<a.length; i++){
            int count=1;
            while(i<a.length-1 && a[i]==a[i+1]){
                count+=1;
                i++;
            }
            result.put(a[i],count);
        }
        
        for(String i: result.keySet()){
            System.out.println("String: "+i+ " Frequency : " +result.get(i));
        }
        



    }
    public static void main(String[] args){
        String[] test ={"hi","my","thought","for","for","hi","thought","pie","pieplane","hello","for","my","my","my"};
        frequency(test);

    }
    
}
