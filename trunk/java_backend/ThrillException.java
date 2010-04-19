// this class file implements the Thrill Exception type
public class ThrillException extends Exception {
	private String message = null;
	
	public ThrillException(String message){
		this.message = message;
	}
	
	public static void ExceededObjectLimitException(String lineInfo, String object, int limit) throws ThrillException{
		ThrillException e = new ThrillException(lineInfo + object + " definition has exceeded the maximum limit " + limit);
		throw e;
	}
	
	public static void RedefinitionException(String lineInfo, String identifier) throws ThrillException{
		ThrillException e = new ThrillException(lineInfo + identifier + " already defined");
		throw e;
	}
	
	public static void ObjectNotFoundException(String lineInfo, String identifier) throws ThrillException{
		ThrillException e = new ThrillException(lineInfo + " definition of identifier " + identifier + " not known");
		throw e;
	}
	
	public static void UnexpectedTypeException(String lineInfo, String expectedObjType, String foundObjType) throws ThrillException{
		ThrillException e = new ThrillException(lineInfo + " expected object type " + expectedObjType + ". Found type " + foundObjType);
		throw e;
	}
	
	public static void InvalidStringConstantException(String lineInfo, String identifier, String value) throws ThrillException{
		ThrillException e = new ThrillException(lineInfo + " string constant " + identifier + " has invalid value " + value);
		throw e;
	}
	
	public static void InvalidArgumentException(String lineInfo, String message) throws ThrillException{
		ThrillException e = new ThrillException(lineInfo + message);
		throw e;
	}
	
	public String getMessage() {
		return message;
	}
}
