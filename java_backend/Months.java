// This class extends the Duration class

public class Months extends Duration {
	//one tick is 15 minutes
	//Month is 30 Days
	private int months; //how much of each type; ex. for 3 days, value is 3

	public Months(int months){
		type = "Months";
		this.months = months;
		ticks = months * 30 * 48;
	}

	public String getType() {
		return type;
	}

	public int getTicks() {
		return ticks;
	}

	public int getMonths() {
		return months;
	}
	
	public int getDays(){
		return months * 30;
	}
}
