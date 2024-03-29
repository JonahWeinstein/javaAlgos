package searching;

import java.util.Stack;
import graphs.*;

public class NFA {
    // match transitions
    private char[] re;
    // epsilon (empty string) transitions
    private Digraph G;
    // number of states
    private int M;

    public NFA(String regex) {
        // create the NFA for the given regular expression
        // we are only interested in epsilon transitions

        // stack for indices of operation characters ( left parentheses/or operator)
        // indecies are used because they are state values in the NFA and G represents
        // the
        // empty string transitions between them
        Stack<Integer> ops = new Stack<Integer>();
        re = regex.toCharArray();
        M = re.length;
        G = new Digraph(M + 1); // one extra vertex for accept state
        for (int i = 0; i < M; i++) {

            int lp = i;

            if (re[i] == '(' || re[i] == '|') {
                ops.push(i);
            } else if (re[i] == ')') {
                //
                int or = ops.pop();

                if (re[or] == '|') {

                    lp = ops.pop();
                    G.addEdge(lp, or + 1);
                    G.addEdge(or, i);
                }
            }

        }

    }

    public boolean recognizes(String txt) { // Does the NFA recognize txt?
        Bag<Integer> pc = new Bag<Integer>();
        DirectedDFS dfs = new DirectedDFS(G, 0);
        for (int v = 0; v < G.V(); v++)
            if (dfs.marked(v))
                pc.add(v);
        for (int i = 0; i < txt.length(); i++) { // Compute possible NFA states for txt[i+1].
            Bag<Integer> match = new Bag<Integer>();
            for (int v : pc)
                if (v < M)
                    if (re[v] == txt.charAt(i) || re[v] == '.')
                        match.add(v + 1);
            pc = new Bag<Integer>();
            dfs = new DirectedDFS(G, match);
            for (int v = 0; v < G.V(); v++)
                if (dfs.marked(v))
                    pc.add(v);
        }
        for (int v : pc)
            if (v == M)
                return true;
        return false;
    }

}
