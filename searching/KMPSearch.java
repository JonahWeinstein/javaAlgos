/*
The constructor in this implementation of the Knuth-Morris-Pratt algorithm for substring search
builds a DFA from a pattern string, to support a search() method that can find the pattern in a 
given text string. This program does the same job as the brute-force method, but it runs faster 
for patterns that are self-repetitive.
*/

public class KMPSearch {
    private String pat;
    // deterministic finite-state automoton (DFA) for keeping track fo where to backtrack 
    // pattern pointer on mismatch
    private int[][] dfa;

    // constructor whcih builds DFA based on given pattern
    public KMPSearch(String pat) {
        this.pat = pat;
        int M = pat.length();
        // assuming extended ascii alphabet (8-bit char)
        int R = 256;
        dfa = new int[R][M];
        // Sets dfa[pat.charAt(j)][j] to j+1 for first match case
        dfa[pat.charAt(0)][0] = 1;
        for(int X = 0, j = 1; j < M; j++) {
            // compute dfa[][j]
            for(int c = 0; c < R; c++) {
                // copy mismatch cases
                dfa[c][j] = dfa[c][X];
            }
             // set match case
             dfa[pat.charAt(j)][j] = j +1;
            // update restart state
            X = dfa[pat.charAt(j)][X];
        }
    }

    public int search(String txt) {
        // simulate operation of DFA on text
        int i, j, N = txt.length(), M = pat.length();
        // loop will terminate either when pattern is found or end of txt is reached
        for(i = 0, j = 0; i < N && j < M; i++){
            j = dfa[txt.charAt(i)][j];
        }
        // found pattern! j hit end of pattern so return start index 
        if (j == M) return i - M;
        // pattern wasn't found
        else return -1;
    }



}