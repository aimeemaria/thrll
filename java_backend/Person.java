import java.util.Random;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Person {
	private int energyLevel;
	private int thrillLevel;
	private double spendingCapacity;
	private int location; // what land this person is in
	private double x,y; //position
	private int tick;
	private int id;
	private LandElement whereAmI; //The exact store, restaurant, or attraction this person is in	
	static FileWriter positionFile = null; 
	
	private void setattributes(int energy,int thrill, double spend){
		energyLevel = energy;
		thrillLevel = thrill;
		spendingCapacity = spend;
	}

	public Person(int energy, int thrill, int spend, int id) {
		setattributes(energy,thrill,spend);
		tick = 48;
		location = 1;
		this.id = id;
		whereAmI = new Store(); //the entrance, used a store as it does not effect the person's attributes
		didEnter = false;
	}
	public Person(){
		setattributes(10,10,10);
		x=0;
		y=0;
		tick = 48;
		location = 1; //person enters in Land 1
		whereAmI = new Store(); //the entrance, used a store as it does not effect the person's attributes
		didEnter = false;
	}

	public void set_position(double x, double y){
		this.x=x;
		this.y=y;
	}
	public int getEnergyLevel(){
		return energyLevel;
	}

	public int getThrillLevel(){
		return thrillLevel;
	}

	public double getSpendingCapacity(){
		return spendingCapacity;
	}

	public double[] getposition(){
		double pos[]={x,y};
		return pos;
	}

	public void setSpendingCapacity(double spendingCapacity) {
		this.spendingCapacity = spendingCapacity;
	}

	public void setEnergyLevel(int energyLevel) {
		this.energyLevel = energyLevel;
	}

	public void setThrillLevel(int thrillLevel) {
		this.thrillLevel = thrillLevel;
	}

	public void decreaseTick(int dec){
		tick -= dec;
	}

	public int getTick(){
		return tick;

	}

	public void leavePark(){
		tick = 0;
	}

	public int getLocation(){
		return location;
	}

	public void setLocation(int land){
		location = land;
	}

	public void setSpecificLocation(LandElement location){
		whereAmI = location;
		//this should happen only when simulate button is clicked. 
		Point2D currentlocation = whereAmI.getPosition();
		
		System.out.println("Current Location: x, y:" + currentlocation.getX() + ","+ currentlocation.getY());
		//write the person number and the position into the file.
		if(positionFile == null){
			try{
			    System.out.println("Creating a file\n");
				positionFile = Park.positionFile;
			}catch(Exception e){
				
			}
		}
		
		try{
			String posLine = Integer.toString(id) 
			+ ":" + Double.toString(currentlocation.getX()) 
			+ ":" + Double.toString(currentlocation.getY()) 
			+ "\n"
			;

			positionFile.write(posLine);
		}catch(IOException io){

		}
	}

	public LandElement getSpecificLocation(){
		return whereAmI;
	}

}
