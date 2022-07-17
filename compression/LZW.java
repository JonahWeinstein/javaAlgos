package compression;
import searching.TrieST;

/**
 * This implementation of {@code Lempel-Ziv-Welch} data compression uses 8-bit input bytes and 12-bit codewords 
 * and is appropriate for arbitrary large files. Its codewords for the small example are similar to those 
 * discussed in the text: the single-character codewords have a leading 0; the others start at 100.

 */
public class LZW {
    // size of alphabet
    private static final int R = 256;
    // codeword width
    private static final int W = 12;
    // number of possible codewords (12 bit codeword means 2^12 possible)
    private static int L = 4096;

    public static void compress() {
        String input = BinaryStdIn.readString();
        TrieST<Integer> st = new TrieST<Integer>();
        // fill ST with single char values
        for (int i = 0; i < R; i++) {
            st.put("" + (char) i, i);
        }
        // R id codeword for EOF
        int code = R + 1;
        while(input.length() > 0) {
            // find max prefix match
            String s = st.longestPrefixOf(input);
            // print encoding of s
            BinaryStdOut.write(st.get(s), W);
            int t = s.length();
            
            if( t < input.length() && code < L) {
                // add s to symbol table
                // must do it like this because input is from stdin
                st.put(input.substring(0, t + 1), code++);  
            }   
            // cut off already read input
            input = input.substring(t);
        }
        // write EOF
        BinaryStdOut.write(R);
        BinaryStdOut.close();
    }
    
}
