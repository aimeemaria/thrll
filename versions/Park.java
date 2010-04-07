// this class implements the Park type
import java.util.ArrayList;

public class Park {
	private String parkName = null;
	private double admission;
	private int capacity;
	private double cost;
	
	// internal list of land objects
	private ArrayList<Land> landObjs = new ArrayList<Land>();

	public Park(){
		admission = 20.0;
		capacity = 1000;
		cost = 10000;
	}
	
	public String getParkName() {
		return parkName;
	}


	public double getAdmission() {
		return admission;
	}


	public int getCapacity() {
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


	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}


	public void setCost(double cost) {
		this.cost = cost;
	}


	public void setLandObjs(ArrayList<Land> landObjs) {
		this.landObjs = landObjs;
	}


	public ArrayList<Land> getLandObjs() {
		return landObjs;
	}

	public void addLand(Land land) throws ThrillException{
		if(landObjs.contains(land))
			ThrillException.redefinitionException(land.getLandName());
		landObjs.add(land);
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
