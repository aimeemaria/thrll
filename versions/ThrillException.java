// this class file implements the Thrill Exception type
import java.util.Hashtable;

public class ThrillException extends Exception {
	private String message = null;
	private String lineInfo = null;

	public ThrillException(String message){
		this.message = message;
	}
	
	public ThrillException(String lineInfo, String message){
		this.lineInfo = lineInfo;
		this.message = message;
	}

	public static ThrillException ExceededObjectLimitException(String lineInfo, String object, int limit) throws ThrillException{
		ThrillException e = new ThrillException(lineInfo, lineInfo + object + " definition has exceeded the maximum limit " + limit);
		return e;		
	}

	public static ThrillException AlreadyDefinedLocationException(String lineInfo, int location) throws ThrillException {
		ThrillException e = new ThrillException(lineInfo, lineInfo + "location " + location + " has already been set before.");
		return e;
	}
	
	public static ThrillException RedefinitionException(String lineInfo, String identifier) throws ThrillException{
		ThrillException e = new ThrillException(lineInfo, lineInfo + "'" + identifier + "' already defined");
		return e;
	}

	public static ThrillException ObjectNotFoundException(String lineInfo, String identifier) throws ThrillException{
		ThrillException e = new ThrillException(lineInfo, lineInfo + " definition of identifier '" + identifier + "' not known");
		return e;
	}

	public static ThrillException UnexpectedTypeException(String lineInfo, String expectedObjType, String foundObjType) throws ThrillException{
		ThrillException e = new ThrillException(lineInfo, lineInfo + " expected object type '" + expectedObjType + "'. Found type '" + foundObjType + "'");
		return e;
	}

	public static ThrillException InvalidStringConstantException(String lineInfo, String identifier, String value) throws ThrillException{
		ThrillException e = new ThrillException(lineInfo, lineInfo + " string constant '" + identifier + "' has invalid value " + value);
		return e;
	}

	public static ThrillException InvalidArgumentException(String lineInfo, String message) throws ThrillException{
		ThrillException e = new ThrillException(lineInfo, lineInfo + message);
		return e;
	}

	public static ThrillException MissingReturnStatementException(String lineInfo, String message) throws ThrillException{
		ThrillException e = new ThrillException(lineInfo, lineInfo + message);
		return e;
	}

	public static ThrillException UndefinedFunctionException(String lineInfo, String functionName, String[] types) throws ThrillException{
		String paramsList = "";
		ThrillException e = null;
		
		for(int i = 0; i < types.length - 1; ++i){
			paramsList += types[i] + ", ";
		}
		
		paramsList += types[types.length - 1];
		
		if(types != null && types[0] != null){
			e = new ThrillException(lineInfo, "Function type: '" + functionName + "(" + paramsList + ")' undefined in the source");
		}
		else
			e = new ThrillException(lineInfo, "Function type: '" + functionName + "()' undefined in the source");
		
		return e;
	}

	public static ThrillException InsufficientParamsException(String lineInfo, String functionName) throws ThrillException{
		ThrillException e = new ThrillException(lineInfo, lineInfo + "'" + functionName + "' has insufficient parameters");
		throw e;
	}

	public static ThrillException TypesMismatchException(String lineInfo, String type1, String type2) throws ThrillException{
		ThrillException e = new ThrillException(lineInfo, lineInfo + " type mismatch between '" + type1 + "' and '" + type2 + "'");
		return e;
	}
	
	public String getMessage() {
		return message;
	}
}
