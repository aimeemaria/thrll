%{
import java.lang.Math;
import java.io.*;
import java.util.StringTokenizer;
import java.util.Hashtable;
%}

/* YACC Declarations */
%token EMPTY            /* newline  */
%token <dval> NUMBER    /* a number */
%token <sval> ID        /* a string */
%token WP               /* whitespace */
%token SEMICOLON        /* semicolon */
%token COMMA            /* comma separator */
%token COLON            /* colon separator */
%token OPEN_PARAN       /* { */ 
%token CLOSE_PARAN      /* } */
%token EQUAL            /* = */
%token NOTEQUAL         /* != */
%token LESSEQUAL        /* <= */
%token GREATEQUAL       /* >= */
%token ISEQUAL          /* == */
%token PLUS             /* + */
%token MINUS            /* - */
%token MUL              /* * */
%token <sval> DIV              /* / */
%token <sval> LESS             /* < */
%token <sval> GREAT            /* > */
%token OPEN             /* ( */
%token CLOSE            /* ) */
%token <sval> Quote            /* " */

/* Keywords */
%token Admission        	 /* Admission keyword        */
%token Attraction       	 /* Attraction keyword       */
%token <dval> Capacity         /* Capacity keyword         */
%token Crowd            	 /* Crowd keyword            */
%token <dval> Cost             /* Cost keyword             */
%token Duration         	 /* Duration keyword         */
%token Else             	 /* Else keyword             */
%token Employees        	 /* Employees keyword        */
%token EnergyIncrease   /* EnergyIncrease keyword   */
%token <dval>EnergyLevel      /* EnergyLevel keyword      */
%token EnergyLost       /* EnergyLost keyword       */
%token If               	 /* If keyword               */
%token In               	 /* In keyword               */
%token Iterate          	 /* Iterate keyword          */
%token Land             	 /* Land keyword             */
%token Location         	 /* Location keyword         */
%token <dval> Number   	 	 /* Number keyword           */
%token Park             	 /* Park keyword             */
%token Restaurant       	 /* Restaurant keyword       */
%token Return           	 /* Return keyword           */
%token Set              	 /* Set keyword              */
%token <dval>Size             /* Size keyword             */
%token SpendLevel       /* SpendLevel keyword       */
%token <dval> SpendingCapacity /* SpendingCapacity keyword */
%token Start            /* Start keyword            */
%token Store            /* Store keyword            */
%token String           /* String keyword           */
%token <dval> ThrillLevel      /* ThrillLevel keyword      */
%token Until            /* Until keyword            */

/* Functions */
%token CalculateRevenue /* CalculateRevenue function */
%token Print            /* Output function           */
%token Simulate         /* Simulate function         */

/* Duration */
%token Days             /* Number of days            */
%token Months           /* Number of months          */
%token Weeks            /* Number of weeks           */
%token Years            /* Number of years           */

/* Associativity and precedence */
%left MINUS PLUS
%left MUL DIV
%nonassoc EQUAL NOTEQUAL LESSEQUAL GREATEQUAL ISEQUAL LESS GREAT

%type <sval> attraction_name
%type <sval> crowd_name
%type <sval> function_name
%type <sval> land_name
%type <sval> park_name
%type <sval> restaurant_name
%type <sval> store_name
%type <sval> variable_name
%type <sval> crowd_attributes
%type <sval> crowd_attribute
%type <sval> crowd_elements
%type <sval> c1
%type <sval> c2
%type <sval> c3
%type <sval> c4
%type <sval> park_attributes
%type <sval> park_attribute
%type <sval> park_elements
%type <sval> p1
%type <sval> p2
%type <sval> p3
%type <sval> land_attributes
%type <sval> land_elements
%type <sval> land_element
%type <sval> attraction_definition
%type <sval> attraction_attributes
%type <sval> attraction_attribute
%type <sval> a1
%type <sval> a2
%type <sval> a3
%type <sval> a4
%type <sval> a5
%type <sval> restaurant_definition
%type <sval> restaurant_attributes
%type <sval> restaurant_attribute
%type <sval> r1
%type <sval> r2
%type <sval> r3
%type <sval> r4
%type <sval> r5
%type <sval> store_definition
%type <sval> store_attributes
%type <sval> store_attribute
%type <sval> s1
%type <sval> s2
%type <sval> s3
%type <sval> s4
%type <sval> value
%type <sval> constant
%type <sval> primitive_type
%type <sval> data_type
%type <sval> constant_or_variable
%type <sval> constant_variable_chain
%type <sval> simulate
%type <sval> output
%type <sval> duration_type
%type <sval> calculate_revenue
%type <sval> thrill_functions
%type <sval> duration_name
%type <sval> initialization_list
%type <sval> initialization
%type <sval> formal_parameters
%type <sval> function_call
%type <sval> declaration_list
%type <sval> declaration
%type <sval> relational_expression
%type <sval> arithmetic_expression
%type <sval> left_side
%type <sval> right_side
%type <sval> assignment
%type <sval> add_attribute
%type <sval> statement
%type <sval> statements
%type <sval> end_block
%type <sval> start_block
%type <sval> block
%type <sval> actual_parameters
%type <sval> return_type
%type <sval> function
%type <sval> start
%type <sval> loop
%type <sval> condition
%type <sval> usercode
%type <sval> functions
%type <sval> return
%type <sval> initialize_duration
%type <sval> crowd_definition
%type <sval> crowd_definitions
%type <sval> park_definition
%type <sval> land_definitions
%type <sval> land_definition
%type <sval> definitions
%type <sval> empty
%%

program: definitions usercode { generateThrillProgram($1, $2);};

definitions: crowd_definitions park_definition crowd_definitions {$$ = $1 + $2 + $3; } ;
crowd_definitions: crowd_definitions crowd_definition { $$ += $2; }
                 | empty { $$ = "";}
                 ;

usercode: start functions { $$ = $1 + $2; };

crowd_definition: Crowd crowd_name crowd_elements 
			{ addToHashtable($2, "Crowd"); 
			  $$ = "\nCrowd " + $2 + " = " + "new Crowd();\n" + generateSetAttribute($2, $3); 
			};
                
crowd_elements: SEMICOLON {}
              | crowd_attributes { $$ = $1; }
              ;
              
crowd_attributes: crowd_attributes crowd_attribute { $$ += $2;}
                | empty	{ $$ = "";}
                ;
                
crowd_attribute: c1 { $$ = $1; }
               | c2 { $$ = $1; }
               | c3 { $$ = $1; }
               | c4 { $$ = $1; }
               ;

c1: Set SpendingCapacity NUMBER SEMICOLON { $$ = ":SpendingCapacity:" + $3;};
c2: Set Size NUMBER SEMICOLON 		{ $$ = ":Size:" + $3; };
c3: Set EnergyLevel NUMBER SEMICOLON	{ $$ = ":EnergyLevel:" + $3;};
c4: Set ThrillLevel NUMBER SEMICOLON   	{ $$ = ":ThrillLevel:" + $3;};

park_definition: Park park_name park_elements { addToHashtable($2, "Park"); };
		     land_definitions { $$ = "\nPark " + $2 + " = " + "new Park();\n" + generateSetAttribute($2, $3) + $5; }

park_elements: SEMICOLON {}
             | park_attributes { $$ = $1; }
             ;
             
park_attributes: park_attributes park_attribute { $$ += $2; }
               | empty { $$ = ""; }
               ;
               
park_attribute: p1 { $$ = $1; }
              | p2 { $$ = $1; }
              | p3 { $$ = $1; }              
              ;
               
p1: Set Admission NUMBER SEMICOLON { $$ = ":Admission:" + $3;};
p2: Set Capacity NUMBER SEMICOLON  { $$ = ":Capacity:" + $3;};
p3: Set Cost NUMBER SEMICOLON      { $$ = ":Cost:" + $3;};

land_definitions: land_definitions land_definition { $$ += $2; }
                | empty { $$ = ""; }
		    ;
land_definition: Land land_name { addToHashtable($2, "Land"); } 
		     land_attributes 
		     land_elements {
					   $$ = "\nLand " + $2 + " = " + "new Land();\n" + generateSetAttribute($2, $4) + $5; 					   
					 } ;
land_attributes: Set Location NUMBER SEMICOLON { $$ = ":Location:" + $3; };
land_elements: land_elements land_element { $$ += $2; }
             | empty { $$ = ""; }
		 ;
land_element: attraction_definition { $$ = $1; }
            | restaurant_definition { $$ = $1; }
            | store_definition 	{ $$ = $1; }
            ;

attraction_definition: Attraction attraction_name { addToHashtable($2, "Attraction"); }
			     In land_name
			     attraction_attributes { $$ = createAttractionDefinition($5, $2) + generateSetAttribute($2, $6); };
attraction_attributes: SEMICOLON { $$ = ""; }
			   | attraction_attributes attraction_attribute { $$ += $2; }
                     | empty { $$ = ""; }
                     ;
attraction_attribute: a1 { $$ = $1; }
                    | a2 { $$ = $1; }
                    | a3 { $$ = $1; }
                    | a4 { $$ = $1; }
                    | a5 { $$ = $1; }
                    ;                     
a1: Set Cost NUMBER SEMICOLON 	 { $$ = ":Cost:" + $3;};
a2: Set Capacity NUMBER SEMICOLON 	 { $$ = ":Capacity:" + $3;};
a3: Set Employees NUMBER SEMICOLON 	 { $$ = ":Employees:" + $3;};
a4: Set ThrillLevel NUMBER SEMICOLON { $$ = ":ThrillLevel:" + $3;};
a5: Set EnergyLost NUMBER SEMICOLON  { $$ = ":EnergyLost:" + $3;};

restaurant_definition: Restaurant restaurant_name { addToHashtable($2, "Restaurant"); }
			     In land_name
			     restaurant_attributes { $$ = createRestaurantDefinition($5, $2) + generateSetAttribute($2, $6); }
			     ;
restaurant_attributes: SEMICOLON { $$ = ""; }
			   | restaurant_attributes restaurant_attribute { $$ += $2; }
                     | empty { $$ = ""; }
			   ;
restaurant_attribute: r1 { $$ = $1; }
                    | r2 { $$ = $1; }
                    | r3 { $$ = $1; }
                    | r4 { $$ = $1; }
                    | r5 { $$ = $1; }
                    ;
r1: Set Cost NUMBER SEMICOLON 		{ $$ = ":Cost:" + $3;};
r2: Set Capacity NUMBER SEMICOLON;		{ $$ = ":Capacity:" + $3;};
r3: Set Employees NUMBER SEMICOLON;		{ $$ = ":Employees:" + $3;};
r4: Set SpendLevel NUMBER SEMICOLON;	{ $$ = ":SpendLevel:" + $3;};
r5: Set EnergyIncrease NUMBER SEMICOLON;	{ $$ = ":EnergyIncrease:" + $3;};

store_definition: Store store_name { addToHashtable($2, "Store"); }
			In land_name
			store_attributes { $$ = createStoreDefinition($5, $2) + generateSetAttribute($2, $6); }
			;
store_attributes: SEMICOLON { $$ = ""; }
		    | store_attributes store_attribute { $$ += $2; }
                | empty { $$ = ""; }
		    ;
store_attribute: s1 { $$ = $1; }
               | s2 { $$ = $1; }
               | s3 { $$ = $1; }
               | s4 { $$ = $1; }
               ;
               
s1: Set Cost NUMBER SEMICOLON		 { $$ = ":Cost:" + $3;};
s2: Set Capacity NUMBER SEMICOLON;	 { $$ = ":Capacity:" + $3;};
s3: Set Employees NUMBER SEMICOLON;	 { $$ = ":Employees:" + $3;};
s4: Set SpendLevel NUMBER SEMICOLON; { $$ = ":SpendLevel:" + $3;}; 

start: Start COLON block { $$ = $3; };

functions: functions function { $$ = $1 + $2; }
	   | empty { $$ = ""; }
	   ;

function: return_type function_name COLON actual_parameters block 
	  { $$ = "\n" + $1 + " " + $2 + "(" + $4 + ")\n" + $5; }
        ;
return_type: Number { $$ = "double"; }
           | String { $$ = "String"; }
           | empty {$$ = "void"; }
           ;
actual_parameters: actual_parameters COMMA data_type variable_name { $$ = $1 + ", " + $3 + " " + $4; }
		     | data_type variable_name { $$ = $1 + " " + $2; }
                 | empty { $$ = ""; }
		     ;

block: start_block statements end_block { $$ = "{" + $2 + "\n}"; }
     ;
start_block: OPEN_PARAN;
end_block: CLOSE_PARAN;

statements: statements statement { $$ = $$ + "\n" +  $2; }
          | empty { $$ = ""; }
	    ;
statement: add_attribute 	 { $$ = $1; }
	   | assignment 	 	 { $$ = $1; }
	   | condition 	 	 { $$ = $1; }
         | declaration 	 	 { $$ = $1; }
	   | function_call 	 { $$ = $1; }
         | initialization 	 { $$ = $1; }
	   | initialize_duration { $$ = $1; }
	   | loop 			 { $$ = $1; }
	   | return 		 { $$ = $1; }
	   | thrill_functions 	 { $$ = $1; }
         ;

add_attribute: Set Capacity value In variable_name SEMICOLON		{ $$ = generateAttribute($5, "Capacity", $3); }
		 | Set Cost value In variable_name SEMICOLON			{ $$ = generateAttribute($5, "Cost", $3); }
		 | Set Employees value In variable_name SEMICOLON		{ $$ = generateAttribute($5, "Employees", $3); }
		 | Set EnergyIncrease value In variable_name SEMICOLON	{ $$ = generateAttribute($5, "EnergyIncrease", $3); }
	       | Set EnergyLevel value In variable_name SEMICOLON		{ $$ = generateAttribute($5, "EnergyLevel", $3); }
             | Set EnergyLost value In variable_name SEMICOLON		{ $$ = generateAttribute($5, "EnergyLost", $3); }
		 | Set Size value In variable_name SEMICOLON			{ $$ = generateAttribute($5, "Size", $3); }
             | Set SpendingCapacity value In variable_name SEMICOLON	{ $$ = generateAttribute($5, "SpendingCapacity", $3); }
		 | Set SpendLevel value In variable_name SEMICOLON		{ $$ = generateAttribute($5, "SpendLevel", $3); }
             | Set ThrillLevel value In variable_name SEMICOLON	      { $$ = generateAttribute($5, "ThrillLevel", $3); }
		 ;

assignment: left_side EQUAL right_side { $$ = $1 + " = " + $3;};
left_side: variable_name { $$ = $1; } ;
right_side: arithmetic_expression SEMICOLON { $$ = $1 + ";"; }
 	   | function_call { $$ = $1; }
	   | calculate_revenue { $$ = $1; System.out.print($$);}
	   ;
arithmetic_expression: arithmetic_expression PLUS arithmetic_expression  { $$ = $1 + "+" + $3; }
			   | arithmetic_expression MINUS arithmetic_expression { $$ = $1 + "-" + $3; }
			   | arithmetic_expression MUL arithmetic_expression 	 { $$ = $1 + "*" + $3; }
			   | arithmetic_expression DIV arithmetic_expression 	 { $$ = $1 + "/" + $3; }
                     | OPEN arithmetic_expression CLOSE 			 { $$ = "(" + $2 + ")"; }
                     | variable_name 						 { $$ = $1; }
                     | constant 							 { $$ = $1; }
                     ;

condition: If OPEN relational_expression CLOSE block
	     { $$ = "if(" + $3 + ")" + $5; }
         | If OPEN relational_expression CLOSE block Else block
	     { $$ = "if(" + $3 + ")" + $5 + "\nelse" + $7; }
	   ;
relational_expression: variable_name LESSEQUAL constant_or_variable  { $$ = $1 + " <= " + $3;}
			   | variable_name GREATEQUAL constant_or_variable { $$ = $1 + " >= " + $3;}
			   | variable_name NOTEQUAL constant_or_variable   { $$ = $1 + " != " + $3;}
			   | variable_name LESS constant_or_variable 	   { $$ = $1 + " < " + $3;}
			   | variable_name GREAT constant_or_variable 	   { $$ = $1 + " > " + $3;}
		         ;

declaration: primitive_type declaration_list SEMICOLON { $$ = $1 + " " + $2 + ";"; }
declaration_list: declaration_list COMMA variable_name { $$ = $1 + ", " + $3; }
                | variable_name { $$ = $1; }
		    ;

function_call: function_name COLON formal_parameters SEMICOLON 
		 { $$ = $1 + "(" + $3 + ");" ; }
		 ;

formal_parameters: formal_parameters COMMA variable_name { $$ = $$ + "," + $3; }
                 | variable_name {$$ = $1;}
		     | empty { $$ = ""; }
		     ;

initialization: primitive_type initialization_list SEMICOLON 
                { $$ = $1 + " " + $2 + ";"; }
		  ;

initialization_list: initialization_list COMMA variable_name EQUAL constant 
                     { $$ = $1 + ", " + $3 + "=" + $5; }
		       | variable_name EQUAL constant 
			   { $$ = $1 + " = " + $3; }
		       ;

initialize_duration: duration_type variable_name EQUAL NUMBER SEMICOLON { $$ = initializeDuration($1, $2, new Double($4).toString() ); }

loop: Iterate block Until OPEN relational_expression CLOSE SEMICOLON 
      {$$ = "do" + $2 + "while (" + $5 + ");" ; }
    ;

return: Return constant_or_variable SEMICOLON { $$ = "return " + $2 + ";"; };

thrill_functions: calculate_revenue { $$ = $1; }
                  | output { $$ = $1; } 
                  | simulate { $$ = $1; }
			;

calculate_revenue: CalculateRevenue COLON crowd_name COMMA duration_name SEMICOLON
			 {$$ = generateRevenue($3, $5) ; }
		     	;

output: Print constant_variable_chain SEMICOLON { $$ = "System.out.println(" + $2 + ");" ; };

simulate: Simulate COLON crowd_name SEMICOLON {$$ = generateSimulate($3); };

constant_variable_chain: constant_variable_chain COMMA constant_or_variable 
				 { $$ = $1 + "+" + $3; }
                       | constant_or_variable 
				 { $$ = $1; }
			     ;

constant_or_variable: constant { $$ = "\"" + $1 + "\""; }
                    | variable_name { $$ = $1; }
			  ;

data_type: Crowd { $$ = "Crowd"; }
         | primitive_type { $$ = $1; }
	   ;

primitive_type: Number 		{ $$ = "double"; }
              | String 		{ $$ = "String"; }
		  ;

duration_type: Days   { $$ = "Days"; }
             | Weeks  { $$ = "Weeks"; }
             | Months { $$ = "Months"; }
             | Years  { $$ = "Years"; }
		 ;

constant: NUMBER { $$ = new Double($1).toString(); }
        | Quote ID Quote { $$ = "\"" + $2 + "\""; }
	  ;

value: NUMBER 	   { $$ = new Double($1).toString(); }
     | variable_name { $$ = $1; }
     ;

attraction_name: variable_name { $$ = $1; } ;
crowd_name: variable_name 	 { $$ = $1; } ;
duration_name: variable_name   { $$ = $1; } ;
function_name: variable_name 	 { $$ = $1; } ;
land_name: variable_name 	 { $$ = $1; } ;
park_name: variable_name 	 { $$ = $1; } ;
restaurant_name: variable_name { $$ = $1; } ;
store_name: variable_name 	 { $$ = $1; } ;
variable_name: ID 		 { $$ = $1; } ;

empty: { $$ = ""; } ;

%%
	private Yylex lexer;
	private Hashtable<String, String> thrillObjects = new Hashtable<String, String>();
	int noOfParks = 0, noOfLands = 0;
	short keywordType = 0, attributeType = 0;
	String parkName = null;

	private int yylex () {
		int yyl_return = -1;
		try {
			yylval = new ParserVal(0);
			yyl_return = lexer.yylex();
		}
		catch (IOException e) {
			System.err.println("IO error:" +e);
		}
		return yyl_return;
	}

	public void yyerror (String error) {
		System.err.println ("Error: " + error);
	}

	public Parser(Reader r) {
		//yydebug = true;
		//System.out.println("yydebug = " + yydebug);
		lexer = new Yylex(r, this);
	}

	static boolean interactive;

	public void setAttributeType(short val){
		attributeType = val;
	}

	public short getAttributeType(){
		return attributeType;
	}

	public void setKeywordType(short val){
		keywordType = val;
	}

	public short getKeywordType(){
		return keywordType;
	}

	public String createAttractionDefinition(String landName, String attractionName) { 
		String result = "\nAttraction " + attractionName + " = new Attraction();\n";
		if(!thrillObjects.containsKey(landName)){
			// ThrillException.ObjectNotFoundException(landName);
		}
		
		String setName = attractionName + ".setAttractionName(\"" + attractionName + "\");\n";
		String setLand = attractionName + ".setLand(" + landName + ");\n";
		String addAttraction = landName + ".addAttraction(" + attractionName + ");\n";
		result += setName + setLand + addAttraction;
		
		return result;
	}

	public String createRestaurantDefinition(String landName, String restaurantName) { 
		String result = "\nRestaurant " + restaurantName + " = new Restaurant();\n";
		if(!thrillObjects.containsKey(landName)){
			// ThrillException.ObjectNotFoundException(landName);
		}
		
		String setName = restaurantName + ".setRestaurantName(\"" + restaurantName + "\");\n";
		String setLand = restaurantName + ".setLand(" + landName + ");\n";
		String addRestaurant = landName + ".addRestaurant(" + restaurantName + ");\n";
		result += setName + setLand + addRestaurant;
		
		return result;
	}

	public String createStoreDefinition(String landName, String storeName) { 
		String result = "\nStore " + storeName + " = new Store();\n";
		if(!thrillObjects.containsKey(landName)){
			// ThrillException.ObjectNotFoundException(landName);
		}
		
		String setName = storeName + ".setStoreName(\"" + storeName + "\");\n";
		String setLand = storeName + ".setLand(" + landName + ");\n";
		String addStore = landName + ".addStore(" + storeName + ");\n";
		result += setName + setLand + addStore;
		
		return result;
	}

	public void addToHashtable(String identifier, String type){
		if(!thrillObjects.containsKey(identifier)){
			// ThrillException.RedefinitionException(identifier);
		}
		if(type == "Park")
			parkName = identifier;
		
		//System.out.println("Adding " + identifier + " Type = " + type);
		thrillObjects.put(identifier, type);
	}

	public String generateSetAttribute(String identifier, String allAttributes){
		String result = "";
		String obj = thrillObjects.get(identifier);
		if(obj == null){
			// ThrillException.ObjectNotFoundException(identifier);
		}
		
		if(obj.equalsIgnoreCase("Attraction")){
			result += generateAttractionAttribute(identifier, allAttributes);
		}		
		else if(obj.equalsIgnoreCase("Crowd")){
			result += generateCrowdAttribute(identifier, allAttributes);
		}
		else if(obj.equalsIgnoreCase("Land")){
			result += generateLandAttribute(identifier, allAttributes);
		}
		else if(obj.equalsIgnoreCase("Park")){
			result += generateParkAttribute(identifier, allAttributes);
		}	
		else if(obj.equalsIgnoreCase("Restaurant")){
			result += generateRestaurantAttribute(identifier, allAttributes);
		}
		else if(obj.equalsIgnoreCase("Store")){
			result += generateStoreAttribute(identifier, allAttributes);
		}
				
		return result;
	}

	public String generateAttractionAttribute(String a, String allAttributes){
		String result = null;
		String regex = ":";		
		String[] attributes = allAttributes.split(regex);

		for(int i = 1; i < attributes.length; i+=2){
			String value = validateAttributeValue(attributes[i], attributes[i+1]);
			result += a + ".set" + attributes[i] + "(" + value + ");\n";
		}
		
		return result;
	}

	public String generateCrowdAttribute(String c, String allAttributes)
	{		
		String regex = ":";
		String result = c + ".setCrowdName(\"" + c + "\");\n";
		String[] attributes = allAttributes.split(regex);

		for(int i = 1; i < attributes.length; i+=2){
			String value = validateAttributeValue(attributes[i], attributes[i+1]);
			result += c + ".set" + attributes[i] + "(" + value + ");\n";
		}
		
		return result;
	}
	
	public String generateLandAttribute(String l, String allAttributes){
		String regex = ":";
		String[] attributes = allAttributes.split(regex);
		String result = l + ".setLandName(\"" + l + "\");\n";
		String setPark = l + ".setPark(" + parkName + ");\n";
		String addLand = parkName + ".addLand(" + l + ");\n";
		result += setPark + addLand;
		//System.out.println(result);
		for(int i = 1; i < attributes.length; i+=2){
			String value = validateAttributeValue(attributes[i], attributes[i+1]);
			result += l + ".set" + attributes[i] + "(" + value + ");\n";
		}		
		return result;
	}

	public String generateParkAttribute(String p, String allAttributes){
		String regex = ":";
		String[] attributes = allAttributes.split(regex);
		String result = p + ".setParkName(\"" + p + "\");\n"; 
		
		for(int i = 1; i < attributes.length; i+=2){
			String value = validateAttributeValue(attributes[i], attributes[i+1]);
			result += p + ".set" + attributes[i] + "(" + value + ");\n";
		}
		
		return result;
	}

	public String generateRestaurantAttribute(String r, String allAttributes){
		String regex = ":";
		String[] attributes = allAttributes.split(regex);
		String result = "";

		for(int i = 1; i < attributes.length; i+=2){
			String value = validateAttributeValue(attributes[i], attributes[i+1]);
			result += r + ".set" + attributes[i] + "(" + value + ");\n";
		}		
		
		return result;
	}

	public String generateStoreAttribute(String s, String allAttributes){
		String regex = ":";
		String[] attributes = allAttributes.split(regex);
		String result = "";

		for(int i = 1; i < attributes.length; i+=2){
			String value = validateAttributeValue(attributes[i], attributes[i+1]);
			result += s + ".set" + attributes[i] + "(" + value + ");\n";
		}		
		
		return result;
	}

	public Hashtable<String, String> getThrillObjects() {
		return thrillObjects;
	}
	
	public String generateAttribute(String variable, String function, String value) {
		String result = "";;
		
		value = validateAttributeValue(function, value);
		
		Object obj = thrillObjects.get(variable);
		
		if(obj == null){
			// ThrillException.ObjectNotFoundException(variable);
		}
		else{
			if(function.equalsIgnoreCase("Capacity")){
				if(obj instanceof Crowd){
					//ThrillException.UnexpectedTypeException(variable, "Crowd");
				}
				result = variable.concat(".set" + function + "(" + value + ");");
			}
			else if(function.equalsIgnoreCase("Cost")){
				if(obj instanceof Crowd){
					//ThrillException.UnexpectedTypeException(variable, "Crowd");
				}
				result = variable.concat(".set" + function + "(" + value + ");");
			}
			else if(function.equalsIgnoreCase("Employees")){
				if(obj instanceof Crowd){
					//ThrillException.UnexpectedTypeException(variable, "Crowd");
				}
				result = variable.concat(".set" + function + "(" + value + ");");
			}
			else if(function.equalsIgnoreCase("EnergyIncrease")){
				if(!(obj instanceof Restaurant)){
					//ThrillException.UnexpectedTypeException("Restaurant", variable);
				}
				result = variable.concat(".set" + function + "(" + value + ");");

			}
			else if(function.equalsIgnoreCase("EnergyLevel")){
				if(!(obj instanceof Crowd)){
					//ThrillException.UnexpectedTypeException("Crowd", variable);
				}
				result = variable.concat(".set" + function + "(" + value + ");");				
			}
			else if(function.equalsIgnoreCase("EnergyLost")){
				if(!(obj instanceof Attraction)){
					//ThrillException.UnexpectedTypeException("Attraction", variable);
				}
				result = variable.concat(".set" + function + "(" + value + ");");
			}
			else if(function.equalsIgnoreCase("Size")){
				if(!(obj instanceof Crowd)){
					//ThrillException.UnexpectedTypeException("Crowd", variable);
				}
				result = variable.concat(".set" + function + "(" + value + ");");				
			}
			else if(function.equalsIgnoreCase("SpendingCapacity")){
				if(!(obj instanceof Crowd)){
					//ThrillException.UnexpectedTypeException("Crowd", variable);
				}
				result = variable.concat(".set" + function + "(" + value + ");");				
			}
			else if(function.equalsIgnoreCase("SpendLevel")){
				if(!(obj instanceof Restaurant || obj instanceof Store)){
					//ThrillException.UnexpectedTypeException("Restaurant/Store", variable);
				}
				result = variable.concat(".set" + function + "(" + value + ");");				
			}
			else if(function.equalsIgnoreCase("ThrillLevel")){
				if(!(obj instanceof Attraction || obj instanceof Crowd)){
					//ThrillException.UnexpectedTypeException("Attraction/Crowd", variable);
				}
				result = variable.concat(".set" + function + "(" + value + ");");				
			}
			else{
				// error condition
				// ThrillException.UnexpectedTypeException("Attraction/Crowd/Restaurant/Store", variable);
			}
		}
		
		return result;
	}
	
	public String validateAttributeValue(String function, String value){
		String result = "";;
		
		double d = Double.parseDouble(value);
		
		if(function.equalsIgnoreCase("Admission") ||
		   function.equalsIgnoreCase("Cost") || 
		   function.equalsIgnoreCase("SpendingCapacity")){	
			if(d < 0)
				throw new IllegalArgumentException(function + " cannot be less than zero");
			result = value;
		}
		else{
			int i = (int)d;
			if(function.equalsIgnoreCase("Location")){
				if(i < 1 || i > 6)
					throw new IllegalArgumentException(function + " cannot be less than zero");				
			}
			else if(function.equalsIgnoreCase("Capacity")  || 
			   function.equalsIgnoreCase("Employees") ||
			   function.equalsIgnoreCase("Size")){
				if(i < 0)
					throw new IllegalArgumentException(function + " cannot be less than zero");
				
			}
			else {
				if(i < 0 || i > 20)
					throw new IllegalArgumentException(function + "cannot be less than 0 or greater than 20");
			}
			result = new Integer(i).toString();
		}
		return result;
	}

	// have to check the second argument as well
	public String generateRevenue(String crowdName, String duration) {
		String result = null;
		try {
			String c = thrillObjects.get(crowdName);
			if(c == null){
				ThrillException.ObjectNotFoundException(crowdName);
			}
			result = "calculateRevenue(" + crowdName + ", " + duration + ")";
		}catch(ThrillException oe){
			//throw oe;
		}
		catch(Exception ex){
			// ThrillException.UnexpectedTypeException("Crowd", crowdName);
		}
		System.out.println(result);
		return result;
	}

	public String generateSimulate(String crowdName) {
		String result = null;
		try {
			String c = thrillObjects.get(crowdName);
			if(c == null){
				ThrillException.ObjectNotFoundException(crowdName);
			}
			result = "simulate(" + crowdName + ");";
		}catch(ThrillException oe){
			//throw oe;
		}
		catch(Exception ex){
			// ThrillException.UnexpectedTypeException("Crowd", crowdName);
		}
		return result;
	}

	public void generateThrillProgram(String definitions, String usercode){
		String classStart = "public class ThrillProgram {\n";
		String classEnd = "\n}";
		String main = "public static void main(){\n";		
		usercode = usercode.substring(1);
		
		try{
			FileWriter writer = new FileWriter(new File("ThrillProgram.java"));
			String buffer = classStart + main + definitions +  usercode + classEnd;
			writer.write(buffer);
			writer.close();
		}catch(IOException io){			
		}		
	}

	public String initializeDuration(String durationType, String durationName, String value){
		String result = null;
		double temp = Double.parseDouble(value);
		int days = (int)temp;
		result = durationType + " " + durationName + " = new " + durationType + "(" + days + ");"; 
		return result;
	}

	public static void main(String args[]) throws IOException {
		System.out.println("\n\n\t\tTHRLL programming language\n\n");

		Parser yyparser;
		if ( args.length > 0 ) {
			// parse a file
			yyparser = new Parser(new FileReader(args[0]));
		}
		else {
			// interactive mode
			System.out.println("[Quit with CTRL-D]");
			System.out.println("Input");
			interactive = true;
			yyparser = new Parser(new InputStreamReader(System.in));
		}
		
		yyparser.yyparse();
		
		Hashtable<String, String> objects = yyparser.getThrillObjects();
		//System.out.println("No .of objects = " + objects.size());
	
		System.out.println("\n\n\t\tHave a nice day\n\n");
	}