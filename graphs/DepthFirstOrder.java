package graphs;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Stack;

/**
 * {@code DepthFirstOrder } class enables clients to iterate through the vertices in various orders defined by 
 * depth-first search. This ability is very useful in the development of advanced 
 * digraph-processing algorithms, because the recursive nature of the search enables us to 
 * prove properties of the computation.

 * Topological sort is represented by reverse post order

 */

public class DepthFirstOrder {
    
    private boolean[] marked;
    // we use three data structures to keep track of the 3 different orders for given Digraph G
    // pre-order
    private Queue<Integer> pre;
    // post order
    private Queue<Integer> post;
    // reverse post order
    private Stack<Integer> reversePost;

    public DepthFirstOrder(Digraph G) {
        pre = new LinkedList<Integer>();
        post = new LinkedList<Integer>();
        reversePost = new Stack<Integer>();
        marked = new boolean[G.V()];

        for(int v = 0; v < G.V(); v++) {
            if(!marked[v]) dfs(G, v);
        }
    }

    private void dfs(Digraph G, int v) {
        marked[v] = true;
        // for pre-order vertices are added before the recursive calls
        pre.add(v);
        for(int w: G.adj(v)) {
            if(!marked[w]) {
                dfs(G, w);
            }
        }
        // post order vertices are added after all recursive calls on unmarked adjacent 
        // vertices are done
        post.add(v);
        // same with reversePost but this uses a stack (will have reverse order)
        reversePost.push(v);

    } 
    public Iterable<Integer> pre() 
    { return pre(); }
    public Iterable<Integer> post()
    { return post();}
    public Iterable<Integer> reversePost()
    { return reversePost();}


}
