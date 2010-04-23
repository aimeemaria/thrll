// This class extends the Duration class
public class Years extends Duration {
	//one tick is 15 minutes
	// Year is 365 days
	private int years; //how much of each type; ex. for 3 days, value is 3

	public Years(int years){
		type = "Years";
		this.years = years;
		ticks = years * 12 * 30 * 48;
	}

	public String getType() {
		return type;
	}

	public int getTicks() {
		return ticks;
	}

	public int getYears(){
		return years;
	}
	
	public int getDays() {
		return years * 365;
	}
}
