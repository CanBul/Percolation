package percolation;

public class Percolation {
	
	private boolean[] table;
	private int length, opensites, virtualUpIndex, virtualDownIndex;
	private ModifiedQuickUnion Un;
	

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
    	length = n;
    	Un = new ModifiedQuickUnion(n*n+2);
    	virtualUpIndex = n*n;
    	virtualDownIndex = n*n+1;
    	
    	table = new boolean[n*n];
    	
    	for (int i=0; i<n*n; i++) {
    		
    		table[i] = false;    		
    	}
    	
    }
    private int getindex(int row, int col) {
    	return ((row-1)*length)+(col-1);
    }
    
    private void leftunion(int row,int col) {
    	int index = getindex(row, col);
    	if (isOpen(row, col-1)) {Un.union(index, index-1); } //union with the left
    	
    }
    
    private void rightunion(int row,int col) {
    	int index = getindex(row, col);
    	if (isOpen(row, col+1)) {Un.union(index, index+1); } //union with the right
    	
    }
    
    private void upunion(int row, int col) {
    	int index = getindex(row, col);
    	if (isOpen(row-1, col)) {Un.union(index, index-length); }
    	
    }
    
    private void downunion(int row, int col) {
    	int index = getindex(row, col);
    	if (isOpen(row+1, col)) {Un.union(index, index+length); }
    	
    }
    

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
    	
    	
    	int index = getindex(row, col);
    	
    	if (table[index]) { return; } 
		
    	opensites += 1;    	    	
    	table[index] = true;   	    	
    	
			
		if (row == 1 ) { 
			
			Un.union(index, virtualUpIndex); //with virtual up
			downunion(row,col);
			
		}  else if (row == length) { 
			
			Un.union(index, virtualDownIndex); //with virtual down
			upunion(row, col);
			
		} 	else { 
			upunion(row, col);
			downunion(row,col);
		}
		
		if (col==length) {
			leftunion(row,col);
								
		} else if (col == 1) { //leftmost
			rightunion(row,col);				
			
		} else { //else
			leftunion(row,col);
			rightunion(row,col);
			
		}   	   	

    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
    	int index = getindex(row, col);
    	return table[index];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
    	int index = getindex(row, col);
    	
    	return Un.connected(virtualUpIndex, index);
    	
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
    	return opensites;
    }

    // does the system percolate?
    public boolean percolates() {
    	return Un.connected(virtualUpIndex, virtualDownIndex);
    }

    
}
