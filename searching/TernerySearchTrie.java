public class TernerySearchTrie<Value> {
    
    private Node root;

    private class Node {
        char c;
        Node left, mid, right;
        Value val;
    }
    public Value get(String key) {
        Node x = get(root, key, 0);
        if(x.val == null) return null;
        return x.val;
    }

    private Node get(Node x, String key, int d) {
        if (x == null) return null;
        
        // proceed in ternery search fashion
        char current = key.charAt(d);
        if(x.c < current) return get(x.left, key, d);

        else if (x.c > current) return get(x.right, key, d);
        // only increment digit if character is found
        // subtract 1 from key length because next else will take care of end of key
        else if (d < key.length() - 1) return get(x.mid, key, d + 1);
        
        else return x;
        
    }

    public void put(String key, Value val) {
        root = put(root, key, val, 0);
    }

    private Node put(Node x, String key, Value val, int d) {
        char current = key.charAt(d);
        // if x is null we must add new nodes until end of key is reached
        if (x == null) {
            x = new Node();
            x.c = current;
        }
        if (current < x.c) return put(x.left, key, val, d);
        else if (current > x.c) return put(x.right, key, val, d);

        else if (d < key.length() - 1) return put(x.mid, key, val, d + 1);
        // current == x.c and d == key.
        else x.val = val;
        return x;
    }
}
