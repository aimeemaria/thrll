import java.util.Random;

abstract class Distribution {
	
	int generatedArray[];
	public abstract int[] generateNumbers(int meanValue, int size);
}

public class NormalDistribution extends Distribution {
	
	public int[] generateNumbers(int meanValue, int size) {
	
		generatedArray = new int[size];
		int i, value;
		double rand, standardDev; 
		Random generator = new Random();
		
		//generate numbers with mean = 0.0 and std deviation 1.0 
		for(i=0;i<size;i++) {
			rand = generator.nextGaussian();
			
			//find how much the standard deviation must be
			if(meanValue < 10) {
				standardDev = (10-meanValue)/3;
			}else {
				standardDev = (20 - meanValue)/3;
			}
		
			//convert to the std dev and mean that we want. 
			value = (int) (rand * standardDev + meanValue);
			
	 		//remove things that are above or below the specified range. 
			if(value < 0 || value > 20) {
				i--;
				continue; //continue as though this iteration never happened.
			}
    		generatedArray[i] = value;
		}
		return generatedArray;
	}
}