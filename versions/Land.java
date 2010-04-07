// this class implements the Land type
import java.util.ArrayList;

public class Land {
	String landName = null;
	private int locationVal = 1;
	double location = 0;
	Park park = null;
	// other attributes

	ArrayList<Store> attractionList = new ArrayList<Store>();
	ArrayList<Store> restaurantList = new ArrayList<Store>();
	ArrayList<Store> storeList = new ArrayList<Store>();
	
	private void setLandAttributeValues(double location){
		this.location = location;
	}
	
	public Land(){
		setLandAttributeValues(locationVal++);
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

	public void addStore(Store s){
		storeList.add(s);
	}
}
