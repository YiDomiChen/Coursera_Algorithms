
public class UFWeightedQuickUnion extends UFQuickUnion {
	
	private int[] sz;
	
	public UFWeightedQuickUnion(int n) {
		super(n);
		id = new int[n];
		for(int i = 0; i < n; i++) {
			sz[i] = 1;
		}
		
	}
	
	
	@Override
	public void union(int p, int q) {
		int i = root(p);
		int j = root(q);
		if(i == j) return;
		if(sz[i] < sz[j]) {
			id[i] = j;
			sz[j] += sz[i];
		}
		else {
			id[j] = i;
			sz[i] += sz[j];
		}
	}
}
