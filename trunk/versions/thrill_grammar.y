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
%token DIV              /* / */
%token LESS             /* < */
%token GREAT            /* > */
%token OPEN             /* ( */
%token CLOSE            /* ) */
%token Quote            /* " */

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
%type <dval> land_attributes
%type <sval> attraction_attributes
%type <sval> attraction_attribute
%type <sval> a1
%type <sval> a2
%type <sval> a3
%type <sval> a4
%type <sval> a5
%type <sval> restaurant_attributes
%type <sval> restaurant_attribute
%type <sval> r1
%type <sval> r2
%type <sval> r3
%type <sval> r4
%type <sval> r5
%type <sval> store_attributes
%type <sval> store_attribute
%type <sval> s1
%type <sval> s2
%type <sval> s3
%type <sval> s4
%%

program: definitions usercode;

definitions: crowd_definitions park_definition crowd_definitions;
crowd_definitions: crowd_definitions crowd_definition
                 | empty
                 ;

usercode: start; 

crowd_definition: Crowd  { createObj = true; }
			crowd_name { try { createObject($3); } catch(Exception ex) {} }
			crowd_elements { try{ setAttribute($3, $5);}catch(Exception ex){} };
                
crowd_elements: SEMICOLON {}
              | crowd_attributes { $$ = $1; }
              ;
              
crowd_attributes: crowd_attributes crowd_attribute { $$ += $2;}
                | empty	{}
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

park_definition: Park { createObj = true; }
		     park_name { try { createObject($3); } catch(Exception ex) {} }
		     park_elements { try{ setAttribute($3, $5);}catch(Exception ex){} };
   	 	     land_definitions

park_elements: SEMICOLON {}
             | park_attributes { $$ = $1; }
             ;
             
park_attributes: park_attributes park_attribute { $$ += $2; }
               | empty {}
               ;
               
park_attribute: p1 { $$ = $1; }
              | p2 { $$ = $1; }
              | p3 { $$ = $1; }              
              ;
               
p1: Set Admission NUMBER SEMICOLON { $$ = ":Admission:" + $3;};
p2: Set Capacity NUMBER SEMICOLON  { $$ = ":Capacity:" + $3;};
p3: Set Cost NUMBER SEMICOLON      { $$ = ":Cost:" + $3;};

land_definitions: land_definitions land_definition
                | empty {}
		    ;
land_definition: Land { createObj = true; }
		     land_name { try { createObject($3); } catch(Exception ex) {} }
		     land_attributes land_elements { setLocation($3, $5); } ;
land_attributes: Set Location NUMBER SEMICOLON { $$ = $3; };
land_elements: land_elements land_element
             | empty {}
		 ;
land_element: attraction_definition 
            | restaurant_definition 
            | store_definition 
            ;

attraction_definition: Attraction { createObj = true; }
			     attraction_name { try { createObject($3); } catch(Exception ex) {} }
			     In 
			     land_name { createAttractionDefinition($6, $3); }
			     attraction_attributes { try{ setAttribute($3, $8);}catch(Exception ex){} };
attraction_attributes: SEMICOLON {}
			   | attraction_attributes attraction_attribute { $$ += $2; }
                     | empty {}
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

restaurant_definition: Restaurant { createObj = true; }
			     restaurant_name { try { createObject($3); } catch(Exception ex) {} }
			     In 
  			     land_name { createRestaurantDefinition($6, $3); }
			     restaurant_attributes { try{ setAttribute($3, $8);}catch(Exception ex){} };
			     ;
restaurant_attributes: SEMICOLON {}
			   | restaurant_attributes restaurant_attribute { $$ += $2; }
                     | empty {}
			   ;
restaurant_attribute: r1 { $$ = $1; }
                    | r2 { $$ = $1; }
                    | r3 { $$ = $1; }
                    | r4 { $$ = $1; }
                    | r5{ $$ = $1; }
                    ;
r1: Set Cost NUMBER SEMICOLON 		{ $$ = ":Cost:" + $3;};
r2: Set Capacity NUMBER SEMICOLON;		{ $$ = ":Capacity:" + $3;};
r3: Set Employees NUMBER SEMICOLON;		{ $$ = ":Employees:" + $3;};
r4: Set SpendLevel NUMBER SEMICOLON;	{ $$ = ":SpendLevel:" + $3;};
r5: Set EnergyIncrease NUMBER SEMICOLON;	{ $$ = ":EnergyIncrease:" + $3;};

store_definition: Store  { createObj = true; }
			store_name { try { createObject($3); } catch(Exception ex) {} }
			In 
		      land_name { createStoreDefinition($6, $3); }
			store_attributes { try{ setAttribute($3, $8);}catch(Exception ex){} };
			;
store_attributes: SEMICOLON {}
		    | store_attributes store_attribute { $$ += $2; }
                | empty {}
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

start: Start COLON block;

function: return_type function_name COLON actual_parameters block function
        | empty
        ;
return_type: Number
           | String
           | empty
           ;
actual_parameters: data_type variable_name actual_parameter
                 | empty;
actual_parameter: COMMA actual_parameters
                | empty;

block: start_block statements end_block;
start_block: OPEN_PARAN;
end_block: CLOSE_PARAN;

statements: statements statement
           | empty;
statement: add_attraction_attribute
	   | add_crowd_attribute
	   | add_restaurant_attribute
	   | add_store_attribute
	   | assignment
	   | condition
         | declaration
	   | function_call
         | initialization
	   | thrill_functions
         ;

add_attraction_attribute: Set Cost value In attraction_name SEMICOLON
                        | Set Capacity value In attraction_name SEMICOLON
                        | Set Employees value In attraction_name SEMICOLON
                        | Set ThrillLevel value In attraction_name SEMICOLON
                        | Set EnergyLost value In attraction_name SEMICOLON
                        ;

add_crowd_attribute: Set Size value In crowd_name SEMICOLON
                   | Set EnergyLevel value In crowd_name SEMICOLON
                   | Set ThrillLevel value In crowd_name SEMICOLON
                   | Set SpendingCapacity value In crowd_name SEMICOLON
                   ;

add_restaurant_attribute: Set Cost value In restaurant_name SEMICOLON
                        | Set Capacity value In restaurant_name SEMICOLON
                        | Set Employees value In restaurant_name SEMICOLON
                        | Set SpendLevel value In restaurant_name SEMICOLON
                        | Set EnergyIncrease value In restaurant_name SEMICOLON
                        ;

add_store_attribute: Set Cost value SEMICOLON
                   | Set Capacity value In store_name SEMICOLON
                   | Set Employees value In store_name SEMICOLON
                   | Set SpendLevel value In store_name SEMICOLON
		       ;

assignment: left_side EQUAL right_side;
left_side: variable_name;
right_side: arithmetic_expression SEMICOLON 
 	   | function_call
	   ;
arithmetic_expression: arithmetic_expression PLUS arithmetic_expression
			   | arithmetic_expression MINUS arithmetic_expression
			   | arithmetic_expression MUL arithmetic_expression
			   | arithmetic_expression DIV arithmetic_expression
                     | OPEN arithmetic_expression CLOSE
                     | variable_name
                     | constant
                     ;

condition: If OPEN relational_expression CLOSE block
         | If OPEN relational_expression CLOSE block Else block ;
relational_expression: relational_expression LESSEQUAL relational_expression
			   | relational_expression GREATEQUAL relational_expression
			   | relational_expression NOTEQUAL relational_expression
			   | relational_expression LESS relational_expression
			   | relational_expression GREAT relational_expression
                     | OPEN relational_expression CLOSE
                     | variable_name
                     | constant
		         ;

declaration: primitive_type declaration_list;
declaration_list: variable_name COMMA declaration_list
                | variable_name SEMICOLON;

function_call: function_name COLON formal_parameters SEMICOLON;
formal_parameters: variable_name formal_parameter
                 | empty;
formal_parameter: COMMA formal_parameters 
                | empty;

initialization: primitive_type initialization_list;
initialization_list: variable_name EQUAL constant SEMICOLON 
		    | variable_name EQUAL constant COMMA initialization_list
		    ;

thrill_functions: calculate_revenue | output | simulate;
calculate_revenue: CalculateRevenue COLON Crowd crowd_name COMMA Duration duration_type;
duration_type: Days
             | Weeks
             | Months
             | Years;

output: Print constant_variable_chain;

simulate: Simulate COLON SEMICOLON;

constant_variable_chain: constant_or_variable PLUS constant_variable_chain
                       | constant_or_variable;

constant_or_variable: constant
                    | variable_name;

data_type: complex_type
         | primitive_type;

complex_type: Crowd
            | Duration;

primitive_type: Number
              | String;

constant: NUMBER
        | Quote ID Quote;

value: NUMBER
     | variable_name;

attraction_name: variable_name;
crowd_name: variable_name;
function_name: variable_name;
land_name: variable_name;
park_name: variable_name;
restaurant_name: variable_name;
store_name: variable_name;
variable_name: ID;

empty: ;

%%
	private Yylex lexer;
	private Hashtable<String, Object> thrillObjects = new Hashtable<String, Object>();
	Park parkObj = null;
	int noOfParks = 0, noOfLands = 0;
	boolean createObj = false;
	short keywordType = 0, attributeType = 0;

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
		System.out.println("yydebug = " + yydebug);
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

	public void createObject(String identifier) throws Exception{
		if(createObj){
			switch(keywordType){

			case Attraction:
				Attraction attraction = new Attraction();
				attraction.setAttractionName(identifier);
				if(thrillObjects.containsKey(identifier))
					ThrillException.redefinitionException(identifier);
				thrillObjects.put(identifier, attraction);
				break;

			case Crowd:
				Crowd crowd = new Crowd();
				crowd.setCrowdName(identifier);
				// check for redefinition
				if(thrillObjects.containsKey(identifier))
					ThrillException.redefinitionException(identifier);
				thrillObjects.put(identifier, crowd);

				break;

			case Land:
				if(noOfLands > 6)
					throw new Exception("Number of lands > 6");

				Land land = new Land();
				land.setLandName(identifier);
				// check for redefinition
				if(thrillObjects.containsKey(identifier))
					ThrillException.redefinitionException(identifier);
				thrillObjects.put(identifier, land);
				parkObj.addLand(land);
				land.setPark(parkObj);

				break;

			case Duration:
				//Duration duration = new Duration();
				break;

			case Park: 			 
				// We can have only one park object in the entire program
				if(noOfParks > 1)
					throw new Exception("Number of parks > 1");

				Park park = new Park();
				park.setParkName(identifier);
				// check for redefinition
				if(thrillObjects.containsKey(identifier))
					ThrillException.redefinitionException(identifier);
				thrillObjects.put(identifier, park);

				// Need a local copy of Park object in the Land object
				parkObj = park;

				break;

			case Restaurant:
				Restaurant restaurant = new Restaurant();
				restaurant.setRestaurantName(identifier);
				if(thrillObjects.containsKey(identifier))
					ThrillException.redefinitionException(identifier);
				thrillObjects.put(identifier, restaurant);

			case Store:
				Store store = new Store();
				store .setStoreName(identifier);
				if(thrillObjects.containsKey(identifier))
					ThrillException.redefinitionException(identifier);
				thrillObjects.put(identifier, store);
				break;
			}
			createObj = false;
		}
	}

	public void setLocation(String landName, double location){
		Land land = (Land)thrillObjects.get(landName);
		if(land == null) 
		{ 
			// grrrr.. this land has not been defined! throw an exception 
		}
		land.setLocation(location);
	}

	public void createAttractionDefinition(String landName, String attractioName) { 
		Land land = (Land)thrillObjects.get(landName);
		if(land == null) 
		{ 
			// grrrr.. this land has not been defined! throw an exception 
		}
		// Now we need to make sure that this store is add to the list of stores in
		// in the land object
		Attraction a = (Attraction)thrillObjects.get(attractioName);
		a.setLand(land);
		land.addAttraction(a);
	}

	public void createRestaurantDefinition(String landName, String restaurantName) { 
		Land land = (Land)thrillObjects.get(landName);
		if(land == null) 
		{ 
			// grrrr.. this land has not been defined! throw an exception 
		}
		// Now we need to make sure that this store is add to the list of stores in
		// in the land object
		Restaurant r = (Restaurant)thrillObjects.get(restaurantName);
		r.setLand(land);
		land.addRestaurant(r);
	}

	public void createStoreDefinition(String landName, String storeName) { 
		Land land = (Land)thrillObjects.get(landName);
		if(land == null) 
		{ 
			// grrrr.. this land has not been defined! throw an exception 
		}
		// Now we need to make sure that this store is add to the list of stores in
		// in the land object
		Store s = (Store)thrillObjects.get(storeName);
		s.setLand(land);
		land.addStore(s);
	}	

	public void setAttribute(String identifier, String allAttributes) throws ThrillException{
		Object obj = thrillObjects.get(identifier);
		if(obj instanceof Attraction){
			Attraction a = (Attraction)obj;
			if(a == null)
				ThrillException.objectNotFoundException(identifier);
			setAttractionAttribute(a, allAttributes);
		}		
		else if(obj instanceof Crowd){
			Crowd c = (Crowd)obj;
			if(c == null)
				ThrillException.objectNotFoundException(identifier);
			// set the attribute in the crowd object
			setCrowdAttribute(c, allAttributes);
		}
		else if(obj instanceof Land){
			// similar to Crowd			
		}
		else if(obj instanceof Park){
			Park p = (Park)obj;
			if(p == null)
				ThrillException.objectNotFoundException(identifier);
			// set the attribute in the crowd object
			setParkAttribute(p, allAttributes);
		}
		else if(obj instanceof Restaurant){
			Restaurant r = (Restaurant)obj;
			if(r == null)
				ThrillException.objectNotFoundException(identifier);
			setRestaurantAttribute(r, allAttributes);
		}
		else if(obj instanceof Store){
			Store s = (Store)obj;
			if(s == null)
				ThrillException.objectNotFoundException(identifier);
			setStoreAttribute(s, allAttributes);
		}
	}

	public void setAttractionAttribute(Attraction a, String allAttributes){
		String regex = ":";
		String[] attributes = allAttributes.split(regex);

		for(int i = 1; i < attributes.length; i+=2){
			if(attributes[i].equalsIgnoreCase("Cost")){ 				 
				a.setCost(Double.parseDouble(attributes[i + 1]));
			}
			else if(attributes[i].equalsIgnoreCase("Capacity")){
				a.setCapacity((int)Double.parseDouble(attributes[i + 1]));
			}
			else if(attributes[i].equalsIgnoreCase("Employees")){
				a.setEmployees((int)Double.parseDouble(attributes[i + 1]));
			}
			else if(attributes[i].equalsIgnoreCase("EnergyLost")){
				a.setEnergyLost((int)Double.parseDouble(attributes[i + 1]));
			}
			else{
				a.setThrillLevel((int)Double.parseDouble(attributes[i + 1]));
			}
		}
	}

	public void setCrowdAttribute(Crowd c, String allAttributes)
	{		
		String regex = ":";
		String[] attributes = allAttributes.split(regex);

		for(int i = 1; i < attributes.length; i+=2){
			if(attributes[i].equalsIgnoreCase("EnergyLevel")){ 				 
				c.setEnergyLevel((int)Double.parseDouble(attributes[i + 1]));
			}
			else if(attributes[i].equalsIgnoreCase("Size")){
				c.setSize((int)Double.parseDouble(attributes[i + 1]));
			}
			else if(attributes[i].equalsIgnoreCase("SpendingCapacity")){
				c.setSpendingCapacity(Double.parseDouble(attributes[i + 1]));
			}
			else{
				c.setThrillLevel((int)Double.parseDouble(attributes[i + 1]));
			}
		}	
	}

	public void setLandAttribute(Land a, String allAttributes){
		// similar to setCrowdAttribute()
	}

	public void setParkAttribute(Park p, String allAttributes){
		String regex = ":";
		String[] attributes = allAttributes.split(regex);

		for(int i = 1; i < attributes.length; i+=2){
			if(attributes[i].equalsIgnoreCase("Admission")){ 				 
				p.setAdmission((int)Double.parseDouble(attributes[i + 1]));
			}
			else if(attributes[i].equalsIgnoreCase("Capacity")){
				p.setCapacity((int)Double.parseDouble(attributes[i + 1]));
			}
			else{
				p.setCost(Double.parseDouble(attributes[i + 1]));
			}
		}
	}

	public void setRestaurantAttribute(Restaurant r, String allAttributes){
		String regex = ":";
		String[] attributes = allAttributes.split(regex);

		for(int i = 1; i < attributes.length; i+=2){
			if(attributes[i].equalsIgnoreCase("Capacity")){
				r.setCapacity((int)Double.parseDouble(attributes[i + 1]));
			}
			else if(attributes[i].equalsIgnoreCase(attributes[i + 1])){
				r.setCost(Double.parseDouble(attributes[i + 1]));
			}
			else if(attributes[i].equalsIgnoreCase("Employees")){
				r.setEmployees((int)Double.parseDouble(attributes[i + 1]));
			}
			else if(attributes[i].equalsIgnoreCase("EnergyLost")){
				r.setEnergyIncrease((int)Double.parseDouble(attributes[i + 1]));
			}
			else{
				r.setSpendLevel((int)Double.parseDouble(attributes[i + 1]));
			}
		}
	}

	public void setStoreAttribute(Store s, String allAttributes){
		String regex = ":";
		String[] attributes = allAttributes.split(regex);

		for(int i = 1; i < attributes.length; i+=2){
			if(attributes[i].equalsIgnoreCase("Capacity")){
				s.setCapacity((int)Double.parseDouble(attributes[i + 1]));
			}
			else if(attributes[i].equalsIgnoreCase(attributes[i + 1])){
				s.setCost(Double.parseDouble(attributes[i + 1]));
			}
			else if(attributes[i].equalsIgnoreCase("Employees")){
				s.setEmployees((int)Double.parseDouble(attributes[i + 1]));
			}
			else {
				s.setSpendLevel((int)Double.parseDouble(attributes[i + 1]));
			}
		}
	}

	public Hashtable<String, Object> getThrillObjects() {
		return thrillObjects;
	}

	public static void main(String args[]) throws IOException {
		System.out.println("THRLL programming language");

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
		
		Hashtable<String, Object> objects = yyparser.getThrillObjects();
		System.out.println("No .of objects = " + objects.size());

		System.out.println();
		System.out.println("Have a nice day");
	}