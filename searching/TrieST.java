package searching;

// R-Trie for storing strings (which is why we don;t need Key generic)
// each node implicitly represents one character (except root) and 
// has R links, where R is size of alphabet

public class TrieST<Value> {
    // here alphabet is extended ascii (8 bits)
    private static int R = 256;
    private Node root;

    private static class Node {
        private Object val;
        private Node[] next = new Node[R];
        
    }

    public int size() {
        return size(root);
    }
    // lazy size method, eager method would keep track of size as ST changes
    private int size(Node x) {
        if(x == null) return 0;
        
        int count = 0;
        if ( x.val != null) count++;
        for(char c = 0; c < R; c++) {
            c += size(x.next[c]);
        }
        return count;

    }

    public Value get(String key) {
        Node x = get(root, key, 0);
        if (x.val == null) return null;
        return (Value) x.val;
    }

    private Node get(Node x, String key, int d) {
        if (x == null) return null;
        // if we are at last char of string return current node
        if (d == key.length()) return x;
        // continue search in x.next
        char c = key.charAt(d);
        return get(x.next[c], key, d + 1);

    }
}   
