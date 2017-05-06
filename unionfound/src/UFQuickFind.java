/**
 * 
 * 
 * 
 * 
 * 
 * 
 * 
*/


public class UFQuickFind implements UnionFind {
	private int[] id;
	
	public UFQuickFind(int n) {
		// TODO Auto-generated constructor stub
		id = new int[n];
		for(int i = 0; i < n; i++) {
			id[i] = i;
		}
	}
	
	@Override
	public void union(int p, int q) {
		// TODO Auto-generated method stub
		int pid = id[p];
		int qid = id[q];
		for(int i = 0; i < id.length; i++) {
			if(id[i] == pid) id[i] = qid;	//check all entries with id[p] to id[q] (at most 2N+2 array accesses)
		}
	}

	@Override
	public boolean connected(int p, int q) {
		return id[p] == id[q];	//check whether p and q are in the same component (2 array accesses)
	}
	
	
	
	
	
	
}
