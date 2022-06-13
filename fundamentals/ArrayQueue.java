package fundamentals;
public class ArrayQueue{

    private String[] s;
    private int tail;
    private int head;

    public ArrayQueue(){
        s=new String[1];
        tail=0;
        head=0;
    }

    private void resizeArray(int capacity){
        String[] copy=new String[capacity];
        for(int i=0;i<tail;i++){
            copy[i]=s[i];
        }
        s=copy;
    }

    public boolean isEmpty(){
        return s[head]==null;
    }

    public void enqueue(String item){
        if(tail==s.length){
            resizeArray(s.length*2);
        }
        s[tail]=item;
        tail++;
    }
    public String dequeue(){
        if(tail==s.length/4){
            resizeArray(s.length/2);
        }
        String item=s[head];
        head++;
        return item;
    }
    public static void main(String[] args){
        ArrayQueue test = new ArrayQueue();
        test.enqueue("hello");
        test.enqueue("good");
        test.enqueue("good");
        test.dequeue();
        test.dequeue();
        test.dequeue();
        System.out.println(test.isEmpty());


    }


}