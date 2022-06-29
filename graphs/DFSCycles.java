package graphs;

// Is undirected graph G acyclic? Assumes no self loops or parrallel edges
// the idea is to check if a marked node is the one that we just came from, if not there is a cycle
public class DFSCycles {
    private boolean[] marked;
    private boolean hasCycle;

    public DFSCycles(Graph G) {
        marked = new boolean[G.V()];
        // iterate through every vertex
        for (int s = 0; s < G.V(); s++) {
            if(!marked[s]) {
                dfs(G, s, s);
            }
        }
    }

    private void dfs(Graph G, int curr, int prev) {
        marked[curr] = true;
        for (int w: G.adj(curr)) {
            if(!marked[w]) {
                // if unmarked, run recursively with curr becoming the new prev
                dfs(G, w, curr);
            }
            // if w is marked it is either the previous node or there is a cycle
            else if (w != prev) hasCycle = true;
        }

    }
    
    public boolean hasCycle() {
        return hasCycle;
    }

}
