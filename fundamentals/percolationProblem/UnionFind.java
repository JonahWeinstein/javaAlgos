package fundamentals.percolationProblem;

public class UnionFind{
    public int[] id;
    public int[] sz;

    public UnionFind(int N){
        id=new int[N];
        sz=new int[N];
        for (int i=0; i<N; i++){
            id[i]=i;
            sz[i]=1;
        }
    }

    private int root(int i){
        while(i != id[i]){
            id[i]=id[id[i]];
            i = id[i];
        }
        return i;
    }
    public boolean connected(int p, int q){
        return root(p)==root(q);
    }
    public void union(int p, int q){
        int i=root(p);
        int j =root(q);
        if (sz[i] < sz[j]){
            id[i]=j;
            sz[j]+=sz[i];
        }
        else{
            id[j]=i;
            sz[i]+=sz[j];
        }}
    public static void main(String[] args){
        UnionFind x= new UnionFind(10);
        x.union(0,1);
        x.union(1,2);
        x.union(2,7);
        x.union(7,9);
        System.out.println(x.connected(0,9));
        for(int i=0; i<x.id.length; i++){
            System.out.println(x.id[i]+" ");
        }

        }
}
