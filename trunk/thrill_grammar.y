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
program: elements_definition start function;
elements_definition: crowd_definition park_definition | park_definition crowd_definition;

crowd_definition: Create crowd_elements | empty;
crowd_elements: Crowd crowd_name SEMICOLON | Crowd crowd_name crowd_attributes;
crowd_attributes: c1 | c2 | c3 | c4 | empty;
c1: Set Size NUMBER SEMICOLON crowd_attributes;
c2: Set EnergyLevel NUMBER SEMICOLON crowd_attributes;
c3: Set ThrillLevel NUMBER SEMICOLON crowd_attributes;
c4: Set SpendingCapacity NUMBER SEMICOLON crowd_attributes;

park_definition: Create park_elements;
park_elements: Park park_name SEMICOLON | Park park_name park_attributes land_definition;
park_attributes:  p1 | p2 | p3 | empty;
p1: Set Admission NUMBER SEMICOLON park_attributes;
p2: Set Capacity NUMBER SEMICOLON park_attributes;
p3: Set Cost NUMBER SEMICOLON park_attributes;

land_definition: Create Land land_name land_attributes land_elements;
land_attributes: Set Location NUMBER SEMICOLON;
land_elements:  attraction_definition | restaurant_definition | store_definition | empty;
 
attraction_definition: Create Attraction attraction_name In land_name attraction_attributes SEMICOLON land_elements;
attraction_attributes:  a1 | a2 | a3| a4 | a5 | empty;
a1: Set Cost NUMBER SEMICOLON attraction_attributes;
a2: Set Capacity NUMBER SEMICOLON attraction_attributes;
a3: Set Employees NUMBER SEMICOLON attraction_attributes;
a4: Set Thrill NUMBER SEMICOLON attraction_attributes;
a5: Set EnergyLost NUMBER SEMICOLON attraction_attributes;

restaurant_definition: Create Restaurant restaurant_name In land_name restaurant_attributes SEMICOLON land_elements;
restaurant_attributes:  r1 | r2 | r3 | r4 | r5 | empty;
r1: Set Cost NUMBER SEMICOLON restaurant_attributes;
r2: Set Capacity NUMBER SEMICOLON restaurant_attributes;
r3: Set Employees NUMBER SEMICOLON restaurant_attributes;
r4: Set SpendLevel NUMBER SEMICOLON restaurant_attributes;
r5: Set EnergyIncrease NUMBER SEMICOLON restaurant_attributes;

store_definition: Create Store store_name In land_name store_attributes SEMICOLON land_elements;
store_attributes:  s1 | s2 | s3 | s4 | empty;
s1: Set Cost NUMBER SEMICOLON store_attributes;
s2: Set Capacity NUMBER SEMICOLON store_attributes;
s3: Set Employees NUMBER SEMICOLON store_attributes;
s4: Set SpendLevel NUMBER SEMICOLON store_attributes;

start: Start COLON block;

function: return_type function_name COLON actual_parameters block function | empty;
return_type: Number | String | empty;
actual_parameters: data_type variable_name actual_parameter | empty;
actual_parameter: COMMA actual_parameters | empty;

block: start_block statements end_block;
start_block: OPEN_PARAN;
end_block: CLOSE_PARAN;
statements : statement statements | empty;
statement: add_attraction_attribute 
         | add_crowd_attribute
         | add_restaurant_attribute 
         | add_store_attribute 
         | assignment
         | condition 
         | declaration
         | function_call
         | initialization
         | loop
         | thrill_functions
         ;

add_attraction_attribute: Set Cost value In attraction_name SEMICOLON |
                          Set Capacity value In attraction_name SEMICOLON |
                          Set Employees value In attraction_name SEMICOLON |
                          Set Thrill value In attraction_name SEMICOLON |
                          Set EnergyLost value In attraction_name SEMICOLON ;

add_crowd_attribute:      Set Size value In crowd_name SEMICOLON |
                          Set EnergyLevel value In crowd_name SEMICOLON |
                          Set ThrillLevel value In crowd_name SEMICOLON |
                          Set SpendingCapacity value In crowd_name SEMICOLON;

add_restaurant_attribute: Set Cost value In restaurant_name SEMICOLON |
                          Set Capacity value In restaurant_name SEMICOLON |
                          Set Employees value In restaurant_name SEMICOLON |
                          Set SpendLevel value In restaurant_name SEMICOLON |
                          Set EnergyIncrease value In restaurant_name SEMICOLON ;
                          
add_store_attribute:      Set Cost value In store_name SEMICOLON |
                          Set Capacity value In store_name SEMICOLON |
                          Set Employees value In store_name SEMICOLON |
                          Set SpendLevel value In store_name SEMICOLON ;

assignment: left_side EQUAL right_side SEMICOLON;
left_side : variable_name;
right_side : arithmetic_expression | function_call;
arithmetic_expression:  arithmetic_expression arithmetic_operator arithmetic_expression | OPEN arithmetic_expression CLOSE | variable_name | constant;
arithmetic_operator : PLUS | MINUS | DIV | MUL;

condition: If OPEN relational_expression CLOSE block | If OPEN relational_expression CLOSE block Else block ;
relational_expression : relational_expression relational_operator relational_expression | OPEN relational_expression CLOSE |
variable_name | constant;
relational_operator : LESSEQUAL | GREATEQUAL | NOTEQUAL | ISEQUAL | EQUAL | LESS | GREAT;

declaration : primitive_type declaration_list;
declaration_list: variable_name COMMA declaration_list | variable_name SEMICOLON;

function_call: function_name COLON formal_parameters SEMICOLON;
formal_parameters: variable_name formal_parameter | empty;
formal_parameter: COMMA formal_parameters | empty;

initialization : primitive_type initialization_list;
initialization_list : variable_name EQUAL constant SEMICOLON | variable_name EQUAL constant COMMA initialization_list;

loop: Iterate block Until OPEN relational_expression CLOSE SEMICOLON;

/* I guess we need to write SDD to handle this part */
thrill_functions: calculate_revenue | output | simulate;
calculate_revenue: CalculateRevenue COLON Crowd crowd_name COMMA Duration duration_type;
duration_type: Days | Weeks | Months | Years;
output: Print constant_variable_chain;
constant_variable_chain: constant_or_variable PLUS constant_variable_chain | constant_or_variable;
constant_or_variable: constant | variable_name;
constant: NUMBER | Quote ID Quote;
simulate: Simulate COLON SEMICOLON;

attraction_name: ID;
crowd_name: ID;
function_name: ID;
land_name: ID;
park_name: ID;
restaurant_name: ID;
store_name: ID;
variable_name: ID;
value: NUMBER | variable_name;
complex_type: Crowd | Duration;
data_type: primitive_type | complex_type;
primitive_type: Number | String;

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
    //yydebug = true;
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
  
  
