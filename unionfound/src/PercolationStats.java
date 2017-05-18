import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;


public class PercolationStats {

  private int size; //  size of the site
  private int experiments;  //  number of independent computational experienments
  private double[] results;

  /**
   * perform trials independent experiments on an n-by-n grid.
   * 
   * @param n size of array
   * @param trials number of experiments
   */
  public PercolationStats(int n, int trials) {
    if (n <= 0 || trials <= 0) { 
      throw new IllegalArgumentException(); 
    }
    this.size = n;
    this.experiments = trials;
    this.results = new double[trials];

    for (int i = 0; i < experiments; i++) {
      Percolation perc = new Percolation(size);
      while (!perc.percolates()) {
        int rowIdx = StdRandom.uniform(1, size + 1);
        int colIdx = StdRandom.uniform(1, size + 1);
        if (!perc.isOpen(rowIdx, colIdx)) { 
          perc.open(rowIdx, colIdx);
        }
      }

      double threshold = (double)perc.numberOfOpenSites() / (size * size);
      results[i] = threshold;
    }
  }

  /**
   * sample mean of percolation threshold.
   * 
   * @return mean
   */
  public double mean() {
    return StdStats.mean(results);
  }

  /**
   * sample standard deviation of percolation threshold.
   * 
   * @return standard deviation
   */
  public double stddev() {
    return StdStats.stddev(results);
  }

  /**
   * low endpoint of 95% confidence interval.
   * 
   * @return low confidence interval
   */
  public double confidenceLo() {    
    return StdStats.mean(results) 
            - 1.96 * StdStats.stddev(results) / Math.sqrt(experiments);
  }

  /**
   * high endpoint of 95% confidence interval.
   * 
   * @return high confidence interval
   */
  public double confidenceHi() {
    return StdStats.mean(results) + 1.96 * StdStats.stddev(results) / Math.sqrt(experiments);
  }

  public static void main(String[] args) {
    int n = Integer.parseInt(args[0]);
    int expnum = Integer.parseInt(args[1]);
    PercolationStats percStats = new PercolationStats(n, expnum);
    System.out.println("mean                    = " + percStats.mean());
    System.out.println("stddev                  = " + percStats.stddev());
    System.out.println("95% confidence interval = "  
        + "[" + percStats.confidenceLo() + ", " + percStats.confidenceHi() + "]");

  }
}
