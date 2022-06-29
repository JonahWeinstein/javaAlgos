package graphs;

import java.util.Stack;

public class DirectedCycle {

    private boolean[] marked;
    private int[] edgeTo;
    // vertices on a cycle (if one exists)
    private Stack<Integer> cycle;
    // keeps tracks of vertices in the call stack
    private boolean[] onStack;

    public DirectedCycle(Digraph G) {
        onStack = new boolean[G.V()];
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        // must iterate through all V to find any cycle
        for(int v = 0; v < G.V(); v++) {
            if(!marked[v]) dfs(G, v);
        }

    }

    private void dfs(Digraph G, int v) {
        onStack[v] = true;
        marked[v] = true;
        for(int w: G.adj(v)) {
            if( this.hasCycle()) return;
            else if(!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);
            }
            // if w is marked check if it is in the call stack (means there is a cycle )
            else if(onStack[w]) {
                cycle = new Stack<Integer>();
                // add all vertices in the cycle to the stack
                for(int x = v; x != w; x = edgeTo[x]) {
                    cycle.push(x);
                } 
                // finally add v and w to the cycle
                cycle.push(v);
                cycle.push(w);
            }

        }
        // vertex will now be removed from stack
        onStack[v] = false;
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<Integer> cycle() {
        return cycle;
    }

    
}
