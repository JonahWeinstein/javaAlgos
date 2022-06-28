package graphs;

    
public class DepthFirstSearch
{
   private boolean[] marked;
   private int count = 0;
 public DepthFirstSearch(Graph G, int s)
   {
      marked = new boolean[G.V()];
      // assumes connected graph, otherwise would need a collection of all vertices to iterate 
      // through and run dfs for any that are unmarked
      dfs(G, s);
   }
 private void dfs(Graph G, int v)
   {
      marked[v] = true;
      count++;
      for (int w : G.adj(v))
         if (!marked[w]) dfs(G, w);
   }
 public boolean marked(int w)
   {  return marked[w];  }
 public int count()
   {  return count;  }
}

