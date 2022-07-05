package graphs;

/**
 {@code TopologicalSort} first checks that Digraph G is a DAG (Directed Acyclic Graph) using {@link DirectedCycle}.java 
to find any cycles. If no cycles are found it then uses {@link DepthFirstOrder} to find the reversePost 
order which is the topological sorted order
 */
public class TopologicalSort {
    // topological order
    private Iterable<Integer> order;

    public TopologicalSort(Digraph G) {
        DirectedCycle cycleFinder = new DirectedCycle(G);
        if (!cycleFinder.hasCycle()) {
            DepthFirstOrder dfs = new DepthFirstOrder(G);
            order = dfs.reversePost();
        } 
    }

    public Iterable<Integer> order()
    { return order; }
    // returns true if no cycles are found in G
    public boolean isDAG() {
        return order != null;
    }

    
}
