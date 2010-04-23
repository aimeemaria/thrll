// This class extends the Duration class
public class Days extends Duration {
	//one tick is 15 minutes
	//Day is 12 Hours
	private int days; //how much of each type; ex. for 3 days, value is 3

	public Days(int days){
		type = "Days";
		this.days = days;
		ticks = days * 48;
	}

	public String getType() {
		return type;
	}

	public int getTicks() {
		return ticks;
	}

	public int getDays() {
		return days;
	}
}