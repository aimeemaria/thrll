// this class file implements the Crowd type
public class Crowd {
	private String crowdName = null;
	private double spendingCapacity = 0;
	private int size = 0;
	private int energyLevel = 0;
	private int thrillLevel = 0;
	
	public Crowd(){
		energyLevel = 5;
		size = 1000;
		spendingCapacity = 100.0;
		thrillLevel = 5;		
	}

	public String getCrowdName() {
		return crowdName;
	}

	public double getSpendingCapacity() {
		return spendingCapacity;
	}

	public int getSize() {
		return size;
	}

	public int getEnergyLevel() {
		return energyLevel;
	}

	public int getThrillLevel() {
		return thrillLevel;
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
