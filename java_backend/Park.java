/*
 * Park.java
 * Team THRLL
 * This file represents Theme Park.
 */

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class Park 
{
	private String ParkName;
	private double admission;
	private double capacity;
	private double cost;
	public int hours;  		//the number of hours the park operates in a day.
	public double salary;  	//the salary of park employees
	static FileWriter positionFile = null; 
	static boolean createPositionFile = false;
	private int cx,cy; //center of park graphics, same as in display panel
	private int cr = 40;//hub radius (must match value in display panel)

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

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize(); 
		int SCREEN_WIDTH=(int)dim.getWidth(); //screen size for panel
		int SCREEN_HEIGHT=(int)dim.getHeight();
		cx = (int)(SCREEN_WIDTH/3-cr/3);
		cy = (int) (SCREEN_HEIGHT/3-cr/3);
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
		sortLands();

		double sales = 0;

		//for each land
		//get cost of operation (include employees)
		//and reset sales values
		double cost=0;
		for(int i = 0 ; i < LandObjs.size();i++){
			Land land = LandObjs.get(i);
			cost += land.getcost(hours,salary);//returns operation cost for all elements in the land
			land.resetSales();
		}

		double admissionSales = c.getSize() * admission;
		sales += admissionSales;

		if(createPositionFile){
			//write the number of people and number of land elements inside the positionFile, first thing. Also, this overwrites an existing position file
			try{
				//System.out.println("Park: Creating a file\n");
				positionFile = new FileWriter(new File("../gui/position.txt"));
				String numobjs;
				int total=0; //total count of land elements in park
				for(int i =0;i<LandObjs.size();i++){
					total+=LandObjs.get(i).getContents().size();
				}
				numobjs= Integer.toString(total) + "\n";
				positionFile.write(numobjs);
				//for each element write the position to the file

				for(int i = 0 ; i < LandObjs.size();i++){
					Point2D p;
					LandElement e;
					char type;
					String position;
					for(int j =0; j < LandObjs.get(i).getContents().size();j++){//for each element in this land, write position to file
						e = LandObjs.get(i).getContents().get(j);
						p = e.getPosition();
						position = Double.toString(p.getX()*cr + cx) + " " + Double.toString(p.getY()*cr + cy);
						type = e.getType();
						positionFile.write( type + " " + position + "\n");
					}
				}
				String numpeople = Integer.toString(c.getSize()) + "\n";	
				positionFile.write(numpeople);
			}catch(IOException io){
			}
		}

		//assume the crowd enters as soon as park opens
		c.createpeople();
		Person p;


		Random generator = new Random();  //Random Number Generator
		//Iterate through the day's ticks (1 tick = 15 minutes)
		for(int j = 48; j >0; j--){
			//Iterate through each person
			for(int i =0;i<c.getSize();i++){
				p = c.people.get(i);
				if (p.getTick() == j){

					if (p.getDidEnter())
					{
						p.getSpecificLocation().exit(p);
						p.setDidEnter(false);
					}
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
								p.setDidEnter(true);
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
								p.setDidEnter(true);
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
								p.setDidEnter(true);
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
		
		for(int i =0;i<c.getSize();i++){
			Person pe = c.people.get(i);
			pe.leavePark();
		}
		

		//Get Sales figures for all stores and restaurants
		for(int i = 0; i < LandObjs.size(); i++){
			ArrayList<LandElement> land_contents = LandObjs.get(i).getContents();
			for(int j = 0; j < land_contents.size(); j++){
				sales += land_contents.get(j).calculateRevenue();
			}
		}

		double park_net_revenue = sales - cost;

		if(createPositionFile){
			try{
			    //System.out.println("Closing file");
				positionFile.close();
				positionFile = null;
				Person.positionFile = null;
			}
			catch(Exception e){
			}
		}
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

	public void addLand(Land t)
	{
		LandObjs.add(t);
	}

	private ArrayList<LandElement> getPossibleChoices(int land_choice, char type){
		boolean isChoosing= true;
		int num_Lands = LandObjs.size();
		ArrayList<LandElement> choices = new ArrayList<LandElement>();
		ArrayList<Land> emptyLands = new ArrayList<Land>();
		while(isChoosing){
			choices = getAllType(LandObjs.get(land_choice), type);
			//An ArrayList of lands that do not contain elements of this choice
			Random generator = new Random();

			if (choices.size()==0){
				emptyLands.add(LandObjs.get(land_choice));
				if (emptyLands.size() != num_Lands){
					do{
						land_choice = generator.nextInt(num_Lands);
					}
					while(emptyLands.contains(LandObjs.get(land_choice)));
				}
				else
					return choices;
			}
			else
				isChoosing = false;
		}
		return choices;
	}

	public void setCreateFile(boolean createFile){
		createPositionFile = createFile;
	}

	public void sortLands(){
		Land[] sortedLands = new Land[6];
		LinkedList<Land> landBuffer = new LinkedList<Land>();
		int upperbound;
		//check to ensure 6 or less lands have been defined
		if (LandObjs.size() > 6){
			//System.out.println("More than six lands define.  Only first 6 defined will be used");
			upperbound = 6;
		}
		else
			upperbound = LandObjs.size();

		//the actual sorting
		for (int i = 0; i < upperbound; i++){
			boolean suppressLaterOutput = false;
			Land currentLand = LandObjs.get(i);
			int location = currentLand.getLocation();
			if (location >6){
				//System.out.println("Set location is out of bounds.  Land will be added at an available position");
				location = 1;
				suppressLaterOutput = true;
			}
			int index = location - 1;

			//if no other land has been set to that location
			if(sortedLands[index] == null)
				sortedLands[index] = currentLand;
			else{
				if(!suppressLaterOutput){
					//System.out.println("Land already defined for Land " +location
						//	+"\nLand will be placed in an available position.");
				}
				landBuffer.addLast(currentLand);
			}

		}

		//Fill in undefined lands with conflictingly labeled lands from above
		for (int i = 0; i < 6; i++){
			if (sortedLands[i] == null){
				//If there are no more lands to use, fill with an empty land
				if (landBuffer.isEmpty())
					sortedLands[i] = createEmptyLand();
				else
					sortedLands[i] = landBuffer.remove();
			}
		}

		//Copy sorted list into existing list.
		LandObjs.clear();
		for (int i = 0; i < sortedLands.length; i++){
			LandObjs.add(sortedLands[i]);
		}
	}

	public Land createEmptyLand(){
		Land emptyLand = new Land();
		return emptyLand;
	}

	public ArrayList<Land> getLands(){
		return LandObjs;
	}
}
