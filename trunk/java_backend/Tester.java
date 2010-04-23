
public class Tester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Park myPark = new Park();
		Land Main_Street = new Land();
		Land Adventureland = new Land();
		
		myPark.add(Main_Street);
		myPark.add(Adventureland);
		
		Attraction Jungle_Cruise = new Attraction();
		Jungle_Cruise.setEnergyLoss(5);
		Adventureland.add(Jungle_Cruise);
		
		Store Emporium = new Store();
		Main_Street.add(Emporium);
		Restaurant Gibson_Girl = new Restaurant();
		Main_Street.add(Gibson_Girl);
		
		Crowd myCrowd = new Crowd();
		myCrowd.setSize(1);
		
		Duration myDuration = new Duration();
		
		myPark.calculateRevenue(myCrowd, myDuration);

	}

}
