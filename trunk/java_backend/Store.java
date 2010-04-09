/**Store.java
 * Team THRLL
 * This file represents a store in the theme park.
 */

/**
 * @author Michael
 *
 */
public class Store implements LandElement {
	
	private String StoreName; 
	private double sales;  //the amount of money made in one day due to sales.
	private int employees;  //the number of employees required to operate the store
	private int capacity;  //the capacity of the store
	private double cost;  //the cost to build the store
	private int spendLevel;  //the spending level associated with the store
	private int currentAttendance;  //the current number of people in the store
	private double price;  //the average purchase price of the store
	private Land land;
	
	public Store(){
		//set default values
		StoreName = "Main Store";
		capacity = 100;
		cost = 0;
		spendLevel = 5;
		currentAttendance = 0;
		price = 10.00;
		employees = 2;
		sales = 0;		
	}
	
	/**Calculates the revenue generated by the store during the day
	 * @param park The park the store is located in
	 * @return The revenue from the day.
	 */
	public double calculateRevenue(Park park) {
		double revenue = sales - park.hours * employees * park.salary;
		return revenue;
	}

	/**Determines if a person can enter the store.
	 * @param p The person trying to enter the store
	 * @return A boolean determining if the person can enter the store
	 */
	public boolean canEnter(Person p) {
		if (p.getSpendingCapacity() >= this.spendLevel && currentAttendance < capacity)
			return true;
		else
			return false;
	}

	/**Allows a person to enter the store.  Assumes a sale will occur once they enter the store.
	 * @param p The person entering the store.
	 * 
	 */
	public void enter(Person p) {
		if (canEnter(p)){
			currentAttendance++;
			sales += price;
			}
	}
	/**Allows a person to exit the store.
	 * @param p The person exiting the store.
	 * 
	 */
	public void exit(Person p) {
		currentAttendance--;
	}

	/*
	 * @return The name of the store 
	 */
	public String getStoreName() 
	{ 
		return StoreName; 
	} 
	/*
	 *  @param The name of the store
	 */
	public void setStoreName(String StoreName) 
	{ 
		this.StoreName = StoreName; 
	} 
	
	/**
	 * @return The employees required to operate the store
	 */
	public int getEmployees() {
		return employees;
	}

	/**
	 * @param Employees The employees required to operate the stores
	 */
	public void setEmployees(int employees) {
		this.employees = employees;
	}

	/**
	 * @return The capacity of the store
	 */
	public int getCapacity() {
		return capacity;
	}

	/**
	 * @param capacity The capacity of the store
	 */
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	/**
	 * @return The cost to construct the store
	 */
	public double getCost() {
		return cost;
	}

	/**
	 * @param cost The cost to construct the store
	 */
	public void setCost(double cost) {
		this.cost = cost;
	}

	/**
	 * @return The spending level associated with the store
	 */
	public int getSpendLevel() {
		return spendLevel;
	}

	/**
	 * @param spendLevel The spending level associated with the store.
	 */
	public void setSpendLevel(int spendLevel) {
		this.spendLevel = spendLevel;
	}

	/**
	 * @return The average purchase price of the store
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price The average purchase price of the store
	 */
	public void setPrice(int price) {
		this.price = price;
	}

	/**
	 * @return The current number of people in the store (excluding employees)
	 */
	public int getCurrentAttendance() {
		return currentAttendance;
	}
	
	public void setLand(Land land)
	{ 
		this.land = land; 
	} 
	  
	public Land getLand()
	{ 
		return land; 
	} 
}
