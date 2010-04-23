// This class implements the Weeks type
public class Weeks extends Duration {

	//one tick is 15 minutes
	//Week is 7 Days
	private int weeks; //how much of each type; ex. for 3 days, value is 3

	public Weeks(int weeks){
		type = "Weeks";
		this.weeks = weeks;
		ticks = weeks * 7 * 48;
	}

	public String getType() {
		return type;
	}

	public int getTicks() {
		return ticks;
	}

	public int getWeeks(){
		return weeks;
	}
	
	public int getDays() {
		return weeks * 7;
	}
}
