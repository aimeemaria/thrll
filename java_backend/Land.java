/*
 * Land.java
 * Team THRLL
 * This file represents Land in theme park.
 */

import java.util.ArrayList;
import java.awt.geom.Point2D;
public class Land {

	private int location; // possible locations 1-6; 60*location-1 < angle < 60*location
	private String LandName;
	
	
	//list of land element objects
	private ArrayList <LandElement> LandElemObjs = new ArrayList<LandElement>();
	//a land can hold up to 6 landElements
	
	/* Constructor
	 * To initialize the members with default values
	 */
	public Land()
	{
		// Set Default Values
		location = 1;
		LandName = "MainLand";
	}
	
	public String getLandName() 
	{ 
		return LandName; 
 	}
	
	public int getlocation() 
	{
		return location;	
	}
	
	public void setLandName(String LandName) 
	{ 
		this.LandName = LandName; 
 	} 
	
	public void setlocation(int location) 
	{
		this.location = location;	
	}	
	
	// Create the Land Elements in Land
	public void add(LandElement t)
	{
		if(LandElemObjs.size() < 9){
			t.setLand(this);
			LandElemObjs.add(t);
		}
		else
			System.out.println("Land is full!");
	}
	public double getcost(int hours, double salary){
		double cost = 0;
		//for each land element in land
		//get cost of operation
		for(int i = 0 ; i<LandElemObjs.size();i++){
			cost += LandElemObjs.get(i).getcost(hours,salary);
		}
		return cost;
	}
	
	public ArrayList<LandElement> getContents(){
		return LandElemObjs;
	}

}
