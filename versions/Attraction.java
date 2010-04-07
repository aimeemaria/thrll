
public class Attraction {
	private int capacity;
	private double cost;
	private int energyLost;
	private int employees;
	private int thrillLevel;
	
	public Attraction(){
		cost = 100;
		capacity = 100;
		energyLost = 1;
		employees = 5;
		thrillLevel = 5;
	}

	public int getCapacity() {
		return capacity;
	}

	public double getCost() {
		return cost;
	}

	public int getEnergyLost() {
		return energyLost;
	}

	public int getEmployees() {
		return employees;
	}

	public int getThrillLevel() {
		return thrillLevel;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public void setEnergyLost(int energyLost) {
		this.energyLost = energyLost;
	}

	public void setEmployees(int employees) {
		this.employees = employees;
	}

	public void setThrillLevel(int thrillLevel) {
		this.thrillLevel = thrillLevel;
	}
}
