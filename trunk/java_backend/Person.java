import java.util.Random;
import java.awt.geom.Point2D;
public class Person {
	private int energyLevel;
	private int thrillLevel;
	private double spendingCapacity;
	private int location; // what land this person is in
	private double x,y; //position
	private int tick;
	private LandElement whereAmI; //The exact store, restaurant, or attraction this person is in
	private boolean didEnter;


	private void setattributes(int energy,int thrill, double spend){
		energyLevel = energy;
		thrillLevel = thrill;
		spendingCapacity = spend;
	}

	public Person(int energy, int thrill, int spend) {
		setattributes(energy,thrill,spend);
		tick = 48;
		location = 1;
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
	}

	public LandElement getSpecificLocation(){
		return whereAmI;
	}

	public void setDidEnter(boolean value){
		didEnter = value;
	}
	
	public boolean getDidEnter(){
		return didEnter;
	}
}