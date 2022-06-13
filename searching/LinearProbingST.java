package src.searching;

import java.util.LinkedList;
import java.util.Queue;
import java.util.LinkedList;

import src.tools.StdRandom;

public class LinearProbingST<Key, Value>{

    private int M = 17;
    private int N = 0;
    private Value[] vals;
    private Key[] keys;

    public LinearProbingST() {
        vals = (Value[]) new Object[M];
        keys = (Key[]) new Object[M];
    }

    public LinearProbingST(int capacity) {
        M = capacity;
        vals = (Value[]) new Object[M];
        keys = (Key[]) new Object[M];
    }

    private int hash(Key key) {
        return (key.hashCode() & 0x7FFFFFFF) % M;
    }

    public Value get(Key key) {
        for (int i = hash(key); keys[i] != null; i = (i + 1) % M) { // once i exceeds M it'll wrap to 0,1,2,etc
            if (key.equals(keys[i]))
                return vals[i];
        }
        return null;
    }

    public void put(Key key, Value val) {
        if (N >= M / 2)
            resize(M * 2);
        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % M) {
            if (key.equals(keys[i])) {
                vals[i] = val;
                return;
            }
        }
        keys[i] = key;
        vals[i] = val;
        N++;
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }
    public int size(){
        return N;
    }
    
    public Iterable<Key> keys() {
        Queue<Key> queue = new LinkedList<Key>();
        for (int i = 0; i < M; i++)
            if (keys[i] != null) queue.add(keys[i]);
        return queue;
    }

    public void delete(Key key) {
        if (!contains(key))
            return; // replace with sequential search?
        int i = hash(key);
        while (!key.equals(keys[i]))
            i = (i + 1) % M;
        keys[i] = null;
        vals[i] = null;
        i = (i + 1) % M;
        while (keys[i] != null) {
            Key keyToRedo = keys[i];
            Value valToRedo = vals[i];
            keys[i] = null;
            vals[i] = null;
            N--;
            put(keyToRedo, valToRedo);
            i = (i + 1) % M;
        }
        N--;
        if (N > 0 && N == M / 8)
            resize(M / 2);
    }

    private void resize(int capacity) {
        LinearProbingST<Key, Value> t;
        t = new LinearProbingST<Key, Value>(capacity);
        for (int i = 0; i < M; i++) {
            if (keys[i] != null) {
                t.put(keys[i], vals[i]);
            }
        }
        keys = t.keys;
        vals = t.vals;
        M = t.M;

    }
    public int searchMiss(){
        int probes = 1;
        for (int i = StdRandom.uniform(0, M); keys[i] != null; i = (i + 1) % M){
            probes+=1;
        }
        return probes;
    }
    public float avgSearchMiss(){
        float total= 0;
        float runs = 100000;
        for(int i = 0; i<runs; i++){
            total+=searchMiss();

        }
        return total/runs;
    }
    public float percentFull(){
        return ((float) N/ (float) M);
    }

    public static void main(String[] args) {
        int N =30;
        LinearProbingST<Integer, String> test;
        test = new LinearProbingST<Integer, String>(N);
        for(int i=0;i<N/2; i++ ){
            test.put(StdRandom.uniform(0,1000000), "hi");
        }
        System.out.println(test.keys());
      
        System.out.println(test.avgSearchMiss());
        

    }

}
