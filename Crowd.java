
import java.util.ArrayList;

public class Crowd {
	int Size;
	int EnergyLevel;
	int ThrillLevel;
	int SpendingCapacity;
	ArrayList<Person> people;
	
	private void setattributes(int size,int energy,int thrill,int spend){
		Size = size;
		EnergyLevel = energy;
		ThrillLevel = thrill;
		SpendingCapacity = spend;
	}
	
	public Crowd(){
		people=new ArrayList<Person>();
		setattributes(10,10,5,50);//default values?
		for(int i = 0; i<Size;i++){
			//TODO:set attributes of each created person in a normal distribution of crowd attributes
			people.add(new Person());
		}
	}

	public int getEnergyLevel(){
		return EnergyLevel;
	}
	
	public int getThrillLevel(){
		return ThrillLevel;
	}
	
	public int getSpendingCapacity(){
		return SpendingCapacity;
	}
	
}
