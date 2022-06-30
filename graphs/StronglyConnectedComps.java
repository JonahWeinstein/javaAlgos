package graphs;

/**
 @code To find strong components, it does a depth-first search in the reverse digraph to produce 
 a vertex order (reverse postorder of that search) for use in a depth-first search of the 
 given digraph.

 */



public class StronglyConnectedComps {
    private boolean[] marked;
    // strongly connected component identifiers
    private int[] id;
    private int count;

    public StronglyConnectedComps(Digraph G) {
        marked = new boolean[G.V()];
        // there are between 1 and V strongly connected components in any Digraph G
        id = new int[G.V()];
        // process reverse of digraph because we are interested in reverse postorder of reversed graph
        DepthFirstOrder order = new DepthFirstOrder(G.reverse());
        for(int s: order.reversePost()) {
            if(!marked[s]) {
                dfs(G, s);
                // this will increase # of SCCs by 1
                count++;
            }
        }

    }

    private void dfs(Digraph G, int v) {
        marked[v] = true;
        // count only is incremented for each new SCC
        id[v] = count;
        for (int w: G.adj(v)) {
            if(!marked[w]) {
                dfs(G, w);
            }
        }
    }
    // returns true if v and w are in the same strongly connected componenet
    public boolean ConnectedComponents(int v, int w) {
        return id[v] == id[w];
    }
    // returns id of component which contains v
    public int id(int v) {
        return id[v];
    }
    // returns number of strongly connected components
    public int count() {
        return count;
    }
}
