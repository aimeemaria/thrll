%{
import java.lang.Math;
import java.io.*;
import java.util.StringTokenizer;
import java.util.Hashtable;

%}

/* YACC Declarations */
%token EMPTY            /* newline  */
%token <dval> NUMBER    /* a number */
%token ID               /* a string */
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
%token Admission        /* Admission keyword        */
%token Attraction       /* Attraction keyword       */
%token Capacity         /* Capacity keyword         */
%token Create           /* Create keyword           */
%token Crowd            /* Crowd keyword            */
%token Cost             /* Cost keyword             */
%token Duration         /* Duration keyword         */
%token Else             /* Else keyword             */
%token Employees        /* Employees keyword        */
%token EnergyIncrease   /* EnergyIncrease keyword   */
%token EnergyLevel      /* EnergyLevel keyword      */
%token EnergyLost       /* EnergyLost keyword       */
%token If               /* If keyword               */
%token In               /* In keyword               */
%token Iterate          /* Iterate keyword          */
%token Land             /* Land keyword             */
%token Location         /* Location keyword         */
%token Number           /* Number keyword           */
%token Park             /* Park keyword             */
%token Restaurant       /* Restaurant keyword       */
%token Return           /* Return keyword           */
%token Set              /* Set keyword              */
%token Size             /* Size keyword             */
%token SpendLevel       /* SpendLevel keyword       */
%token SpendingCapacity /* SpendingCapacity keyword */
%token Start            /* Start keyword            */
%token Store            /* Store keyword            */
%token String           /* String keyword           */
%token Thrill           /* Thrill keyword           */
%token ThrillLevel      /* ThrillLevel keyword      */
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
%left '-' '+'
%left '*' '/'

/* Grammar follows */
%%

program: definitions code;

definitions: park_definition

code: empty;

park_definition: Create { createObj = true; addKeyword("Create"); } 
		     Park { addKeyword("Park"); }
		     park_name { addIdentifier(); }
		     park_elements
		   ;

park_elements: park_attributes
		 | SEMICOLON 
             ;
             
park_attributes: park_attributes park_attribute 
               | empty
               ;
               
park_attribute: p1 
              | p2 
              | p3               
              ;
               
p1: Set Admission NUMBER { parkObj.setAdmission(yylval.dval); } SEMICOLON; 	
p2: Set Capacity NUMBER { parkObj.setCapacity(yylval.dval); } SEMICOLON;		
p3: Set Cost NUMBER { parkObj.setCost(yylval.dval); } SEMICOLON;

attraction_name: ID;
crowd_name: ID;
function_name: ID;
land_name: ID;
park_name: ID;
restaurant_name: ID;
store_name: ID;
variable_name: ID;

empty: ;

%%
  private Yylex lexer;
  private Hashtable<String, String> keywords;
  private String identifier = null;
  Park parkObj = null;
  boolean createObj = false;
  short keywordType = 0;
  
  private int yylex () {
    int yyl_return = -1;
    try {
      yylval = new ParserVal(0);
      yyl_return = lexer.yylex();
    }
    catch (IOException e) {
      System.err.println("IO error :" +e);
    }
    return yyl_return;
  }
  
  public void yyerror (String error) {
    System.err.println ("Error: " + error);
  }

  public Parser(Reader r) {
    yydebug = true;
    System.out.println("yydebug = " + yydebug);
    lexer = new Yylex(r, this);
    keywords = new Hashtable<String, String>();
  }
  
  static boolean interactive;

  public void addKeyword(String keyword)
  {
	try {
    	    if(!keywords.containsKey(keyword))
        	keywords.put(keyword, "keyword");
	}
	catch(Exception ex){
	}
  }

  public void setIdentifier(String id)
  {
	identifier = id;
  }

  public void addIdentifier()
  {
	createObject();

	if(identifier != null){
		try {
			if(!keywords.containsKey(identifier))
				keywords.put(identifier, "identfier");			
		}
		catch(Exception ex){
		}
		finally{
			identifier = null;
		}
	}
  }

  public void setKeywordType(short val){
	keywordType = val;
  }

  public short getKeywordType(){
  	return keywordType;
  }

  public void createObject(){
	  if(createObj){
		  switch(keywordType){
		  case Park:
			  parkObj = new Park();
			  break;
			  
		  case Land:
			  //landObj = new Land();
			  break;
			  
		  case Crowd:
			  //crowdObj = new Crowd();
			  break;
			  
		  case Duration:
			  //durationObj = new Duration();
			  break;
		  case Store:
			  storeObj = new Store();
			  symbolTable.add(storeName, storeObj);
			  Store s = symbolTable.getValue(storeName);
		  }
	  createObj = false;
	  }
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
    
    if (interactive) {
      System.out.println();
      System.out.println("Have a nice day");
    }
  }
