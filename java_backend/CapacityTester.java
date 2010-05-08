/**CapacityTester.java
 * 
 * This file tests to ensure that the capacity constraint
 * of objects is functioning properly
 */

/**
 * @author Michael Seaman
 *
 */
public class CapacityTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Park myPark = new Park();
		Land myLand = new Land();

		myPark.addLand(myLand);

		Restaurant myRestaurant = new Restaurant();
		myLand.addRestaurant(myRestaurant);
		myRestaurant.setCapacity(100);
		myRestaurant.setPrice(1.0);
		myRestaurant.setTimeNeeded(1);
		
		Attraction myAttraction = new Attraction();
		myAttraction.setEnergyLost(0);
		myLand.addAttraction(myAttraction);
		
		Store myStore = new Store();
		myStore.setPrice(0);
		myLand.addStore(myStore);

		Crowd myCrowd = new Crowd();
		myCrowd.setSize(30000);

		Days oneDay = new Days(1);
		
		double revenue1 = myPark.calculateRevenue(myCrowd, oneDay);
		double storeRevenue = myRestaurant.calculateRevenue();

		System.out.println("Restaurant with full capacity: " + storeRevenue);
		System.out.println("Park Revenue: " +revenue1 +"\n");

		myRestaurant.setCapacity(0);
		double revenue2 = myPark.calculateRevenue(myCrowd, oneDay);
		storeRevenue = myRestaurant.calculateRevenue();

		System.out.println("Restaurant with capacity of 0: " +storeRevenue);
		System.out.println("Park Revenue: " +revenue2 +"\n");
		
		myRestaurant.setCapacity(1);
				
		double revenue3 = myPark.calculateRevenue(myCrowd, oneDay);
		storeRevenue = myRestaurant.calculateRevenue();

		System.out.println("Restaurant with capacity of 1: " + storeRevenue);
		System.out.println("Park Revenue: " +revenue3 +"\n");

		myRestaurant.setCapacity(100);
		double revenue4 = myPark.calculateRevenue(myCrowd, oneDay);
		storeRevenue = myRestaurant.calculateRevenue();

		System.out.println("Restaurant with full capacity: " + storeRevenue);
		System.out.println("Park Revenue: " +revenue4);	
	}
}