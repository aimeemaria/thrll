
// class to represent the user defined function calls
// which will make it easier to figure out if the function
// has been defined
public class ThrillUserFunction {
	private int line;
	private String functionName;
	private String[] formalParameters;
	private String scopeName;
	private int parameters;

	public ThrillUserFunction(String functionName){
		this.functionName = functionName;
	}

	public int getLine() {
		return line;
	}

	public void setLine(int line) {
		this.line = line;
	}

	public String[] getFormalParameters() {
		return formalParameters;
	}

	public void setFormalParameters(String[] formalParameters) {
		this.formalParameters = formalParameters;
	}

	public String getScopeName() {
		return scopeName;
	}

	public void setScopeName(String scopeName) {
		this.scopeName = scopeName;
	}

	public int getParameters() {
		return parameters;
	}

	public void setParameters(int parameters) {
		this.parameters = parameters;
	}

	public String getFunctionName() {
		return functionName;
	}
}