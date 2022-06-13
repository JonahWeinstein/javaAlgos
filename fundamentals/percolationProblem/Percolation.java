package src.fundamentals.percolationProblem;

public class Percolation {
    private int[][] grid;
    UnionFind UF;
    private int openSites;

    public Percolation(int N){
        grid=new int[N][N];
        UF = new UnionFind(N*N+2);
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                grid[i][j]=0;
        openSites=0;
            }
        }
    }
    private boolean isOpen(int p, int q){
        if(p<grid.length && q< grid.length && p>=0 && q>=0) {
            return grid[p][q]==1;
        }
        else{
            return false;
        }
    }
    private void open(int p, int q){
        if(grid[p][q] == 0){
            grid[p][q]=1;
            int index=p*grid.length+q+1;
            if(p==0){
                UF.union(0,index);
            }
            if(p==grid.length-1){
                UF.union(UF.id.length-1,index);
            }
            if(isOpen(p-1,q)){
                UF.union(index,index-grid.length);
            }
            if(isOpen(p+1,q)){
                UF.union(index,index+grid.length);
            }
            if(isOpen(p,q-1)){
                UF.union(index,index-1);
            }
            if(isOpen(p,q+1)){
                UF.union(index,index+1);
            }
            openSites+=1;
        }

    }
    public boolean isFull(int p, int q){
        int index=p*grid.length+q+1;
        return UF.connected(0,index);   
    }
    public boolean percolates(){
        if(UF.connected(0,UF.id.length-1)){
            return true;
        }
        else{
            return false;
        }
    }
    public int numberOfOpenSites(){
        return openSites;
    }
    
    
    

    public static void main(String[] args){
        Percolation test=new Percolation(4);
        test.open(0,1);
        test.open(1,2);
        test.open(2,2);
        test.open(2,1);
        test.open(3,1);
        for(int i=0; i < test.UF.id.length; i++){
            System.out.println(test.UF.id[i]);   
        }
        System.out.println(test.percolates());

        /* for(int i=0; i < test.grid.length; i++) {
            for(int j=0; j<test.grid[i].length; j++){
                System.out.println(test.grid[i][j]); 
        }
    } */
    }
}
    

