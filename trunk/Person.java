import java.util.Random;
public class Person {
	private int EnergyLevel;
	private int ThrillLevel;
	private int SpendingCapacity;
	private double x,y; //position
	private int tick; //time
	
	private void setattributes(int energy,int thrill, int spend){
		EnergyLevel = energy;
		ThrillLevel = thrill;
		SpendingCapacity = spend;
	}
	
	public Person(){
		setattributes(10,5,50);//what are the default values?
		x=0;
		y=0;
		tick = 600;
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
	
	public double[] getposition(){
		double pos[]={x,y};
		return pos;
	}
	
	public void decide(){
		//randomly select attribute store or restaurant
		Random generator = new Random();
		int random = generator.nextInt(3);//generates 3 random integer {0,1,2}
		int dist;
		if (random == 0){//chose attraction
			dist = 1;//get closest attraction
			//if (canenter()){ enter and decrease energy} ;
		}
		else if(random == 1)//chose restaurant
			dist =2;//get closest rest
		else if(random == 2)//chose store
			dist = 3;//get closest store
	}
}
