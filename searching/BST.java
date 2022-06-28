package searching;
import java.util.Queue;
import java.util.LinkedList;


public class BST<Key extends Comparable<Key>, Value> {
    private Node root; // root of the BST

    private class Node {
        private Key key;
        private Value val;
        private Node left, right; // links to subtrees
        private int N; // number of nodes in the subtree that has this node as root

        public Node(Key key, Value val, int N) {
            this.key = key;
            this.val = val;
            this.N = N;
        }
    }
    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null)
            return 0;
        return x.N;
    }

    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node x, Key key) {
        if (x == null)
            return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            return get(x.left, key);
        if (cmp > 0)
            return get(x.right, key);
        else
            return x.val;
    }

    public void put(Key key, Value val) {
        root = put(root, key, val);
    }

    private Node put(Node x, Key key, Value val) {
        if (x == null) { // if we are at bottom of tree
            return new Node(key, val, 1);
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            x.left = put(x.left, key, val);
        else if (cmp > 0)
            x.right = put(x.right, key, val);
        else {
            x.val = val;
            x.N = size(x.left) + size(x.right) + 1; // recalculate subtree sizes as you move up the tree
        }
        return x; // executed on the way back up the tree
    }
    public Key floor(Key key){
        Node x=floor(key,root);
        if(x==null)return null;
        return x.key;
    }
    private Node floor(Key key, Node x){
        int cmp = key.compareTo(x.key);
        if(cmp==0) return x;
        if(cmp<0) return floor(key, x.left);
        Node t=floor(key,x.right);
        if(t==null)return x;
        else return t;
    }

    public Key ceiling(Key key){
        Node x=ceiling(key,root);
        if(x==null)return null;
        return x.key;
    }
    private Node ceiling(Key key, Node x){
        int cmp= key.compareTo(x.key);
        if(cmp==0)return x;
        if(cmp>0)return ceiling(key,x.right);
        Node t=ceiling(key, x.left);
        if(t==null)return x;
        else return t;
    }
    public Key min(){
        Node x=min(root);
        return x.key;
    }
    public Key select(int k){
        Node x=select(root,k);
        return x.key;
    }

    private Node select(Node x, int k){
        if(x==null)return null;
        int t=size(x.left);
        if(t>k)return select(x.left,k);
        else if(t<k)return select(x.right, k-t-1);
        else return x;
    }
    public int rank(Key key){
        return rank(key,root);
    }

    private int rank(Key key,Node x){
        int cmp = key.compareTo(x.key);
        if(cmp<0)return rank(key, x.left);
        else if(cmp>0)return size(x.left) + 1 + rank(key, x.right);
        else return size(x);

    }
    public Node min(Node x){
        if(x.left==null)return x;
        else return min(x.left);
    }

    public Key max(){
        Node x = max(root);
        return x.key;
    }
    public Node max(Node x){
        if(x.right==null)return x;
        else return min(x.right);
    }

    public void deleteMin(){
        root=deleteMin(root);
    }
    private Node deleteMin(Node x){
        if(x.left==null)return x.right;
        x.left=deleteMin(x.left);
        x.N=size(x.left)+size(x.right)+1;
        return x;
    }
    public void delete(Key key){
        root=delete(root, key);
    }
    private Node delete(Node x, Key key){
        if(x==null)return null;
        int cmp=key.compareTo(x.key);
        if(cmp>0) x.right = delete(x.right, key);
        if(cmp<0) x.left = delete(x.left,key);

        else{
            if(x.left==null)return x.right;
            if(x.right==null) return x.left;
            Node t=x;
            x=min(t.right);
            x.right=deleteMin(t.right);
            x.left=t.left;
            return x;
        }
        x.N= 1 + size(x.left) + size(x.right);
        return x;
    }

    public void print(Node x){
        if(x==null) return;
        print(x.left);
        System.out.println(x.key);
        print(x.right);
    }
    public Iterable<Key> keys(){
        return keys(min(), max());
    }
    public Iterable<Key> keys(Key lo, Key hi){
        Queue<Key> queue = new LinkedList<Key>();  //queue is an interface 
        keys(root, queue, lo, hi);
        return queue;
    }

    private void keys(Node x, Queue<Key> queue, Key lo, Key hi){
        if(x==null)return;
        int cmplo = x.key.compareTo(lo);
        int cmphi = x.key.compareTo(hi);
        if(cmplo<0) keys(x.right, queue,lo,hi);
        if(cmplo>=0 && cmphi<=0)queue.add(x.key);
        if(cmphi>0) keys(x.right, queue, lo, hi);

    }

    public static void main(String[] args){
        BST<String, Integer> test =new BST<String, Integer>();
        test.put("hi",1);
        test.put("hello",4);
        test.put("seven",7);
        test.put("hi",3);
        System.out.println(test.get("hello"));
    }
}
