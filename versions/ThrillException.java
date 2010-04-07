// this class file implements the Thrill Exception type
public class ThrillException extends Exception {
	String message = null;
	
	public ThrillException(String message){
		this.message = message;
	}
	
	public static void redefinitionException(String identifier) throws ThrillException{
		ThrillException e = new ThrillException(identifier + " already defined");
		throw e;
	}
	
	public static void objectNotFoundException(String identifier) throws ThrillException{
		ThrillException e = new ThrillException(identifier + " not found");
		throw e;
	}
}
