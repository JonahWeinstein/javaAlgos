package fundamentals.dequeProb;
import java.util.Iterator;
import tools.*;
/*import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;*/

 //should be implemented with an array or doubly linked list
public class Deque <Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int N;

    public Deque(){
        first=null;
        last=null;
        N=0;
    }
    private class Node {
        Item item;
        Node next;
        private Node(Item item, Node next) {
          if (item == null) throw new NullPointerException();
      // 'this' refers to the created instance and helps distinguish the field from the param
          this.item = item;  
          this.next = next;
        }
      }
    public Iterator<Item> iterator(){
        return new ListIterator();
    }
    private class ListIterator implements Iterator<Item>{
        private Node current=first;

        public boolean hasNext(){
            return current!=null;
        }
        public Item next(){
            if(current==null)
            throw new java.util.NoSuchElementException();
            Item item =current.item;
            current=current.next;
            return item;

        }}

    public boolean isEmpty(){
        return N==0;
    }
    public int size(){
        return N;
    }
    public void addFirst(Item item){
        if(item==null)
        throw new IllegalArgumentException("cannot add null");
        Node oldFirst=first;
        first=new Node(item,oldFirst);
        if (N==0) last = first;
        N++;

    }
    public Item removeFirst(){
        if(first==null) 
        throw new java.util.NoSuchElementException("cannot remove null");
        
        Item item=first.item;
        first=first.next;
        N--;
        return item;
    
    }

    public void addLast(Item item){
        if(item==null)
        throw new IllegalArgumentException("cannot add null");
        if (N == 0) {  
            // this will deal with the case (last==null) which causes the NPE
            addFirst(item);
            return;
        }
        last.next=new Node(item,null);
        last=last.next;
        N++;
        }

    public Item removeLast(){
        if(last==null) 
        throw new java.util.NoSuchElementException("cannot remove null");
        else{
        Item item=last.item;
        if(first==last){
            first=null;
            last=null;
            N--;
            return item;
        }
        for(Node n=first;n.next!=null;n=n.next){
            if(n.next==last){
                n.next=null;
                last=n;
                break;
            }
        }
        N--;
        return item;
        }

    }
    
    public static void main(String[] args){
        Deque<Integer> test = new Deque<Integer>();
        test.addFirst(4);
        test.addFirst(4);
        test.addFirst(4);
        test.addLast(4);
        test.removeFirst();
        test.removeFirst();
        test.removeFirst();
        

        for(int n:test){
            StdOut.println(n);

        } 
    }
   
}
