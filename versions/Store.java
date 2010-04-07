// this class implements the Store type
public class Store {
	String storeName = null;
	Land land = null;
	double employees = 0;
	// other attributes
	
/*	private void setStoreAttributeValues(double admission, double capacity, double cost){
		this.admission = admission;
		this.capacity = capacity;
		this.cost = cost;
	}
*/
	public Store(){
		//setStoreAttributeValues();
	}
	
	public String getStoreName() {
		return storeName;
	}
	
	public double getEmployees() {
		return employees;
	}
	
	public Land getLand() {
		return land;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	
	public void setEmployees(double employees) {
		this.employees = employees;
	}
	
	public void setLand(Land land) {
		this.land = land;
	}
	

}
