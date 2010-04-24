
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
		Jungle_Cruise.setEnergyLoss(2);
		Adventureland.add(Jungle_Cruise);
		
		Attraction Indiana_Jones_Adventure = new Attraction();
		Indiana_Jones_Adventure.setEnergyLoss(5);
		Adventureland.add(Indiana_Jones_Adventure);
		
		Store Adventureland_Mercantile = new Store();
		Adventureland_Mercantile.setPrice(20);
		Adventureland.add(Adventureland_Mercantile);
		
		Restaurant Bengal_BBQ = new Restaurant();
		Adventureland.add(Bengal_BBQ);
		
		Store Emporium = new Store();
		Main_Street.add(Emporium);
		Restaurant Gibson_Girl = new Restaurant();
		Main_Street.add(Gibson_Girl);
		
		Crowd myCrowd = new Crowd();
		myCrowd.setSize(1000);
		
		Duration myDuration = new Duration();
		
		myPark.calculateRevenue(myCrowd, myDuration);
		
		System.out.println("Mercantile Sales = " +Adventureland_Mercantile.getSales());
	}

}
