package src;

public class MaxPQ<Key extends Comparable<Key>> {
    private Key[] pq;
    private int N = 0;

    public MaxPQ(int maxN){
        pq=(Key[]) new Comparable[maxN+1]; 
    }
    public MaxPQ(Comparable[] input){
        pq=(Key[]) new Comparable[input.length+1]; 
        for(int i=0;i<input.length;i++){
            pq[++N]= (Key) input[i];
        }
        for(int k=N/2;k>=1;k--){
            sink(k);
        }
    }

    public void insert(Key v){
        if(N+1==pq.length)resizeArray(pq.length*2);
        pq[++N]=v;
        swim(N);
    }
    public Key delMax(){
        if(N+1==pq.length/4)resizeArray(pq.length/2);
        Key max=pq[1];
        exch(1,N--);
        pq[N+1]=null;
        sink(1);
        return max;
    }
    public int size(){return N;}
    public boolean isEmpty(){ return N == 0; }

    private void swim(int k) { //used to reheapify when new key could be greater than  parent
        while (k > 1 && pq[k].compareTo(pq[k / 2]) > 0) {
            exch(k, k / 2);
            k = k / 2;
        }
    }
    private void sink(int k) {  //used to reheapify when new key could be less than children
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && pq[j].compareTo(pq[j+1])<0)
                j++;
            if (pq[k].compareTo(pq[j])>0)
                break;
            exch(k, j);
            k = j;
        }
    }
    private void resizeArray(int capacity){
        Key[] copy=(Key[]) new Comparable[capacity];
        for(int i=0;i<=N;i++){
            copy[i]=pq[i];
        }
        pq=copy;
    }
    private void exch(int i, int j) {
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }

    public static void main(String[] args){
        int size=10;
        Comparable[] a={5,60,44,32,1,3,90,49};
        MaxPQ<Integer> test=new MaxPQ<Integer>(a);
        for(int i=0;i<a.length;i++){
            System.out.println(test.delMax());
        }
    }
}
