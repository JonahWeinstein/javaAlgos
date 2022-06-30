package graphs;

/**
 * @code  TransitiveClosure address the all-reachability problem for Digraphs.
 * It preprocesses Digraph G into a DirectedDFS matrix which can then be 
 * queried to determined if w is reachable from v for all v and w.
 * Each entry in all array specifies which endpoints are reachable from that vertex 
 */
public class TransitiveClosure {
    private DirectedDFS[] all;

    TransitiveClosure(Digraph G) {
        all = new DirectedDFS[G.V()];
        for(int v = 0; v < G.V(); v++) {
            all[v] = new DirectedDFS(G, v);
        }
    }

    public boolean reachable(int v, int w) {
        // if w is reachable from v then it will be marked in DirectedDFS(G, v)
        return all[v].marked(w);
    }
}
