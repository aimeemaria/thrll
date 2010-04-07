
public class Restaurant {
	private String restaurantName;
	private double cost;
	private int capacity;
	private int employees;
	private double spendLevel;
	private int energyIncrease;
	private Land land;
	
	public Restaurant(){
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public double getCost() {
		return cost;
	}

	public int getCapacity() {
		return capacity;
	}

	public int getEmployees() {
		return employees;
	}

	public double getSpendLevel() {
		return spendLevel;
	}

	public int getEnergyIncrease() {
		return energyIncrease;
	}

	public Land getLand() {
		return land;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public void setEmployees(int employees) {
		this.employees = employees;
	}

	public void setSpendLevel(double spendLevel) {
		this.spendLevel = spendLevel;
	}

	public void setEnergyIncrease(int energyIncrease) {
		this.energyIncrease = energyIncrease;
	}

	public void setLand(Land land) {
		this.land = land;
	}

}
