package graphs;

import java.util.Stack;
import tools.In;
import tools.StdOut;
// determines if there is a path from source s to destination v and returns all vertices 
// in that path (in order) 


public class DepthFirstPaths {
    // has this vertex been marked?
    private boolean[] marked;
    // last vertex on known path to this vertex
    private int[] edgeTo;
    // source vertex
    private final int s;

    public DepthFirstPaths(Graph G, int s) {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        dfs(G, s);

    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        for (int x : G.adj(v)) {
            if (!marked[x]) {
                edgeTo[x] = v;
                dfs(G, x);
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

    public static void main(String[] args)
{
   Graph G = new Graph(new In(args[0]));
   int s = Integer.parseInt(args[1]);
   DepthFirstPaths search = new DepthFirstPaths(G, s);
   for (int v = 0; v < G.V(); v++)
   {
      StdOut.print(s + " to " + v + ": ");
      if (search.hasPathTo(v))
         for (int x : search.pathTo(v))
            if (x == s) StdOut.print(x);
            else StdOut.print("-" + x);
      StdOut.println();

   }
}
}
