import java.awt.geom.Point2D;

/**The interface for Land Elements.  This defines the required
 * functions and variables for all three land elements: attractions,
 * stores, and restaurants.
 * 
 */

/**
 * @author Michael
 *
 */

public interface LandElement {
	
	public double calculateRevenue();
	public double getcost(int hours, double salary);
	public boolean canEnter(Person p);
	public Point2D get_position();
	public void setLand(Land land);
	public void enter(Person p);
	public void exit(Person p);
	public char getType();
	public int getTimeNeeded();
	public Land getLand();
	
}
