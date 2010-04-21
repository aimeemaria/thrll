/*
 * Park.java
 * Team THRLL
 * This file represents Theme Park.
 */

import java.util.ArrayList;

public class Park 
{
	private String ParkName;
	private double admission;
	private double capacity;
	private double cost;
	public int hours;  		//the number of hours the park operates in a day.
	public double salary;  	//the salary of park employees
	
	// internal list of land objects
	private ArrayList<Land> LandObjs = new ArrayList<Land>();
	
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

	public double calculateRevenue(Crowd c, Duration d)
	{
		double result = 0;
		
		// write the revenue logic here. This function should be able to 
		// access other objects as well. we need to access tha land objects
		// via landObjs arraylist. Each class should have a copy of its children
		// objects
		
		//for each land
		//get cost of operation (include employees)
		double cost=0;
		for(int i = 0 ; i < LandObjs.size();i++){
			cost += LandObjs.get(i).getcost(hours,salary);//returns operation cost for all elements in the land
		}
		//assume the crowd enters as soon as park opens
		
		c.createpeople();
		//ToDo:
		//run simulation of decisions for each person and update revenue
		Person p;
		for(int i =0;i<c.getSize();i++){
			p = c.people.get(i);
			int decide;
			//Person p will be simulated for the entire Duration
			
			
		}
		return result-cost;
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