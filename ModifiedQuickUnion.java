package percolation;

public class ModifiedQuickUnion {

		static int[] id, sz;
			
			public ModifiedQuickUnion(int N) {
				id = new int[N];
				sz = new int[N];
				for (int i=0; i<N; i++ ) {
					id[i] = i;
					sz[i] = 1;
				}
			}
			
			private int root(int i) {
				while (id[i]!=i) {
					id[i] = id[id[i]]; //move parent leave to one above
					i = id[i];
					}
				return i;
			}
			
			public boolean connected(int x, int y) {
				return root(x) == root(y);
			}
			
			public void union(int p, int q) {
				int x = root(p);
				int y = root(q);
				if (x==y) {return;}
				
				if (sz[x]>sz[y]) {  //add small list to bigger list
					id[y] = x;
					sz[x] += sz[y]; 
				} else {
					id[x] = y;
					sz[y] +=sz[x];
				}
			}
}

