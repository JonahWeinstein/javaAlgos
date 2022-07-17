package compression;

import java.util.PriorityQueue;

public class Huffman {
    // size of alphabet
    private static int R = 256;

    // Huffman trie node
    private static class Node implements Comparable<Node> {
        private char ch;
        // nodes won't change after initialization
        private final Node left, right;
        // used for expansion
        private int freq;

        Node(char ch, int freq, Node left, Node right) {
            this.ch = ch;
            this.freq = freq;
            this.left = left;
            this.right = right;

        }

        public boolean isLeaf() {
            return (left == null && right == null);
        }

        // since Node implements Comparable<Node>
        public int compareTo(Node that) {
            return this.freq - that.freq;
        }
    }

    public static void expand() {
        Node root = readTrie();
        int N = BinaryStdIn.readInt();
        for (int i = 0; i < N; i++) {
            // expand ith codeword
            Node x = root;
            while (!x.isLeaf()) {
                // if x = 1 move right, otherwise move left
                if (BinaryStdIn.readBoolean()) {
                    x = x.right;
                } else
                    x = x.left;
            }
            BinaryStdOut.write(x.ch);
        }
        BinaryStdOut.close();
    }

    // build the actual Huffman encoding trie
    private static Node buildTrie(int[] freq) {
        // initialize priority queue with singleton trees
        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        for (char c = 0; c < R; c++) {
            if (freq[c] > 0) {
                pq.add(new Node(c, freq[c], null, null));

            }
        }
        // build the trie from the bottom up by merging lowest frequency trees
        while (pq.size() > 1) {
            // merge the two smallest trees
            Node x = pq.remove();
            Node y = pq.remove();
            // create parent whose frequency is sum of children
            Node parent = new Node('\0', x.freq + y.freq, x, y);
            pq.add(parent);
        }
        // this will be the parent of the 2 highest frequency nodes (and root of the
        // trie)
        return pq.remove();
    }

    // building an ecoding table (symbol table) from a prefix free code trie
    private static String[] buildCode(Node root) {
        // make a lookup table for the trie
        String[] st = new String[R];
        buildCode(st, root, "");
        return st;
    }

    private static void buildCode(String[] st, Node x, String s) {
        // recursively make a lookup table from the trie
        if (x.isLeaf()) {
            st[x.ch] = s;
            return;
        }
        buildCode(st, x.left, s + '0');
        buildCode(st, x.right, s + '1');

    }

    // writes the encoding trie as a bitstream
    private static void writeTrie(Node x) {
        if (x.isLeaf()) {
            BinaryStdOut.write(true);
            BinaryStdOut.write(x.ch);
            return;
        }
        // write a zero bit to indicate internal node
        BinaryStdOut.write(false);
        writeTrie(x.left);
        writeTrie(x.right);

    }

    // reconstruct the trie from its bitstream representation
    private static Node readTrie() {
        // leaf node
        if (BinaryStdIn.readBoolean()) {
            return new Node(BinaryStdIn.readChar(), 0, null, null);
        }
        return new Node('\0', 0, readTrie(), readTrie());
    }

    // actual compression method
    public static void compress() {
        // Read input.
        String s = BinaryStdIn.readString();
        char[] input = s.toCharArray();
        // Tabulate frequency counts.
        int[] freq = new int[R];
        for (int i = 0; i < input.length; i++)
            freq[input[i]]++;
        // Build Huffman code trie.
        Node root = buildTrie(freq);
        // Build code table (recursive).
        String[] st = buildCode(root);

        // Print trie for decoder (recursive).
        writeTrie(root);
        // Print number of chars.
        BinaryStdOut.write(input.length);
        // Use Huffman code to encode input.
        for (int i = 0; i < input.length; i++) {
            String code = st[input[i]];
            for (int j = 0; j < code.length(); j++)
                if (code.charAt(j) == '1')
                    BinaryStdOut.write(true);
                else
                    BinaryStdOut.write(false);
        }
        BinaryStdOut.close();
    }
    // driver code
    public static void main(String[] args) {
        compress();
       
        
    }

}
