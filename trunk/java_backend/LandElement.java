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
	
	public double calculateRevenue(Park park);
	public boolean canEnter(Person p);
	public void enter(Person p);
	public void exit(Person p);
	
}
