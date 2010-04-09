
import java.util.ArrayList;

public class Crowd {
	private String crowdName;
	private int size;
	private int energyLevel;
	private int thrillLevel;
	private double spendingCapacity;
	ArrayList<Person> people;
	
	private void setattributes(int size,int energy,int thrill,double spend){
		this.size = size;
		energyLevel = energy;
		thrillLevel = thrill;
		spendingCapacity = spend;
	}
	
	public Crowd(){
		people=new ArrayList<Person>();
		//crowdName = "";
		setattributes(1000,5,5,100.);//default values?
		for(int i = 0; i<size;i++){
			//TODO:set attributes of each created person in a normal distribution of crowd attributes
			people.add(new Person());
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
	public void setCrowdName(String crowdName) {
		this.crowdName = crowdName;
	}

	public void setSpendingCapacity(double spendingCapacity) {
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
