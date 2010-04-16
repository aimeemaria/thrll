// this class file implements the Thrill Exception type
public class ThrillException extends Exception {
	String message = null;
	
	public ThrillException(String message){
		this.message = message;
	}
	
	public static void RedefinitionException(String identifier) throws ThrillException{
		ThrillException e = new ThrillException(identifier + " already defined");
		throw e;
	}
	
	public static void ObjectNotFoundException(String identifier) throws ThrillException{
		ThrillException e = new ThrillException(identifier + " not found");
		throw e;
	}
	
	public static void UnexpectedTypeException(String expectedObjType, String foundObjType) throws ThrillException{
		ThrillException e = new ThrillException("Expected object " + expectedObjType + ". Found " + foundObjType);
		throw e;
	}
	
	public static void InvalidStringConstant(String identifier, String value) throws ThrillException{
		ThrillException e = new ThrillException("String constant " + identifier + " has invalid value " + value);
		throw e;
	}
}
