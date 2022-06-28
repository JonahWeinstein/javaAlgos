package fundamentals.dequeProb;
import java.util.Iterator;
import tools.*;
/*import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;*/


public class RandomizedQueue<Item> implements Iterable<Item>{
    private Item[] s;
    private int head;
    private int tail;

    public RandomizedQueue(){
        s=(Item[]) new Object[1];
        head=0;
        tail=0;

    }

    private void resizeArray(int capacity){
        Item[] copy=(Item[]) new Object[capacity];
        for(int i=0;i<tail;i++){
            copy[i]=s[i];
        }
        s=copy;
    }
    public boolean isEmpty(){
        return s[head]==null;

    }
  

    // return the number of items on the randomized queue
    public int size(){
        return tail-head;
    }

    public void enqueue(Item item){
        if(item==null)
        throw new IllegalArgumentException("cannot add null");
        if(tail==s.length){
            resizeArray(s.length*2);
        }
        s[tail]=item;
        tail++;
    }
    public Iterator<Item> iterator(){
        return new ListIterator();
    }
    private class ListIterator implements Iterator<Item>{
        private int current=head;
        //Item[] copy=(Item[]) new Object[s.length];
        
        public boolean hasNext(){
            return current<tail;
        }

        public Item next(){
            if(current>tail)
            throw new java.util.NoSuchElementException();
            return s[current++];
        }

    }
    // remove and return a random item
    public Item dequeue(){
        if(tail==0)
        throw new java.util.NoSuchElementException();
        if(tail==s.length/4){
            resizeArray(s.length/2);
        }
        Item[] copy=(Item[]) new Object[s.length];
        int randIndex=StdRandom.uniform(head,tail);
        Item value =s[randIndex];

        for (int i = 0, j = head; i < (tail-head); i++ ) {
            if (i != randIndex) {
                copy[j++] = s[i];
        }
        }
        s=copy;
        tail--;
        return value;
    }
    

    // return a random item (but do not remove it)
    public Item sample(){
        if(tail==0)
        throw new java.util.NoSuchElementException();
        int randIndex=StdRandom.uniform(head,tail);
        return s[randIndex];

    }

    public static void main(String[] args){
        RandomizedQueue<Integer> test=new RandomizedQueue<Integer>();
        test.enqueue(4);
        test.enqueue(7);
        test.enqueue(1);
        test.enqueue(9);
        for(int item:test){
            StdOut.println(item);
        }
        
    }
}