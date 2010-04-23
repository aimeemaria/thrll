/*
 * Park.java
 * Team THRLL
 * This file represents Theme Park.
 */

import java.util.ArrayList;
import java.util.Random;

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
		Random generator = new Random();  //Random Number Generator
		for(int i =0;i<c.getSize();i++){
			p = c.people.get(i);
			int num_Lands = LandObjs.size();
			int decide;
			//Person p will be simulated for the entire Duration
			while (p.getTick()>0){
				System.out.println("Tick = " +p.getTick());
				//Choose land to visit
				int land_choice = generator.nextInt(num_Lands);
				//Choose Attraction, Store, or Restaurant
				int type_choice = generator.nextInt(3);
				//An ArrayList of lands that do not contain elements of this choice
				ArrayList<Integer> emptyLands = new ArrayList<Integer>();
				
				if (type_choice == 0) {  //choose attraction
					ArrayList<LandElement> attractions = getAllType(LandObjs.get(land_choice), 'a');
					if (attractions.size()==0){
						emptyLands.add(land_choice);
						if (emptyLands.size() != LandObjs.size()){
							do{
								land_choice = generator.nextInt(num_Lands);	
							}
							while(!emptyLands.contains(land_choice));
						}
						else
							p.leavePark();
						}
					else{
						int choice = generator.nextInt(attractions.size());
						LandElement attraction = attractions.get(i);
						//Account for time to move to location
						if(LandObjs.indexOf(attraction.getLand()) == p.getLocation() + 1)
							p.decreaseTick(1);
						else
							p.decreaseTick(2);
						
						if(attraction.canEnter(p)){
							attraction.enter(p);
							System.out.println("Entered Attraction");
							p.decreaseTick(attraction.getTimeNeeded());
							attraction.exit(p);
						}
					}	
				}
					
				else if (type_choice ==1 ){ //choose store
					ArrayList<LandElement> stores = getAllType(LandObjs.get(land_choice), 's');
					if (stores.size()==0){
						emptyLands.add(land_choice);
						if (emptyLands.size() != LandObjs.size()){
							do{
								land_choice = generator.nextInt(num_Lands);	
							}
							while(!emptyLands.contains(land_choice));
						}
						else
							p.leavePark();
						}
					else{
						int choice = generator.nextInt(stores.size());
						LandElement store = stores.get(i);
						//Account for time to move to location
						if(LandObjs.indexOf(store.getLand()) == p.getLocation() + 1)
								p.decreaseTick(1);
						else
							p.decreaseTick(2);
						
						if(store.canEnter(p)){
							store.enter(p);
							p.decreaseTick(store.getTimeNeeded());
							store.exit(p);
						}
					}	
					
				}
				else { //choose restaurant
					ArrayList<LandElement> restaurants = getAllType(LandObjs.get(land_choice), 'r');
					if (restaurants.size()==0){
						emptyLands.add(land_choice);
						if (emptyLands.size() != LandObjs.size()){
							do{
								land_choice = generator.nextInt(num_Lands);	
							}
							while(!emptyLands.contains(land_choice));
						}
						else
							p.leavePark();
						}
					else{
						int choice = generator.nextInt(restaurants.size());
						LandElement restaurant = restaurants.get(i);
						//Account for time to move to location
						if(LandObjs.indexOf(restaurant.getLand()) == p.getLocation() + 1)
							p.decreaseTick(1);
						else
							p.decreaseTick(2);
						
						if(restaurant.canEnter(p)){
							restaurant.enter(p);
							p.decreaseTick(restaurant.getTimeNeeded());
							restaurant.exit(p);
						}
					}	
				}
				
				if (p.getEnergyLevel() <= 1){
					p.leavePark();
				}
			}
		//ADD Get Revenue for each store and restaurant	
			
		}
		return result-cost;
	}
	private ArrayList<LandElement> getAllType(Land l, char type){
		ArrayList<LandElement> land_contents = l.getContents();
		ArrayList<LandElement> land_objects = new ArrayList<LandElement>();
		for (int i = 0; i < land_contents.size(); i++)
		{
			if (land_contents.get(i).getType() == type){
				land_objects.add(land_contents.get(i));
			}
		}
		return land_objects;
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