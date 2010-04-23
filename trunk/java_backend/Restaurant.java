import java.util.Random;
import java.awt.geom.Point2D;
/*
 * Restaurant.java
 * Team THRLL
 * This file represents a Restaurant in the theme park.
 */

public class Restaurant implements LandElement
{
	 private String RestaurantName; 
	 private double cost; 
	 private int capacity; 
	 private int employees; 
	 private double SpendLevel; 
	 private int EnergyIncrease; 
	 private int CurrentAttendance;
	 private double price;
	 private double sales;
	 private Land land;
	 private Point2D.Double p;
	 private int timeNeeded;
	 /*	Constructor
	  * To set default values to the members
	  */
	 public Restaurant()
	 { 
		//set default values
		RestaurantName = "Main Restaurant";
		cost = 100;
		capacity = 100;
		employees = 2;
		SpendLevel = 5;
		EnergyIncrease = 1;
		CurrentAttendance = 0;
		price = 10.00;
		sales = 0.0;	
		timeNeeded = 2;
		p = new Point2D.Double();
	 } 
	 
	 
	 
	 public double getcost(int hours, double salary){
		return hours * employees * salary;
	 }
	 
	 /*
	  * Determines if a person can enter the Restaurant.
	  * @param p The person trying to enter the Restaurant
	  * @return A boolean determining if the person can enter the Restaurant on the basis of the SpendLevel
	  */
	 public boolean canEnter(Person p) 
	 {
	 	 if (p.getSpendingCapacity() >= this.SpendLevel && CurrentAttendance < capacity)
	 		 return true;
	 	 else
	 		 return false;
	 }

	 /*
	  * Allows a person to enter the Restaurant.  Assumes a sale will occur once they enter the Restaurant.
	  * @param p The person entering the Restaurant.
	  */
	 public void enter(Person p) 
	 {
	 	if (canEnter(p))
 		{
			CurrentAttendance++;
			p.setEnergyLevel(p.getEnergyLevel() + EnergyIncrease);
			sales += price;
		}
	 }
	 
	 /*
	  * *Allows a person to exit the Restaurant.
	  * @param p The person exiting the Restaurant.
	  */
	 public void exit(Person p) 
	 {
		CurrentAttendance--;
	 }
	 
	 public String getRestaurantName() 
	 { 
		 return RestaurantName; 
	 } 
	  
	 public double getCost() 
	 { 
		 return cost; 
	 } 
	  
	 public int getCapacity() 
	 { 
		 return capacity; 
	 } 
	  
	 public int getEmployees() 
	 { 
		 return employees; 
	 } 
	  
	 public double getSpendLevel() 
	 { 
		 return SpendLevel; 
	 } 
	  
	 public int getEnergyIncrease() 
	 { 
		 return EnergyIncrease; 
	 } 
	  
	 public void setRestaurantName(String RestaurantName) 
	 { 
		 this.RestaurantName = RestaurantName; 
	 } 
	  
	 public void setCost(double cost) 
	 { 
		 this.cost = cost; 
	 } 
	  
	 public void setCapacity(int capacity) 
	 { 
		 this.capacity = capacity; 
	 } 
	  
	 public void setEmployees(int employees) 
	 { 
		 this.employees = employees; 
	 } 
	  
	 public void setSpendLevel(double SpendLevel) 
	 { 
		 this.SpendLevel = SpendLevel; 
	 } 
	  
	 public void setEnergyIncrease(int EnergyIncrease) 
	 { 
		 this.EnergyIncrease = EnergyIncrease; 
	 } 
	 
	 public int getCurrentAttendance() 
	 {
		return CurrentAttendance;
	 }
	 
	 public void setLand(Land land)
	 { 
		this.land = land; 
		//set location within land
		Random r = new Random();
		int radius = r.nextInt(3)+2;
		int angle = r.nextInt(60) + 60*(land.getlocation()-1);
		p.setLocation(radius*Math.cos(angle),radius*Math.sin(angle));
	 } 
	  
	 public Land getLand()
	 { 
	 	return land; 
	 }

	@Override
	public double calculateRevenue() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Point2D get_position() {
		// TODO Auto-generated method stub
		return null;
	}
	public char getType(){
		return 'r';
	}



	/**
	 * @return the timeNeeded
	 */
	public int getTimeNeeded() {
		return timeNeeded;
	}



	/**
	 * @param timeNeeded the timeNeeded to set
	 */
	public void setTimeNeeded(int timeNeeded) {
		this.timeNeeded = timeNeeded;
	}
	  
}		