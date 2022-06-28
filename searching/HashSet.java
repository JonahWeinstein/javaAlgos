package searching;



public class HashSet<Key> {
    int dummy = 0;
    LinearProbingST<Key, Integer> set;

    HashSet(){
        set = new LinearProbingST<Key, Integer>();
    }

    void add(Key key){
        set.put(key,dummy);

    }
    void delete(Key key){
        set.delete(key);
    }
    boolean contains(Key key){
        return set.contains(key);
    }
    int size(){
        return set.size();
    }

    
    



    
}
