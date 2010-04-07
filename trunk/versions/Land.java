// this class implements the Land type
import java.util.ArrayList;

public class Land {
	private String landName;
	private int locationVal;
	private double location;
	private Park park;
	// other attributes

	ArrayList<Attraction> attractionList = new ArrayList<Attraction>();
	ArrayList<Restaurant> restaurantList = new ArrayList<Restaurant>();
	ArrayList<Store> storeList = new ArrayList<Store>();
	
	public Land(){
	}

	public String getLandName() {
		return landName;
	}

	public double getLocation() {
		return location;
	}
	
	public Park getPark() {
		return park;
	}
	
	public void setLandName(String landName) {
		this.landName = landName;
	}

	public void setLocation(double location) {
		this.location = location;
	}

	public void setPark(Park park) {
		this.park = park;
	}
	
	public void addAttraction(Attraction a){
		attractionList.add(a);
	}
	
	public void addRestaurant(Restaurant r){
		restaurantList.add(r);
	}
	
	public void addStore(Store s){
		storeList.add(s);
	}
}
