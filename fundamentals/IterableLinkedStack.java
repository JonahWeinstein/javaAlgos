package src.fundamentals;
import java.util.Iterator;

public class IterableLinkedStack<Item> implements Iterable<Item>{


    private Node first=null;

    private class Node{
        Item item;
        Node next;
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
            Item item =current.item;
            current=current.next;
            return item;
        }
    }

    public boolean isEmpty(){
        return first==null;
    }
    public void push(Item item){
        Node oldfirst=first;
        first=new Node();
        first.item=item;
        first.next=oldfirst;
    }
    public Item pop(){
            Item item=first.item;
            first=first.next;
            return item;
    }
    public static void main(String[] args){
        IterableLinkedStack<String> s= new IterableLinkedStack<String>();
        s.push("hello");
        s.push("goodbye");
        s.push("to");
        s.push("to");
        s.push("to");
        for(String item:s){
            System.out.println(item);

        }
       

    }
        
    
    
}