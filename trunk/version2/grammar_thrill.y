%{
import java.lang.Math;
import java.io.*;
import java.util.StringTokenizer;
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

program: definitions usercode;

definitions: crowd_definitions park_definition crowd_definitions;
crowd_definitions: crowd_definitions crowd_definition   
                 | empty
                 ;

usercode: empty;

crowd_definition: Crowd crowd_name crowd_elements;
                
crowd_elements: SEMICOLON 
              | crowd_attributes
              ;
              
crowd_attributes: crowd_attributes crowd_attribute
                | empty
                ;
                
crowd_attribute: c1
               | c2
               | c3 
               | c4 
               ;

c1: Set SpendingCapacity NUMBER SEMICOLON;
c2: Set Size NUMBER SEMICOLON;
c3: Set EnergyLevel NUMBER SEMICOLON;            
c4: Set ThrillLevel NUMBER SEMICOLON;


park_definition: Park park_name park_elements land_definitions

park_elements: SEMICOLON 
             | park_attributes 
             ;
             
park_attributes: park_attributes park_attribute 
               | empty
               ;
               
park_attribute: p1 
              | p2 
              | p3               
              ;
               
p1: Set Admission NUMBER SEMICOLON;
p2: Set Capacity NUMBER SEMICOLON;
p3: Set Cost NUMBER SEMICOLON;

land_definitions: land_definitions land_definition
                | empty
		    ;
land_definition: Land land_name land_attributes land_elements;
land_attributes: Set Location NUMBER SEMICOLON;
land_elements: land_elements land_element
             | empty;
land_element: attraction_definition 
            | restaurant_definition 
            | store_definition 
            ;

attraction_definition: Attraction attraction_name In land_name attraction_attributes SEMICOLON;
attraction_attributes: attraction_attributes attraction_attribute
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

restaurant_definition: Restaurant restaurant_name In land_name restaurant_attributes SEMICOLON;
restaurant_attributes: restaurant_attributes restaurant_attribute
                     | empty;
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

store_definition:  Store store_name In land_name store_attributes SEMICOLON;
store_attributes: store_attributes store_attribute
                | empty; 

store_attribute: s1 
               | s2 
               | s3 
               | s4 
               ;
               
s1: Set Cost NUMBER SEMICOLON;
s2: Set Capacity NUMBER SEMICOLON;
s3: Set Employees NUMBER SEMICOLON;
s4: Set SpendLevel NUMBER SEMICOLON;

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
  }
  
  static boolean interactive;
  
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
