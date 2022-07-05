package graphs;

public class EdgeWeightedGraph {
    // number of vertices
    private final int V;
    //  number of edges
    private int E;
    // adjacency lists
    private Bag<Edge>[] adj;

    // creates an empty v vertex graph
    public EdgeWeightedGraph(int V) {
        this.V = V;
        this.E = 0;
        
        // create size V array of Bag<Edge>
        adj = (Bag<Edge>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<Edge>();
        }
    }
    public int V() { return V;}

    public int E() {return E; }

    public void addEdge(Edge e) {
        // get vertices from edge e
        int v = e.either();
        int w = e.other(v);
        // add edge to both vertices adjacency lists
        adj[v].add(e);
        adj[w].add(e);
        // increment total number of edges
        E++;  
    }
    public Iterable<Edge> adj(int v)
   {  return adj[v];  }

    // returns all edges in the graph
    public Iterable<Edge> edges() {
        Bag<Edge> b = new Bag<Edge>();
        for(int v = 0; v < V; v++) {
            for( Edge e: adj[v]) {
                // this is so we only add each edge once 
                if(e.other(v) > v) b.add(e);
            }
        }
        return b;
    }


}
