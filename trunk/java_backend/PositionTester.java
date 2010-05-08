import java.awt.geom.Point2D;
/**PositionTester.java
 * 
 * A test program to test that objects are given accurate and correct coordinates within their lands.
 * @author Michael
 *
 */
public class PositionTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
Park myPark = new Park();
		
		Land Main_Street = new Land();
		Land Adventureland = new Land();
		
		Main_Street.setLocation(1);
		Adventureland.setLocation(3);
		
		myPark.addLand(Main_Street);
		myPark.addLand(Adventureland);
		
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
		
		System.out.println("Gibson Girl Location: " +Gibson_Girl.getPosition().getX()
				+ ", " +Gibson_Girl.getPosition().getY());
		
		double radius = Math.sqrt(Math.pow(Gibson_Girl.getPosition().getX(), 2) + Math.pow(Gibson_Girl.getPosition().getY(),2));
		System.out.println("Radius: " +radius);
		
		double angle = Math.atan2(Gibson_Girl.getPosition().getY(), Gibson_Girl.getPosition().getX());
		System.out.println("Angle: " +Math.toDegrees(angle));

		System.out.println("Jungle Cruise Location: " +Jungle_Cruise.getPosition().getX()
				+ ", " +Jungle_Cruise.getPosition().getY());
		
		double radius2 = Math.sqrt(Math.pow(Jungle_Cruise.getPosition().getX(), 2) + Math.pow(Jungle_Cruise.getPosition().getY(),2));
		System.out.println("Radius: " +radius2);
		
		double angle2 = Math.atan2(Jungle_Cruise.getPosition().getY(), Jungle_Cruise.getPosition().getX());
		System.out.println("Angle: " +Math.toDegrees(angle2));
		
		Crowd c = new Crowd();
		Days d = new Days(1);
		
		myPark.calculateRevenue(c, d);
	}
	

}
