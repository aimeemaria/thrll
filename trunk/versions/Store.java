// this class implements the Store type
public class Store {
	private String storeName;	
	private double cost;
	private int capacity;	
	private int employees;
	private int spendLevel;
	private Land land;

	public Store(){		
	}

	public String getStoreName() {
		return storeName;
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

	public int getSpendLevel() {
		return spendLevel;
	}

	public Land getLand() {
		return land;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
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

	public void setSpendLevel(int spendLevel) {
		this.spendLevel = spendLevel;
	}

	public void setLand(Land land) {
		this.land = land;
	}
}
