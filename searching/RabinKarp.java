/* 
This substring search algorithm is based on hashing. It computes a hash value for the pattern
in the constructor, then searches through the text looking for a hash match.

Monte-Carlo version (no explicit match checking) runs in guaranteed linear time with a tiny 
probability of being incorrect

Las Vegas version (with explicit match checking) is guanranteed to be right with a 
tiny probability it runs in NM time (otherwise it will be linear)
 */

public class RabinKarp {
    // only needed for Las Vegas version
    // private String pat;

    // pattern hash
    private long patHash;
    // len of pat
    private int M;
    // a large prime
    private int Q;
    // size of alphabet
    private int R = 256;
    // R^(M-1) % Q
    private long RM;

    // uses Horner's method (polynomial rolling hashing) to get uniformly distributed hash values
    private long hash(String key, int M)
    {  // Compute hash for key[0..M-1].
       long h = 0;
       for (int j = 0; j < M; j++)
          h = (R * h + key.charAt(j)) % Q;
       return h;
    } 

    public RabinKarp(String pat) {
        // only needed for Las Vegas version
        // this.pat = pat;
        M = pat.length();
        // long prime (should be random)
        Q = 100000007;
        // compute RM = R^(M-1) % Q for use in removing leading digit (when incrementing search index)
        RM = 1;
        for(int i = 1; i <= M-1; i++) {
            RM = (R * RM) % Q;
        }
        // lastly compute pattern hash
        patHash = hash(pat, M);

    }
    public Boolean check(int i) {
        // monte carlo style, if using las vegas version check pat with txt[i]...txt[i+(M-1)]
        return true;
    }

    // returns index of start of paattern match in text
    public int search(String txt) {

        int N = txt.length();
        long txtHash = hash(txt, M);
        // check if first M characters match pattern (check method will only be used in las vegas version
        // it will always return true if otherwise)
        if(txtHash == patHash && check(0)) return 0;
        // if not, move through txt[M] to txt[N-1] checking for a match
        // i will be end of each M char substring
        for(int i = M; i < N; i++) {
            // Remove leading digit, add trailing digit, check for match.
            // Q is added to make sure it stays positive after removing leading digit txt[i-M]
            // we mod Q after each operation to avoid overflow
            txtHash = (txtHash + Q - RM*txt.charAt(i - M) % Q) % Q;

            txtHash = (txtHash * R + txt.charAt(i)) % Q;
            // if there is a match return starting index of the match  in the txt 
            if (txtHash == patHash && check(i - M + 1)) return (i - M + 1);

        }
        // if no match return -1
        return -1;


    }




}
