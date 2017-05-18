import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
  private boolean[][] grids;  //  site in specific location
  private WeightedQuickUnionUF ufTop;   //  union-find from the top
  private WeightedQuickUnionUF ufBoth;  //  union-find from both sides
  private int size;   //  size of the site
  private int count;  //  number of open sites

  //  check whether index is in bounds.
  private boolean checkIndexValid(int index) {
    return (index >= 1 || index <= size);
  }

  /**
   * Create n-by-n grid, with all sites blocked.
   * 
   * @param n Size of grid
   */
  public Percolation(int n) {
    if (n < 1) {
      throw new IllegalArgumentException();
    }

    ufTop = new WeightedQuickUnionUF(n * n + 2); // additional 2 for virtual top and bottom site
    ufBoth = new WeightedQuickUnionUF(n * n + 2);
    grids = new boolean[n][n];
    size = n;
    count = 0;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        grids[i][j] = false;    //set all sites blocked by default
      }
    }
  }

    
  /**
   * Open site (row, col) if it is not open already
   * 
   * @param row The row index, ranging from 1 to n.
   * @param col The column index, ranging from 1 to n.
   */
  public void open(int row, int col) {
    if (!checkIndexValid(row) || !checkIndexValid(col)) {
      throw new IndexOutOfBoundsException();
    }
    if (grids[row - 1][col - 1]) {
      return;
    }
    grids[row - 1][col - 1] = true;
    count++;
    int currentIdx = (row - 1) * size + col;    //  0 for the virtual top site
    if (row - 1 >= 1 && grids[row - 2][col - 1]) {  //  union the upper site
      int upperIdx = currentIdx - size;
      ufTop.union(currentIdx, upperIdx);
      ufBoth.union(currentIdx, upperIdx);
    }
    if (col - 1 >= 1 && grids[row - 1][col - 2]) {  //  union the left site
      int leftIdx = currentIdx - 1;
      ufTop.union(currentIdx, leftIdx);
      ufBoth.union(currentIdx, leftIdx);
    }
    if (col + 1 <= size && grids[row - 1][col]) {    //  union the right site
      int rightInx = currentIdx + 1;
      ufTop.union(currentIdx, rightInx);
      ufBoth.union(currentIdx, rightInx);
    }
    if (row + 1 <= size && grids[row][col - 1]) {    //  union the lower site
      int lowerIdx = currentIdx + size;
      ufTop.union(currentIdx, lowerIdx);
      ufBoth.union(currentIdx, lowerIdx);
    }
    if (row == 1) { 
      ufTop.union(currentIdx, 0);
      ufBoth.union(currentIdx, 0);
    }
    if (row == size) {
      ufBoth.union(currentIdx, size * size + 1);
    }
  }

  /**
   * Is site (row, col) open?
   * @param row The row index, ranging from 1 to n.
   * @param col The column index, ranging from 1 to n.
   * @return true for open, false for blocked
   */
  public boolean isOpen(int row, int col) {
    if (!checkIndexValid(row) || !checkIndexValid(col)) {
      throw new IndexOutOfBoundsException();
    }

    return grids[row - 1][col - 1];
  }

  /**
   * Is site (row, col) full?
   * @param row The row index, ranging from 1 to n.
   * @param col The column index, ranging from 1 to n.
   * @return true for connected to the open site in the top row, false for not connected 
   */
  public boolean isFull(int row, int col) {
    if (!checkIndexValid(row) || !checkIndexValid(col)) {
      throw new IndexOutOfBoundsException();
    }
    int currentIdx = (row - 1) * size + col;
    
    return ufTop.connected(currentIdx, 0);
  }

  /**
   * Number of open sites.
   * 
   * @return number of open sites
   */
  public int numberOfOpenSites() {
    return count;
  }

  /**
  * Does the system percolate.
  * @return whether it's percolate
  */
  public boolean percolates() {
    return ufBoth.connected(0, size * size + 1);
  }
}