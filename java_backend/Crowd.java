
import java.util.ArrayList;

public class Crowd {
	String crowdName = null;
	private int size;
	private int energyLevel;
	private int thrillLevel;
	private int spendingCapacity;
	ArrayList<Person> people;
	
	private void setattributes(int size,int energy,int thrill,int spend){
		this.size = size;
		energyLevel = energy;
		thrillLevel = thrill;
		spendingCapacity = spend;
	}
	
	//default values
	public Crowd(){
		setattributes(1000,10,10,10);
	}
	/*
	 * This might change a little if I think I want to include other distributions.
	 * Right now I am just taking a normal distribution.
	 */
	public void createpeople(){
		
		people=new ArrayList<Person>();
		
		NormalDistribution n = new NormalDistribution();
		
		//get the normal distribution in here. 
		int energy[] = n.generateNumbers(energyLevel, size);
		int thrill[] = n.generateNumbers(thrillLevel, size);
		int spend[] = n.generateNumbers(spendingCapacity, size);
		for(int i = 0; i < size; i++){
			people.add(new Person(energy[i],thrill[i],spend[i],i));
		}
	}
	public int getEnergyLevel(){
		return energyLevel;
	}
	
	public int geThrillLevel(){
		return thrillLevel;
	}
	
	public double getSpendingCapacity(){
		return spendingCapacity;
	}
	public int getSize(){
		return size;
	}
	
	public void setCrowdName(String crowdName) {
		this.crowdName = crowdName;
	}

	public void setSpendingCapacity(int spendingCapacity) {
		this.spendingCapacity = spendingCapacity;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public void setEnergyLevel(int energyLevel) {
		this.energyLevel = energyLevel;
	}

	public void setThrillLevel(int thrillLevel) {
		this.thrillLevel = thrillLevel;
	}
	

}
