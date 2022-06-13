package src.fundamentals;


public class ArrayStackOfStrings{
    private String[] s;
    private int N;

    public ArrayStackOfStrings(){
        s=new String[1];
        N=0;
    }

    public void push(String item){
        if ( N == s.length){
            resizeArray(s.length*2);
        }
        s[N++]=item;
        
    }

    public String pop(){
        String item=s[--N];
        if(N>0 && N==s.length/4){
            resizeArray(s.length/2);
        }
        return item;
    }
    public String peek(){
        String copy=s[N-1];
        return copy;

    }

    private void resizeArray(int capacity){
        String[] copy=new String[capacity];
        for(int i=0;i<N;i++){
            copy[i]=s[i];
        }
        s=copy;
    }

    public static void main(String[] args){
        ArrayStackOfStrings test = new ArrayStackOfStrings();
        test.push("hello");
        test.push("goodbye");
        test.push("h");
        test.pop();
        System.out.println(test.peek());
    }

}