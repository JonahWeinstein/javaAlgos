package src.fundamentals;
import java.util.Scanner;
import java.io.FileReader;
import java.util.Iterator;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;




public class LinkedQueue<Item> implements Iterable<Item>
{
   private Node first; // link to least recently added node
   private Node last;  // link to most recently added node
   private int N;      // number of items on the queue

 private  class Node
   {  // nested class to define nodes
      Item item;
      Node next;
   }

   public Iterator <Item> iterator(){
       return new ListIterator();

   } 
   private class ListIterator implements Iterator<Item>{
       private Node current=first;

       public boolean hasNext(){
           return current!=null;
       }
       public Item next(){
           Item item=current.item;
           current=current.next;
           return item;
       }



   }

 public boolean isEmpty() {  return first == null;  }
   public int size()        {  return N;  }
   public void enqueue(Item item)
   {  // Add item to the end of the list.
      Node oldlast = last;
      last = new Node();
      last.item = item;
      last.next = null;
      if (isEmpty()) first = last;
      else           oldlast.next = last;
      N++;
   }

 public Item dequeue()
   {  // Remove item from the beginning of the list.
      Item item = first.item;
      first = first.next;
      N--;
      if (isEmpty()) last = null;
      return item;
   }



   public static void main(String[] args){
// Create a queue and enqueue/dequeue strings.
 LinkedQueue<String> q = new LinkedQueue<String>();
 try{
 //read in a file

 String fileName="test.txt";
 FileReader file=new FileReader(fileName);
 Scanner in=new Scanner(file);

 while (in.hasNextLine())
   {
      String item = in.nextLine();
      if (!item.equals("-"))
           q.enqueue(item);
      else if (!q.isEmpty()) {
          System.out.println(q.dequeue() + " ");
        }     
   }
   in.close();

}catch(NoSuchElementException e){
    System.out.println("No element found ");
    e.printStackTrace();
}catch(FileNotFoundException e){
    System.out.println("Please make sure file path is correct ");
    e.printStackTrace();
}
System.out.println("(" + q.size() + " left on queue)");

for(String s:q){
    System.out.println(s);
}



}}



