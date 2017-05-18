package demo;

public class UFQuickUnion implements UnionFind {
	
	protected int[] id;
	
	public UFQuickUnion(int n) {
		// TODO Auto-generated constructor stub
		id = new int[n];
		for(int i = 0; i < n; i++) {
			id[i] = i;
		}
	}
	
	protected int root(int i) {
		while(id[i] != i) {
			i = id[i];
		}
		
		return i;	//chase parent pointers until reach root (depth of i array accesses)
	}
	
	@Override
	public void union(int p, int q) {
		// TODO Auto-generated method stub
		int i = root(p);
		int j = root(q);
		id[i] = j;	//change root of p to point to root of q (depth of p and q array accesses)
	}

	@Override
	public boolean connected(int p, int q) {
		// TODO Auto-generated method stub
		return root(p) == root(q);	//check if p and q have same root (depth of p and q array accesses)
	}

}
