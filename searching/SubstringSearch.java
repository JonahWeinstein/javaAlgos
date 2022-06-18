// brute force approach (worst case O(NM) time)
public class SubstringSearch {
    
    public static int search(String txt, String pat) {
        int M = pat.length();
        int N = txt.length();
        for(int i = 0; i < N; i++) {
            int j;
            for(j = 0; j < M; j++) {
                if(txt.charAt(i+ j) != pat.charAt(j)) {
                    break;
                }
            }
            // search hit
            if( j == M) return i;

        }
        //  search miss
        return N;
    }
}
