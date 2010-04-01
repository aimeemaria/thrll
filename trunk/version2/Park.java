// this class implements the Park type
import java.util.ArrayList;

public class Park {
	String parkName = null;
	double admission;
	double capacity;
	double cost;
	
	// internal list of land objects
	private ArrayList<Land> landObjs = null;
	
	private void setParkAttributeValues(double admission, double capacity, double cost){
		this.admission = admission;
		this.capacity = capacity;
		this.cost = cost;
	}
	
	public Park(){
		setParkAttributeValues(100, 10000, 10);
	}

	public String getParkName() {
		return parkName;
	}

	public double getAdmission() {
		return admission;
	}

	public double getCapacity() {
		return capacity;
	}

	public double getCost() {
		return cost;
	}

	public void setParkName(String parkName) {
		this.parkName = parkName;
	}

	public void setAdmission(double admission) {
		this.admission = admission;
	}

	public void setCapacity(double capacity) {
		this.capacity = capacity;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}	
	
	public double calculateRevenue(Crowd c, Duration d){
		double result = 0;
		
		// write the revenue logic here. This function should be able to 
		// access other objects as well. we need to access tha land objects
		// via landObjs arraylist. Each class should have a copy of its children
		// objects
		
		return result;
	}
}
