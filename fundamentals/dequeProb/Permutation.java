package src.fundamentals.dequeProb;
import src.tools.*;
/*import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;*/

public class Permutation {
    public static void main(String[] args){
        int k=Integer.parseInt(args[0]);

        RandomizedQueue<String> test=new RandomizedQueue<String>();

        for(int i=0;i<k;i++){
            test.enqueue(StdIn.readString());
        }
        for(int i=0;i<k;i++){
            System.out.println(test.dequeue());
        }

        
    }
 }