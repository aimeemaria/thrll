// this class implements the Duration type
public class Duration {
	 //one tick is 15 minutes
	//Day is 12 Hours
	//Week is 7 Days
	//Month is 30 Days
	//Year is 365 Days
	private String type;
	private int tick;
	private int value; //how much of each type; ex. for 3 days, value is 3
	
	public Duration(){
		type = "day";
		value = 1;
		tick = 48;
	}
	
	public void set_value(int val){
		value = val;
		set_tick(type);
	}
	
	public void set_type(String s){
		type = s;
		set_tick(s);
	}
	
	public void set_Duration(String s, int val){
		value = val;
		type = s;
		set_tick(s);
	}
	
	private void set_tick(String s){
		if(s == "day")
			tick = 48*value;
		else if(s == "week")
			tick = 48*7*value;
		else if(s == "month")
			tick = 48*7*30*value;
		else if (s == "year")
			tick = 48*7*30*365*value;
		else 
			System.out.println("invalid duration type");
	}
	
	public int get_tick(){
		return tick;
	}
	public String get_type(){
		return type;
	}
	public int get_value(){
		return value;
	}
	  
}
