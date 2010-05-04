import java.awt.geom.Point2D;

import java.util.Random;
/**Attraction.java
 * 4/6/2010
 * This class represents an Attraction in the theme park.
 */

/**
 * @author Michael Seaman
 *
 */
public class Attraction implements LandElement {

	private String attractionName; //Name of the attraction
	private int thrillLevel; //The thrill level of the Attraction
	private int energyLoss; //The amount of energy it takes to ride the attraction
	private double cost; //The cost of the attraction
	private int capacity; //The capacity of the attraction
	private int currentAttendance; //The current number of people in the attraction
	private int employees; //The number of employees needed to operate the attraction
	private int energyLost; //Energy Lost during this attraction
	private int timeNeeded; //The amount of time it takes to experience the attraction
	private Land land; //The land object to which this attraction belongs to
	private Point2D p;
	/**The Constructor
	 * Constructs an attraction with default values
	 */
	public Attraction(){
		//Set Default Values
		thrillLevel = 5;
		energyLoss = 1;
		capacity = 1000;
		currentAttendance = 0;
		employees = 2;
		timeNeeded = 4;
	}
	/**Determines if a person can enter the attraction.  If they can, returns true
	 * 
	 * @param p the person attempting to enter the attraction
	 * @return a boolean representing whether a person can enter the attraction
	 */
	public boolean canEnter(Person p){
		if (p.getThrillLevel() >= this.thrillLevel && p.getEnergyLevel() >= this.energyLoss && currentAttendance <= capacity)
			return true;
		else
			return false;
	}
	
	/**The method used to allow a person to enter the attraction.  This method adds the person to the attraction's
	 * attendance number.  
	 * 
	 * @param p The person entering the attraction
	 */
	public void enter(Person p){
		if (canEnter(p))
			currentAttendance++;
	}
	
	/**The method used to allow a person to exit the attraction.
	 * This method subtracts this attraction's energy requirement from the person and subtracts the
	 * person from the attendance number.
	 * 
	 * @param p The person exiting the attraction
	 */
	public void exit(Person p){
		if (canEnter(p)){//not necessary?
			p.setEnergyLevel(p.getEnergyLevel() - energyLoss);
			currentAttendance--;
		}
	}

	/**This method calculates the revenue generated by this land element.
	 * For an attraction, no revenue is generated.  Costs, however, are
	 * incurred.  Thus, this method returns a negative number representing
	 * the pay to the employees for one day.
	 * 
	 * @param park The park where the attraction is located.
	 * @return The cost to operate this attraction for one day.
	 */
	public double calculateRevenue(){
		double revenue = 0;
		return revenue;
	}
	
	public double getcost(int hours, double salary){
		return hours * employees * salary;
	 }
	public String getAttractionName() {
		return attractionName;
	}
	
	public void setAttractionName(String attractionName) {
		this.attractionName = attractionName;
	}
	/**
	 * @return the thrill level of the attraction
	 */
	public int getThrillLevel() {
		return thrillLevel;
	}

	/**
	 * @param thrill the thrill level of the attraction to set
	 */
	public void setThrillLevel(int thrill) {
		this.thrillLevel = thrill;
	}

	/**
	 * @return the energy loss of the attraction
	 */
	public int getEnergyLoss() {
		return energyLoss;
	}

	/**
	 * @param energyLoss the energy loss of the attraction to set
	 */
	public void setEnergyLoss(int energyLoss) {
		this.energyLoss = energyLoss;
	}

	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	/**
	 * @return the capacity of the attraction
	 */
	public int getCapacity() {
		return capacity;
	}
	public Point2D get_position(){
		return p;
	}

	/**
	 * @param capacity the capacity of the attraction to set
	 */
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	/**
	 * @return the employees required to operate the attraction
	 */
	public int getEmployees() {
		return employees;
	}

	/**
	 * @param employees the employees required to operate the attraction to set
	 */
	public void setEmployees(int employees) {
		this.employees = employees;
	}

	public int getEnergyLost() {
		return energyLost;
	}
	public void setEnergyLost(int energyLost) {
		this.energyLost = energyLost;
	}
	/**
	 * @return the time needed to experience the attraction
	 */
	public int getTimeNeeded() {
		return timeNeeded;
	}

	/**
	 * @param timeNeeded the time needed to experience the attraction to set
	 */
	public void setTimeNeeded(int timeNeeded) {
		this.timeNeeded = timeNeeded;
	}
	/**
	 * @return the number of guests currently experiencing the attraction.
	 */
	public int getCurrentAttendance() {
		return currentAttendance;
	}

	/**
	 * @param the land to which the attraction belongs to
	 */
	public void setLand(Land land){
		this.land = land;
		
		//set location within land
		Random r = new Random();
		int radius = r.nextInt(3)+2;
		int angle = r.nextInt(60) + 60*(land.getLocation()-1);
		p=new Point2D.Double();
		p.setLocation(radius*Math.cos(angle),radius*Math.sin(angle));
	}	

	/**
	 * @return the land to which the attraction belongs to
	 */
	public Land getLand(){
		return land;
	}	
	
	public char getType(){
		return 'a';
	}
}
