// this class implements the Duration type
public abstract class Duration {
	protected String type;
	protected int ticks;
	
	public abstract String getType();
	public abstract int getTicks();	
	public abstract int getDays();
}
