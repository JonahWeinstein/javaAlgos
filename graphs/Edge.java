package graphs;

public class Edge implements Comparable<Edge> {
    // two vertices
    private final int v;
    private final int w;
    // weight of the edge object
    private final double weight;

    public Edge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;

    }

    public double weight() {
        return weight;
    }
    // either and other are used for adding edges to vertex adj lists
    // in edge weighted graph implementation
    public int either() {
        return v;
    }
    public int other(int vertex) {
        if(v == vertex) return w;
        if(w == vertex) return v;
        else throw new RuntimeException("Inconsistent Edge");

    }

    public int compareTo(Edge that) {
        if (this.weight() < that.weight()) return -1;
        else if (this.weight() > that.weight()) return 1;
        else return 0;
    }
    public String toString()
   {  return String.format("%d-%d %.5f", v, w, weight);  }

}
