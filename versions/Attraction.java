
public class Attraction {
	private String attractionName;
	private int capacity;
	private double cost;
	private int energyLost;
	private int employees;
	private int thrillLevel;	
	private Land land = null;
	
	public Attraction(){
	}

	public String getAttractionName() {
		return attractionName;
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

	public Land getLand() {
		return land;
	}

	public void setAttractionName(String attractionName) {
		this.attractionName = attractionName;
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

	public void setLand(Land land) {
		this.land = land;
	}
}
