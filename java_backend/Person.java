import java.util.Random;
public class Person {
	private int energyLevel;
	private int thrillLevel;
	private double spendingCapacity;
	private double x,y; //position
	private int tick; //time
	
	private void setattributes(int energy,int thrill, double spend){
		energyLevel = energy;
		thrillLevel = thrill;
		spendingCapacity = spend;
	}
	
	public Person(){
		setattributes(5,5,100);
		x=0;
		y=0;
		tick = 600;
	}
	
	public int getEnergyLevel(){
		return energyLevel;
	}
	
	public int getThrillLevel(){
		return thrillLevel;
	}
	
	public double getSpendingCapacity(){
		return spendingCapacity;
	}
	
	public double[] getposition(){
		double pos[]={x,y};
		return pos;
	}

	public void setSpendingCapacity(double spendingCapacity) {
		this.spendingCapacity = spendingCapacity;
	}

	public void setEnergyLevel(int energyLevel) {
		this.energyLevel = energyLevel;
	}

	public void setThrillLevel(int thrillLevel) {
		this.thrillLevel = thrillLevel;
	}
	public int decide(){
		//randomly select attribute store or restaurant
		Random generator = new Random();
		/*
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
			*/
		return generator.nextInt(3);
	}
	
}
