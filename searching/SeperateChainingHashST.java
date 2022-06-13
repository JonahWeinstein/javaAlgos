package src.searching;

public class SeperateChainingHashST<Key, Value> {
    private int M = 5;  //number of indices (number of seperate chains)
    private Node[] st = new Node[M];

    private static class Node{
        private Object key;
        private Object val;
        private Node next;
    
    public Node(Object key, Object val, Node next){
        this.key= key;
        this.val=val;
        this.next = next;
    }
}
    private int hash(Key key){
        return (key.hashCode() & 0x7FFFFFFF) % M;
    }

    private Value get(Key key){
        int i = hash(key);
        for(Node x = st[i]; x!=null; x=x.next){
            if(key.equals(x.key))return (Value) x.val;
        }
        return null;
    }
    public void put(Key key, Value val){
        int i = hash(key);
        for(Node x = st[i]; x!=null; x=x.next){
            if(key.equals(x.key)){
                x.val=val;
                return;
            }
        }
        st[i]=new Node(key, val, st[i]); //add new Node to front that links to old node (st[i])

    }
    public static void main(String[] args) {
        SeperateChainingHashST<String, Integer> test = new SeperateChainingHashST<String, Integer>();
        test.put("hi", 3);
        test.put("hello", 3);
        test.put("idea", 10);
        test.put("god", 20);
        test.put("hi", 8);
        System.out.println(test.get("hi"));
        
    }

}
