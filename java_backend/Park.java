/*
 * Park.java
 * Team THRLL
 * This file represents Theme Park.
 */

import java.util.ArrayList;

<<<<<<< .mine
public class Park 
{
	private String ParkName;
	private double admission;
	private double capacity;
	private double cost;
	public int hours;  		//the number of hours the park operates in a day.
	public double salary;  	//the salary of park employees
	
=======
public class Park {
	private String parkName = null;
	private double admission;
	private double capacity;
	private double cost;
	private int hours;  //the number of hours the park operates in a day.
	private double salary;  //the average salary of park employees
		
>>>>>>> .r47
	// internal list of land objects
	private ArrayList<Land> LandObjs = null;
	
	/*	Constructor
	 *	to set default values of the members
	 */
	public Park()
	{
		//Set default Values
		admission = 100;
		capacity = 1000;
		cost = 100000;
		hours = 12;  	
		salary = 15.0;
	}

	public String getParkName() 
	{
		return ParkName;
	}

	public double getAdmission() 
	{
		return admission;
	}

	public double getCapacity() 
	{
		return capacity;
	}

	public double getCost() 
	{
		return cost;
	}

	public void setParkName(String ParkName) 
	{
		this.ParkName = ParkName;
	}

	public void setAdmission(double admission) 
	{
		this.admission = admission;
	}

	public void setCapacity(double capacity) 
	{
		this.capacity = capacity;
	}

	public void setCost(double cost) 
	{
		this.cost = cost;
	}
<<<<<<< .mine

	public double calculateRevenue(Crowd c, Duration d)
	{
=======
	
	public ArrayList<Land> getLandObjs() {
		return landObjs;
	}

	public void addLand(Land land) throws ThrillException{
		if(landObjs.contains(land))
			ThrillException.RedefinitionException(land.getLandName());
		landObjs.add(land);
	}
	
	public double calculateRevenue(Crowd c, Duration d){
>>>>>>> .r47
		double result = 0;
		
		// write the revenue logic here. This function should be able to 
		// access other objects as well. we need to access tha land objects
		// via landObjs arraylist. Each class should have a copy of its children
		// objects
		
		return result;
	}
	
	public int getHours()
	{
		return hours;					
	}
	
	public void setHours(int hours)
	{
		this.hours = hours;
	}
	
	public double getSalary()
	{
		return salary;
	}
	
	public void setSalary(double salary)
	{
		this.salary = salary;
	}
	
	public void add(Land t)
	{
		LandObjs.add(t);
	}
	
}