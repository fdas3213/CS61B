package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    private double[] counts;
    private int nExp;
    private static final double confidence_level = 1.96;

    /*perform T independent experiments on an N-by-N grid */
    public PercolationStats(int N, int T, PercolationFactory pf){
        if(T <= 0 || N <= 0){
            throw new IllegalArgumentException("T or N < 0");
        }
        nExp = T;
        counts = new double[T];
        for (int i = 0; i<T; i++){
            Percolation pc = pf.make(N);
            int count = 0;
            while(!pc.percolates()){
                int row = StdRandom.uniform(N);
                int col = StdRandom.uniform(N);
                if (pc.isOpen(row,col)) continue;
                pc.open(row, col);
                count ++;
            }
            counts[i] = (double) count / (N*N);
        }
    }

    public double mean(){
        return StdStats.mean(counts);
    }

    public double stddev(){
        return StdStats.stddev(counts);
    }

    public double confidenceLow(){
        return mean() - confidence_level * stddev() / Math.sqrt(nExp);
    }

    public double confidenceHigh(){
        return mean() + confidence_level * stddev() / Math.sqrt(nExp);
    }

    public static void main(String[] args){
        PercolationFactory pf = new PercolationFactory();
        int size = 10;
        int numExp = 1000;
        PercolationStats ps = new PercolationStats(size, numExp,pf);
        System.out.println("mean: " + ps.mean());
        System.out.println("stddev: " + ps.stddev());
        System.out.println("confidence interval: " + ps.confidenceLow() + ", " + ps.confidenceHigh());
    }

}
