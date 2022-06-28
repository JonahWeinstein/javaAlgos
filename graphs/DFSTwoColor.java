package graphs;
// checks if a graph is two-colorable (bipartite)


public class DFSTwoColor {
    private boolean[] marked;
    // only two colors so can be stored implicitly as boolean
    private boolean[] color;
    private boolean isTwoColorable = true;

    public DFSTwoColor(Graph G) {
        marked = new boolean[G.V()];
        color = new boolean[G.V()];
        for (int s = 0; s < G.V(); s++) {
            if(!marked[s]) {
                dfs(G, s);
            }
        }
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        for (int w: G.adj(v)) {
            if(!marked[w]) {
                // make adjacent vertex (w) opposite color of current (v)
                color[w] = !color[v];
                dfs(G, w);
            }
            // if w is already marked and with the same color as v the graph is not two colorable
            else if (color[w] == color[v]) isTwoColorable = false;

        }
    }
    public boolean isBipartite() {
        return isTwoColorable;
    }


}
