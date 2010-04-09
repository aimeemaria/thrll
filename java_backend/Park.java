// this class implements the Park type
import java.util.ArrayList;

public class Park {
	private String parkName = null;
	private double admission;
	private double capacity;
	private double cost;
	private int hours;  //the number of hours the park operates in a day.
	private double salary;  //the average salary of park employees
		
	// internal list of land objects
	private ArrayList<Land> landObjs = null;
	
	private void setParkAttributeValues(double admission, double capacity, double cost){
		this.admission = admission;
		this.capacity = capacity;
		this.cost = cost;
	}
	
	public Park(){
		setParkAttributeValues(100, 10000, 10);
		hours = 12;  //default value
		salary = 15.0; //default value
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
	
	public ArrayList<Land> getLandObjs() {
		return landObjs;
	}

	public void addLand(Land land) throws ThrillException{
		if(landObjs.contains(land))
			ThrillException.RedefinitionException(land.getLandName());
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
	
	public int getHours(){
		return hours;
	}
	
	public void setHours(int hours){
		this.hours = hours;
	}
	
	public double getSalary(){
		return salary;
	}
	
	public void setSalary(double salary){
		this.salary = salary;
	}
}
