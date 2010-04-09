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
%token <sval>EnergyLevel      /* EnergyLevel keyword      */
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
%token <sval>Size             /* Size keyword             */
%token SpendLevel       /* SpendLevel keyword       */
%token <sval> SpendingCapacity /* SpendingCapacity keyword */
%token Start            /* Start keyword            */
%token Store            /* Store keyword            */
%token String           /* String keyword           */
%token <sval> ThrillLevel      /* ThrillLevel keyword      */
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
%type <sval> crowd_elements
%type <sval> crowd_attributes
%type <sval> crowd_attribute
%type <sval> c1
%type <sval> c2
%type <sval> c3
%type <sval> c4
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
              
crowd_attributes: crowd_attributes crowd_attribute { $$ = $$ + $2; System.out.println("Crowd attrs " + $$); }
                | empty	{}
                ;
                
crowd_attribute: c1 { $$ = $1; }
               | c2 { $$ = $1; }
               | c3 { $$ = $1; }
               | c4 { $$ = $1; }
               ;

c1: Set SpendingCapacity NUMBER SEMICOLON { $$ = $2 + " " + $3 + ":"; System.out.println("Crowd attrs " + $$); };
c2: Set Size NUMBER SEMICOLON 		{ $$ = $2 + " " + $3 + ":"; System.out.println("Crowd attrs " + $$); };
c3: Set EnergyLevel NUMBER SEMICOLON	{ $$ = $2 + " " + $3 + ":"; System.out.println("Crowd attrs " + $$); };
c4: Set ThrillLevel NUMBER SEMICOLON   	{ $$ = $2 + " " + $3 + ":"; System.out.println("Crowd attrs " + $$); };

park_definition: Park { createObj = true; }
		     park_name { try { createObject($3); } catch(Exception ex) {} }
		     park_elements
   	 	     land_definitions

park_elements: SEMICOLON {}
             | park_attributes 
             ;
             
park_attributes: park_attributes park_attribute 
               | empty {}
               ;
               
park_attribute: p1 
              | p2 
              | p3               
              ;
               
p1: Set Admission NUMBER { } SEMICOLON;
p2: Set Capacity NUMBER { } SEMICOLON;
p3: Set Cost NUMBER { } SEMICOLON;

land_definitions: land_definitions land_definition
                | empty
		    ;
land_definition: Land { createObj = true; }
		     land_name { try { createObject($3); } catch(Exception ex) {} }
		     land_attributes land_elements { setLocation($3, $5); } ;
land_attributes: SEMICOLON {}
		   | Set Location NUMBER SEMICOLON { $$ = $3; };
land_elements: land_elements land_element
             | empty;
land_element: attraction_definition 
            | restaurant_definition 
            | store_definition 
            ;

attraction_definition: Attraction attraction_name In land_name attraction_attributes;
attraction_attributes: SEMICOLON
			   | attraction_attributes attraction_attribute
                     | empty
                     ;
attraction_attribute: a1 
                    | a2 
                    | a3
                    | a4 
                    | a5
                    ;                     
a1: Set Cost NUMBER SEMICOLON;
a2: Set Capacity NUMBER SEMICOLON;
a3: Set Employees NUMBER SEMICOLON;
a4: Set ThrillLevel NUMBER SEMICOLON;
a5: Set EnergyLost NUMBER SEMICOLON;

restaurant_definition: Restaurant restaurant_name In land_name restaurant_attributes;
restaurant_attributes: SEMICOLON
			   | restaurant_attributes restaurant_attribute
                     | empty
			   ;
restaurant_attribute: r1 
                    | r2 
                    | r3 
                    | r4 
                    | r5
                    ;
r1: Set Cost NUMBER SEMICOLON; 
r2: Set Capacity NUMBER SEMICOLON;
r3: Set Employees NUMBER SEMICOLON;
r4: Set SpendLevel NUMBER SEMICOLON;
r5: Set EnergyIncrease NUMBER SEMICOLON;

store_definition: Store  { createObj = true; }
			store_name { try { createObject($3); } catch(Exception ex) {} }
			In 
		      land_name { createStoreDefinition($6, $3); }
			store_attributes;
store_attributes: SEMICOLON
		    | store_attributes store_attribute
                | empty
		    ;
store_attribute: s1 
               | s2 
               | s3 
               | s4
               ;
               
s1: Set Cost NUMBER SEMICOLON;
s2: Set Capacity NUMBER SEMICOLON;
s3: Set Employees NUMBER SEMICOLON;
s4: Set SpendLevel NUMBER SEMICOLON;

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
				parkObj = park;

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

			case Crowd:
				Crowd crowd = new Crowd();
				crowd.setCrowdName(identifier);
				// check for redefinition
				if(thrillObjects.containsKey(identifier))
					ThrillException.redefinitionException(identifier);
				thrillObjects.put(identifier, crowd);
				
				break;

			case Duration:
				//Duration duration = new Duration();
				break;

			case Store:
				Store store = new Store();
				store .setStoreName(identifier);
				if(thrillObjects.containsKey(identifier))
					ThrillException.redefinitionException(identifier);
				thrillObjects.put(identifier, store);
				break;

			case Attraction:
				// Attraction attraction = new Attraction();
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

	public void createAttractionDefinition() { 
		// add code similar to createStoreDefinition
	}
	
	public void createRestaurantDefinition() { 
		// add code similar to createStoreDefinition
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

 	public void setAttribute(String identifier, double value) throws ThrillException{
		Object obj = thrillObjects.get(identifier);
		if(obj instanceof Attraction){
			// similar to Crowd
		}		
		else if(obj instanceof Crowd){
			Crowd c = (Crowd)thrillObjects.get(identifier);
			if(c == null)
				ThrillException.objectNotFoundException(identifier);
			// set the attribute in the crowd object
			setCrowdAttribute(c, value);
		}
		else if(obj instanceof Land){
			// similar to Crowd			
		}
		else if(obj instanceof Restaurant){
			// similar to Crowd
		}
		else if(obj instanceof Store){
			// similar to Crowd
		}
 	}
 	
 	public void setAttractionAttribute(Attraction a, double value){
 		// similar to setCrowdAttribute()
 	}
 	
 	public void setCrowdAttribute(Crowd c, double value)
 	{
			switch(attributeType){
			case EnergyLevel:
				c.setEnergyLevel(value);
				break;
			case Size:
				c.setSize(value);
				break;
			case SpendingCapacity:
				c.setSpendingCapacity(value);
				break;				
			case ThrillLevel:
				c.setThrillLevel(value);
				break;	
		}		
	}
 	
 	public void setLandAttribute(Land a, double value){
 		// similar to setCrowdAttribute()
 	}
 	
 	public void setRestaurantAttribute(Restaurant r, double value){
 		// similar to setCrowdAttribute()
 	}
 	
 	public void setStoreAttribute(Store s, double value){
 		// similar to setCrowdAttribute()
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
    
      System.out.println();
      System.out.println("Have a nice day");
  }
