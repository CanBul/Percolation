package percolation;
import java.util.Random;
import java.util.Scanner;

public class PercolationStats {	 


   public static void main(String[] args) {
	    
		double mean = 0;
    	int i=0;
    	
    	Scanner userinput = new Scanner(System.in);
    	System.out.print("Please enter n:");
    	int length = userinput.nextInt();
    	
    	System.out.print("Please enter trial count:"); 	    	
        int trialCount = userinput.nextInt();
        userinput.close();
    	
    	while (i<trialCount) {
    		Percolation newPerc = new Percolation(length);
    		
    		while (!newPerc.percolates()) {
    			Random r = new Random();
    			int rand1 = r.ints(1, length+1).findFirst().getAsInt();
    			int rand2 = r.ints(1, length+1).findFirst().getAsInt();    			    			
    			
    			newPerc.open(rand1, rand2);
    			
    		}    		
    		
    		double perc = (double)newPerc.numberOfOpenSites() / (length*length);
    		
    		
    		
			mean += perc;
			
    		
    		i += 1;
    	}
    	System.out.println(mean/ trialCount);
    	
    }

}
