/*
 * Land.java
 * Team THRLL
 * This file represents Land in theme park.
 */

import java.util.ArrayList;

public class Land {

	private int location;
	private String LandName;	
	
	//list of land element objects
	private ArrayList <LandElement> LandElemObjs = null;
	
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
		LandElemObjs.add(t);
	}

}
