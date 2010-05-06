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

	public static void AlreadyDefinedLocationException(String lineInfo, int location) throws ThrillException {
		ThrillException e = new ThrillException(lineInfo + "location " + location + " has already been set before.");
		throw e;
	}
	
	public static void RedefinitionException(String lineInfo, String identifier) throws ThrillException{
		ThrillException e = new ThrillException(lineInfo + "'" + identifier + "' already defined");
		throw e;
	}

	public static void ObjectNotFoundException(String lineInfo, String identifier) throws ThrillException{
		ThrillException e = new ThrillException(lineInfo + " definition of identifier '" + identifier + "' not known");
		throw e;
	}

	public static void UnexpectedTypeException(String lineInfo, String expectedObjType, String foundObjType) throws ThrillException{
		ThrillException e = new ThrillException(lineInfo + " expected object type '" + expectedObjType + "'. Found type '" + foundObjType + "'");
		throw e;
	}

	public static void InvalidStringConstantException(String lineInfo, String identifier, String value) throws ThrillException{
		ThrillException e = new ThrillException(lineInfo + " string constant '" + identifier + "' has invalid value " + value);
		throw e;
	}

	public static void InvalidArgumentException(String lineInfo, String message) throws ThrillException{
		ThrillException e = new ThrillException(lineInfo + message);
		throw e;
	}

	public static void MissingReturnStatementException(String lineInfo, String message) throws ThrillException{
		ThrillException e = new ThrillException(lineInfo + message);
		throw e;
	}

	public static void UndefinedFunctionException(String functionName, String[] types) throws ThrillException{
		String paramsList = "";
		
		for(int i = 0; i < types.length - 1; ++i){
			paramsList += types[i] + ", ";
		}
		
		paramsList += types[types.length - 1];
		
		ThrillException e = new ThrillException("Function type: '" + functionName + "(" + paramsList + ")' undefined in the source");
		throw e;
	}

	public static void InsufficientParamsException(String lineInfo, String functionName) throws ThrillException{
		ThrillException e = new ThrillException(lineInfo + "'" + functionName + "' has insufficient parameters");
		throw e;
	}

	public static void TypesMismatchException(String lineInfo, String type1, String type2) throws ThrillException{
		ThrillException e = new ThrillException(lineInfo + " type mismatch between '" + type1 + "' and '" + type2 + "'");
		throw e;
	}
	
	public String getMessage() {
		return message;
	}
}
