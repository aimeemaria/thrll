import java.util.Random;
import java.awt.geom.Point2D;
public class Person {
	private int energyLevel;
	private int thrillLevel;
	private double spendingCapacity;
	private int location; // what land this person is in
	private double x,y; //position
	private int tick;

	
	
	private void setattributes(int energy,int thrill, double spend){
		energyLevel = energy;
		thrillLevel = thrill;
		spendingCapacity = spend;
	}
	
	public Person(int energy, int thrill, int spend) {
		setattributes(energy,thrill,spend);
	}
	public Person(){
		setattributes(5,5,100);
		x=0;
		y=0;
		tick = 48;
		location = 1; //person enters in Land 1
	}
	public void decrease_tick(int n){
		tick -=n;
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

}
