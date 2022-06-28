package graphs;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Stack;

// shortest path algorith when all edge weights are 1
// using breadth first search
public class BFSShortestPath { 
    
    private boolean[] marked;
    private int[] edgeTo;
    // source vertex
    private final int s;

    

    public BFSShortestPath(Graph G, int s) {
        this.s =s;
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
       search(G, s);

    }
    private void search(Graph G, int x) {
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(x);
        marked[x] = true;
        while(!queue.isEmpty()) {
            int v = queue.remove();
            for(int w: G.adj(v)) {
                if(!marked[w]) {
                    // we will not visit w again so this is shortest path to v
                    edgeTo[w] = v;
                    marked[w] = true;
                    queue.add(w);
                }
            }
        }
        
    }
  // does s have a path to v?
  public boolean hasPathTo(int v) {
    // if v is marked it must be connected to s
    return marked[v];
}

    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<Integer>();
        for (int x = v; x != s; x = edgeTo[x] ){
            path.push(x);
        }
        path.push(s);
        return path;
    }

}
