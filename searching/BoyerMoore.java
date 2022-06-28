package searching;
/*
The constructor in this substring search algorithm builds a table giving the rightmost 
occurrence in the pattern of each possible character. The search method scans from right to
left in the pattern, skipping to align any character causing a mismatch with its rightmost 
occurrence in the pattern.
*/
public class BoyerMoore {
    private int right[];
    private String pat;

    BoyerMoore(String pat) {
        this.pat = pat;
        int M = pat.length();
        // assuming extended ascii alphabet
        int R = 256;
        right = new int[R];
        // compute table of every char in the patterns right-most appearence
        for (int c = 0; c < R; c++) {
            // -1 for chars not in pattern
            right[c] = -1;
        }
        for (int j = 0; j < M; j++) {
            // right most index for chars in pattern
            right[pat.charAt(j)] = j; 
        }
    }

    public int search(String txt) {
        int N = txt.length();
        int M = pat.length();
        // amount to shift text pointer by on char mismatch
        int skip;
        for( int i = 0; i <= N - M; i++ ) {
            // does the pattern match the text at position i?
            skip = 0;
            
            for( int j = M - 1; j >=0; j++) {
                // handle mismatch
                if(pat.charAt(j) != txt.charAt(i + j)) {
                    // if txt.charAt(i + j) is not in pat skip will be j + 1
                    // if txt.charAt(i + j) is in pat it will line up to rightmost occurence
                    skip = j - right[txt.charAt(i + j)];
                    if (skip < 1) skip = 1;
                    break;
                }
            }
            // found match (return starting index of match)
            if (skip == 0) return i;
        }
        // if no match return -1
        return -1;

    }
    
}
