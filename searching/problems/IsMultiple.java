package searching.problems;
/*
Takes as input a string of binary and an integer. Returns true if the 
binary is a multile of that integer, false if it is not.
 */
public class IsMultiple {
    
    public int[][] dfa;

    public IsMultiple(int x) {
        // create dfa with x rows and 2 columns
        dfa = new int[x][2];
        dfa[0][0] = 0;
        dfa[0][1] = 1;
        for (int i = 1; i < x; i++) {
            for (int j = 0; j <= 1; j ++) {
                // change in state is q(i+1) = 2q(i) + b where b is the new bit
                dfa[i][j] = (2 * i + j) % x;
            }
        }
        
    }

    public Boolean run(String s) {
        int state = 0;
        // simulate operation of DFA on string
        for(int i = 0, j = 0; i < s.length(); i++) {
            // converts from ascii value to integer
            int bit = s.charAt(i) - '0';
            state = dfa[state][bit];
        }
        if (state == 0) return true;
        else return false;
    }

    public static void main(String[] args) {
        IsMultiple test = new IsMultiple(5);
        String x = "1110001001"; // 905
        System.out.println(test.run(x));
        
    }
    
    }




