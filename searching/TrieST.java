package searching;
import java.util.LinkedList;
import java.util.Queue;

// R-Trie for storing strings (which is why we don;t need Key generic)
// each node implicitly represents one character (except root) and 
// has R links, where R is size of alphabet

public class TrieST<Value> {
    // here alphabet is extended ascii (8 bits)
    private static int R = 256;
    private Node root;

    private static class Node {
        // must be object because java doesn't let you create array of generics (Value)
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

    public void put(String key, Value val) {
        root = put(root, key, val, 0);
    }

    private Node put(Node x, String key, Value val, int d) {
        // if x is null we must add new nodes until reaching end of key
        if( x == null) x = new Node();
        if (d == key.length()) {
            x.val = val;
            return x;
        }
        // use dth character in key to identify subtrie in which to continue
        char c = key.charAt(d);
        x.next[c] = put(x.next[c], key, val, d + 1);
        // will ultimately return root
        return x;

    }
    public Iterable<String> keys() {
        return keysWithPrefix("");
    }
    public Iterable<String> keysWithPrefix(String pre) {
        Queue<String> q = new LinkedList<String>();
        collect(get(root, pre, 0), pre, q);
        return q;
    }
    public void collect(Node x, String pre, Queue<String> q) {
        // collect all keys by adding chars to pre and recursively using collect
        if ( x == null) return;
        // if we have reached an actual key, enque it (key will be equal to pre)
        if (x.val != null) q.add(pre);
        // continue to search through subtrie until there are no more nodes
        for (char c = 0; c < R; c++) {
            collect(x.next[c], pre + c, q);
        }
    }
    public Iterable<String> keysThatMatch(String pat)
{
   Queue<String> q = new LinkedList<String>();
   collect(root, "", pat, q);
   return q;
}
 public void collect(Node x, String pre, String pat, Queue<String> q)
{
    int d = pre.length();
    if (x == null) return;
    if (d == pat.length() && x.val != null) q.add(pre);
    if (d == pat.length()) return;
    char next = pat.charAt(d);
   for (char c = 0; c < R; c++)
      if (next == '.' || next == c)
         collect(x.next[c], pre + c, pat, q);
}

public String longestPrefixOf(String s)
{
   int length = search(root, s, 0, 0);
   return s.substring(0, length);
}
 private int search(Node x, String s, int d, int length)
{
   if (x == null) return length;
   if (x.val != null) length = d;
   if (d == s.length()) return length;
   char c = s.charAt(d);
   return search(x.next[c], s, d+1, length);
}

  

    public static void main(String[] args) {
        TrieST<Integer> test = new TrieST<Integer>();
        test.put("she", 7);
        test.put("sells", 5);
        test.put("shore", 1);
        test.put("by", 20);
        System.out.println("keys with prefix \"sh\":" + test.keysWithPrefix("sh") + "\n");
        System.out.println("keys with prefix \"she\":" +test.longestPrefixOf("she") + "\n");

    }


}

