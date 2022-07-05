package sorting;



public class MinPQ<Key extends Comparable<Key>> {
    private Key[] pq;
    private int N=0;

    public MinPQ(int size){
        pq=(Key[]) new Comparable[size+1];


    }

    public void insert(Key key){
        if(N+1==pq.length)resizeArray(pq.length*2);
        pq[++N]=key;
        swim(N);
    }
    public Key delMin(){
        if(N+1==pq.length/4)resizeArray(pq.length/2);
        Key min=pq[1];
        swap(1,N--);
        sink(1);
        pq[N+1]=null;
        return min;
        
    }

    private void swap(int i, int j){
        Key t=pq[i];
        pq[i]=pq[j];
        pq[j]=t;
    }
    private void swim(int k){ //bottom-up reheapify
        while(k>1 && pq[k].compareTo(pq[k/2])<0){
            swap(k,k/2);
            k=k/2;
        }
    }
    private void sink(int k){ //top-down reheapify
        while(2*k<=N){
            int j=2*k;
            if(j < N && pq[j].compareTo(pq[j+1])>0)j++; //choose the smaller of the children
            if(pq[k].compareTo(pq[j])<0)break;
            swap(k,j);
            k=2*k;
        }
    }
    public void resizeArray(int capacity){
        Key[] copy=(Key[]) new Comparable[capacity];
        for(int i=0;i<=N;i++){
            copy[i]=pq[i];
        }
        pq=copy;


    }
    public static void main(String[] args){
        int size=10;
        MinPQ<Integer> test=new MinPQ<Integer>(5);
        for(int i=0;i<size;i++){
            test.insert(i);
        }
        for(int i=0;i<size;i++){
            System.out.println(test.delMin());
        }
       
    }


    
}
