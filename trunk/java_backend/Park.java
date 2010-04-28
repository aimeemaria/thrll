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
		double sales = 0;

		//for each land
		//get cost of operation (include employees)
		double cost=0;
		for(int i = 0 ; i < LandObjs.size();i++){
			cost += LandObjs.get(i).getcost(hours,salary);//returns operation cost for all elements in the land
		}

		double admissionSales = c.getSize() * admission;

		//assume the crowd enters as soon as park opens
		c.createpeople();
		Person p;
		Random generator = new Random();  //Random Number Generator
		for(int j = 48; j >0; j--){
			for(int i =0;i<c.getSize();i++){
				p = c.people.get(i);
				if (p.getTick() == j){

					//After First Tick
					//Exit Current Location
					if (j < 48)
						p.getSpecificLocation().exit(p);

					int num_Lands = LandObjs.size();

					//Choose land to visit
					int land_choice = generator.nextInt(num_Lands);
					//Choose Attraction, Store, or Restaurant
					int type_choice = generator.nextInt(3);

					if (type_choice == 0) {  //choose attraction

						ArrayList<LandElement> attractions = getPossibleChoices(land_choice, 'a');
						if(attractions.size() == 0){
							p.leavePark();
						}
						else{
							int choice = generator.nextInt(attractions.size());
							LandElement attraction = attractions.get(choice);
							//Account for time to move to location
							if(LandObjs.indexOf(attraction.getLand()) + 1 == p.getLocation())
								p.decreaseTick(1);
							else
								p.decreaseTick(2);

							p.setLocation(LandObjs.indexOf(attraction.getLand()) + 1);

							if(attraction.canEnter(p)){
								attraction.enter(p);
								p.decreaseTick(attraction.getTimeNeeded());
							}
							p.setSpecificLocation(attraction);
						}
					}

					else if (type_choice ==1 ){ //choose store
						ArrayList<LandElement> stores = getPossibleChoices(land_choice, 's');
						if(stores.size()==0){
							p.leavePark();
						}
						else{
							int choice = generator.nextInt(stores.size());
							LandElement store = stores.get(choice);
							//Account for time to move to location
							if(LandObjs.indexOf(store.getLand())+ 1 == p.getLocation())
								p.decreaseTick(1);
							else
								p.decreaseTick(2);

							p.setLocation(LandObjs.indexOf(store.getLand()) + 1);

							if(store.canEnter(p)){
								store.enter(p);
								p.decreaseTick(store.getTimeNeeded());
							}
							p.setSpecificLocation(store);
						}	

					}
					else { //choose restaurant
						ArrayList<LandElement> restaurants = getPossibleChoices(land_choice, 'r');
						if(restaurants.size()==0){
							p.leavePark();
						}
						else{
							int choice = generator.nextInt(restaurants.size());
							LandElement restaurant = restaurants.get(choice);
							//Account for time to move to location
							if(LandObjs.indexOf(restaurant.getLand()) + 1 == p.getLocation())
								p.decreaseTick(1);
							else
								p.decreaseTick(2);

							p.setLocation(LandObjs.indexOf(restaurant.getLand()) + 1);

							if(restaurant.canEnter(p)){
								restaurant.enter(p);
								p.decreaseTick(restaurant.getTimeNeeded());
							}
							p.setSpecificLocation(restaurant);
						}	

					}
					if (p.getEnergyLevel() <= 1){
						p.leavePark();
					}
				}
			}

		}
		//Get Sales figures for all stores and restaurants
		for(int i = 0; i < LandObjs.size(); i++){
			ArrayList<LandElement> land_contents = LandObjs.get(i).getContents();
			for(int j = 0; j < land_contents.size(); j++){
				sales += land_contents.get(j).calculateRevenue();
			}
		}
		double park_net_revenue = sales - cost;

		park_net_revenue = d.getDays() * park_net_revenue;
		return park_net_revenue;
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

	private ArrayList<LandElement> getPossibleChoices(int land_choice, char type){
		boolean isChoosing= true;
		int num_Lands = LandObjs.size();
		ArrayList<LandElement> choices = new ArrayList<LandElement>();
		while(isChoosing){
			choices = getAllType(LandObjs.get(land_choice), type);
			//An ArrayList of lands that do not contain elements of this choice
			ArrayList<Integer> emptyLands = new ArrayList<Integer>();
			Random generator = new Random();
			if (choices.size()==0){
				emptyLands.add(land_choice);
				if (emptyLands.size() != num_Lands){
					do{
						land_choice = generator.nextInt(num_Lands);	
					}
					while(emptyLands.contains(land_choice));
				}
				else
					return choices;
			}
			else
				isChoosing = false;
		}
		return choices;
	}
}