import java.util.ArrayList;

public class LandSortingTester {

	private static Park myPark;

	public static void main(String[] args) {

		myPark = new Park();

		Land land1 = new Land();
		land1.setLocation(1);
		land1.setLandName("Land One");
		myPark.addLand(land1);

		Land land3 = new Land();
		land3.setLocation(3);
		land3.setLandName("Land Three");
		myPark.addLand(land3);

		Land land6 = new Land();
		land6.setLocation(6);
		land6.setLandName("Land Six");
		myPark.addLand(land6);
		
		Land extraLand = new Land();
		extraLand.setLocation(3);
		extraLand.setLandName("Extra Land");
		myPark.addLand(extraLand);
		
		Land land4 = new Land();
		land4.setLocation(4);
		land4.setLandName("Land 4");
		myPark.addLand(land4);
		
		Land extraLand3 = new Land();
		extraLand3.setLandName("Extra Land 2");
		myPark.addLand(extraLand3);
		
		Land extraLand4 = new Land();
		extraLand4.setLocation(4);
		extraLand4.setLandName("Extra Land 3");
		myPark.addLand(extraLand4);
		
		Land extraLand5 = new Land();
		extraLand5.setLandName("Extra Land 4");
		myPark.addLand(extraLand5);
		
		Land extraLand7 = new Land();
		extraLand7.setLandName("Extra Land 7");
		extraLand7.setLocation(4);
		myPark.addLand(extraLand7);

		myPark.sortLands();
		
		listLands();

		Crowd myCrowd = new Crowd();
		myCrowd.setSize(10000);
		
		Days oneDay = new Days(1);
		
		double revenue = myPark.calculateRevenue(myCrowd, oneDay);
		
		//listLands();

		System.out.print("Revenue = " +revenue);
		
		if (revenue == myCrowd.getSize() * myPark.getAdmission() * oneDay.getDays())
			System.out.println("\tOK");
		else
			System.out.println("\tFAIL!!\nRevenue should equal crowd size * admission price * duration");
		
	}

	public static void listLands(){
		ArrayList<Land> lands = myPark.getLands();
		for (int i = 0; i < 6; i++){
			System.out.println("Land " +(i+1) +"= " + lands.get(i).getLandName());
		}
	}
}
