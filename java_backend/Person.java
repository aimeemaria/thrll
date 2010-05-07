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
	}
	public Person(){
		setattributes(10,10,10);
		x=0;
		y=0;
		tick = 48;
		location = 1; //person enters in Land 1
		whereAmI = new Store(); //the entrance, used a store as it does not effect the person's attributes
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
		set_position(0,0);
		setSpecificLocation(null);
		System.out.println("x, y" + x + ", " + y);
		System.out.println("Leaving park " + id);
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
		if(location!=null) {			
			Point2D currentlocation = whereAmI.getPosition();
			this.x = currentlocation.getX();
			this.y = currentlocation.getY();
		}
		
		//System.out.println("Current Location: x, y:" + currentlocation.getX() + ","+ currentlocation.getY());
		//write the person number and the position into the file.
		if(Park.createPositionFile){
			if(positionFile == null){			
				//System.out.println("Person: Opening file \n");
				positionFile = Park.positionFile;
			}
			else{
				try{
					String posLine = Integer.toString(id) 
					+ ":" + Double.toString(x) 
					+ ":" + Double.toString(y) 
					+ "\n"
					;
					positionFile.write(posLine);
				}catch(IOException io){
					System.out.println(io.getMessage());
					System.out.println(io.getStackTrace());
				}
			}
		}
	}

	public LandElement getSpecificLocation(){
		return whereAmI;
	}

}
