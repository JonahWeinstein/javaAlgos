package graphs;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.LinkedList;
/**
 * This implementation of Prim’s algorithm uses a priority queue to hold crossing edges, 
 * a vertex-indexed arrays to mark tree vertices, and a queue to hold MST edges. 
 * This implementation is a lazy approach where we leave ineligible edges in the priority queue.

 */

 // See Sedgewick 4th edition page 619 for eager implementation

public class PrimsMSTLazy {
    // keeps track of which vertices are in the MST
    private boolean[] marked;
    // edges in the MST
    private Queue<Edge> mst;
    // min priority queue of crossing edges and inelligable edges (edges between two MST vertices) 
    // since this is lazy implementation
    private PriorityQueue<Edge> pq;

    // constructor preprocesses graph and craetes MST allowing for client queries
    public PrimsMSTLazy(EdgeWeightedGraph G) {
       
        marked = new boolean[G.V()];
        mst = new LinkedList<Edge>();
        pq = new PriorityQueue<Edge>();
        // assumes a connected graph because otherwise pq could be empty after visiting v
        visit(G, 0);
        while(!pq.isEmpty()) {
            Edge e = pq.remove();
            // get both vertices of min cut edge
            int v = e.either();
            int w = e.other(v);
            // check if edge is inelligible, if so go to next smallest edge
            if(marked[v] && marked[w]) continue;
            // add edge to the mst
            mst.add(e);
            // visit whichever vertex is unmarked to add it to tree and add its edges to pq
            if(!marked[v]) visit(G, v);
            if(!marked[w]) visit(G, w);

        }

    }

    private void visit(EdgeWeightedGraph G, int v) {
        // mark v as added to MST
        marked[v] = true;
        for(Edge e: G.adj(v)) {
            // if this edge points to a vertex not already in the tree then add it to minPQ
            if(!marked[e.other(v)]) pq.add(e);

        }
    }
    // return all edges in the mst
    public Iterable<Edge> edges() {
        return mst;
    }
    // return the total size (weight) of mst
    // public double weight() {}



}
