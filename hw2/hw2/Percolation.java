package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int N;
    private int numberOfOpenSites;
    private int[][] grid;
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF ufTop;

    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N){
        if (N <= 0) {
            throw new IllegalArgumentException("N should be greater than 0");
        }
        this.N = N;
        numberOfOpenSites = 0;
        grid = new int[N][N];
        for(int i = 0; i<N; i++){
            for (int j =0; j< N; j++){
                grid[i][j] = -1;
            }
        }
        uf = new WeightedQuickUnionUF(N*N + 2);
        ufTop = new WeightedQuickUnionUF(N*N + 1);
    }

    private void UnionNeighbor(int row, int col){
        int index = xyTo1D(row, col);
        // Connect with virtual top if row == 0
        if (row == 0){
            uf.union(0,index);
            ufTop.union(0,index);
        }
        // Connect with virtual bottom if row == N-1
        if (row == N-1){
            uf.union(index, N*N+1);
        }
        // Connect with above
        if (row > 0 && isOpen(row-1,col)) {
            uf.union(xyTo1D(row-1,col),index);
            ufTop.union(xyTo1D(row-1,col),index);
        }
        // Connect with left
        if (col > 0 && isOpen(row, col-1)) {
            uf.union(xyTo1D(row,col-1),index);
            ufTop.union(xyTo1D(row,col-1),index);
        }
        // Connect with below
        if (row < N-1 && isOpen(row+1, col)) {
            uf.union(xyTo1D(row+1,col),index);
            ufTop.union(xyTo1D(row+1,col),index);
        }
        // Connect with right
        if (col < N-1 && isOpen(row, col+1)) {
            uf.union(xyTo1D(row,col+1),index);
            ufTop.union(xyTo1D(row,col+1),index);
        }
    }

    //validate index values
    private void validate(int row, int col){
        if (row < 0 || col < 0 || row >= N || col >= N){
            throw new IndexOutOfBoundsException();
        }
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        validate(row, col);
        if(! isOpen(row, col)){
            grid[row][col] = xyTo1D(row,col);
            numberOfOpenSites++;
        }
        UnionNeighbor(row,col);

    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validate(row, col);
        return grid[row][col] >= 0;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validate(row, col);
        int index = xyTo1D(row,col);
        return ufTop.connected(0,index);
    }

    // number of open sites
    public int numberOfOpenSites(){
        return numberOfOpenSites;
    }

    /* Convert 2-D coordinates to 1-D index */
    private int xyTo1D(int r, int c){
        return r*N + c;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.connected(0, N*N+1);
    }

    // use for unit testing (not required, but keep this here for the autograder)
    public static void main(String[] args) {
        Percolation p = new Percolation(5);
        p.open(3,4);
        p.open(2,4);
        System.out.println("Expected: true, Actual: " + p.isOpen(3,4));
        p.open(2,2);
        p.open(2,3);
        p.open(0,2);
        p.open(1,2);
        System.out.println("Expected: true, Actual: " + p.isFull(2,2));
        System.out.println("Percolate: " + p.percolates());
        p.open(4,4);
        System.out.println("Now percolate: " + p.percolates());
    }
}
