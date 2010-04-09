//### This file created by BYACC 1.8(/Java extension  1.15)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";






//#line 2 "thrill_grammar.y"
import java.lang.Math;
import java.io.*;
import java.util.StringTokenizer;
import java.util.Hashtable;
//#line 22 "Parser.java"




public class Parser
             implements ParserTokens
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//public class ParserVal is defined in ParserVal.java


String   yytext;//user variable to return contextual strings
ParserVal yyval; //used to return semantic vals from action routines
ParserVal yylval;//the 'lval' (result) I got from yylex()
ParserVal valstk[];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
void val_init()
{
  valstk=new ParserVal[YYSTACKSIZE];
  yyval=new ParserVal();
  yylval=new ParserVal();
  valptr=-1;
}
void val_push(ParserVal val)
{
  if (valptr>=YYSTACKSIZE)
    return;
  valstk[++valptr]=val;
}
ParserVal val_pop()
{
  if (valptr<0)
    return new ParserVal();
  return valstk[valptr--];
}
void val_drop(int cnt)
{
int ptr;
  ptr=valptr-cnt;
  if (ptr<0)
    return;
  valptr = ptr;
}
ParserVal val_peek(int relative)
{
int ptr;
  ptr=valptr-relative;
  if (ptr<0)
    return new ParserVal();
  return valstk[ptr];
}
final ParserVal dup_yyval(ParserVal val)
{
  ParserVal dup = new ParserVal();
  dup.ival = val.ival;
  dup.dval = val.dval;
  dup.sval = val.sval;
  dup.obj = val.obj;
  return dup;
}
//#### end semantic value section ####
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,   81,   82,   82,   78,   86,   87,   84,   11,   11,
    9,    9,   10,   10,   10,   10,   12,   13,   14,   15,
   88,   89,   91,   83,   18,   18,   16,   16,   17,   17,
   17,   19,   20,   21,   90,   90,   93,   94,   92,   22,
   95,   95,   96,   96,   96,  100,  101,  102,   97,   23,
   23,   23,   24,   24,   24,   24,   24,   25,   26,   27,
   28,   29,  103,  104,  105,   98,   30,   30,   30,   31,
   31,   31,   31,   31,   32,   33,   34,   35,   36,  106,
  107,  108,   99,   37,   37,   37,   38,   38,   38,   38,
   39,   40,   41,   42,   75,   79,   79,   74,   73,   73,
   73,   72,   72,   72,   71,   70,   69,   68,   68,   67,
   67,   67,   67,   67,   67,   67,   67,   67,   66,   66,
   66,   66,   66,   66,   66,   66,   66,   66,   65,   63,
   64,   64,   64,   62,   62,   62,   62,   62,   62,   62,
   77,   77,   61,   61,   61,   61,   61,   60,   59,   59,
   58,   57,   57,   57,   56,   55,   55,   76,   80,   53,
   53,   53,   52,   50,   49,   48,   48,   47,   47,   46,
   46,   45,   45,   45,   51,   51,   51,   51,   44,   44,
   43,   43,    1,    2,   54,    3,    4,    5,    6,    7,
    8,   85,
};
final static short yylen[] = {                            2,
    2,    3,    2,    1,    2,    0,    0,    5,    1,    1,
    2,    1,    1,    1,    1,    1,    4,    4,    4,    4,
    0,    0,    0,    7,    1,    1,    2,    1,    1,    1,
    1,    4,    4,    4,    2,    1,    0,    0,    6,    4,
    2,    1,    1,    1,    1,    0,    0,    0,    8,    1,
    2,    1,    1,    1,    1,    1,    1,    4,    4,    4,
    4,    4,    0,    0,    0,    8,    1,    2,    1,    1,
    1,    1,    1,    1,    4,    4,    4,    4,    4,    0,
    0,    0,    8,    1,    2,    1,    1,    1,    1,    1,
    4,    4,    4,    4,    3,    2,    1,    5,    1,    1,
    1,    4,    2,    1,    3,    1,    1,    2,    1,    1,
    1,    1,    1,    1,    1,    1,    1,    1,    6,    6,
    6,    6,    6,    6,    6,    6,    6,    6,    3,    1,
    2,    1,    1,    3,    3,    3,    3,    3,    1,    1,
    5,    7,    3,    3,    3,    3,    3,    3,    3,    1,
    4,    3,    1,    1,    3,    5,    3,    7,    3,    1,
    1,    1,    6,    3,    4,    3,    1,    1,    1,    1,
    1,    1,    1,    1,    1,    1,    1,    1,    1,    3,
    1,    1,    1,    1,    1,    1,    1,    1,    1,    1,
    1,    0,
};
final static short yydefred[] = {                       192,
    0,    0,    0,    4,    0,  192,    1,    6,   21,  192,
    3,    0,    0,   97,    0,    0,    0,  106,  192,   95,
   99,  100,    0,   96,  101,  191,    7,  184,   22,  188,
    0,  109,    0,  186,    0,    0,  107,    0,    0,  172,
    0,    0,  173,    0,    0,    0,  175,  177,  176,  178,
    0,    0,    0,  162,  161,  174,  160,  118,  115,  114,
  113,    0,  111,  110,  108,  105,  116,  112,  117,    0,
    9,    0,    8,   12,   25,    0,   23,   28,    0,    0,
  179,    0,  169,  168,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  167,    0,    0,    0,
    0,    0,    0,    0,  170,  171,    0,    0,  104,    0,
   11,   13,   14,   15,   16,    0,   27,   29,   30,   31,
  192,    0,    0,    0,    0,  159,  181,  182,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  164,
    0,    0,  153,    0,  154,    0,  155,    0,  148,    0,
    0,    0,  140,  133,  132,    0,  129,  103,    0,   98,
    0,    0,    0,    0,    0,    0,    0,   36,    0,    0,
    0,    0,    0,    0,    0,    0,  180,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  166,  165,
  151,    0,  157,    0,  149,  139,    0,  131,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   37,   35,  145,  143,  144,  146,  147,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  185,
    0,  152,    0,  138,    0,    0,  136,  137,  102,   19,
   18,   17,   20,   32,   33,   34,    0,    0,    0,  119,
  120,  121,  122,  123,  124,  125,  127,  126,  128,  163,
  156,   38,  187,  142,  158,    0,    0,  192,    0,   42,
    0,    0,   46,   63,   80,   41,   43,   44,   45,   40,
    0,    0,    0,   47,  183,   64,  189,   81,  190,    0,
    0,    0,    0,    0,    0,   48,   65,   82,    0,    0,
    0,   50,    0,   52,   67,    0,   69,   84,    0,   86,
    0,   51,   53,   54,   55,   56,   57,    0,   68,   70,
   71,   72,   73,   74,    0,   85,   87,   88,   89,   90,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   59,   58,
   60,   62,   61,   76,   75,   77,   79,   78,   92,   91,
   93,   94,
};
final static short yydgoto[] = {                          1,
  284,   27,   51,  262,   29,  286,  288,  128,   72,  111,
   73,  112,  113,  114,  115,   76,  117,   77,  118,  119,
  120,  268,  303,  312,  313,  314,  315,  316,  317,  306,
  319,  320,  321,  322,  323,  324,  309,  326,  327,  328,
  329,  330,  129,   84,  106,  107,   85,   98,   54,   55,
   56,   57,   58,  231,  102,   59,  144,   60,  103,   61,
  123,  156,   62,  157,   63,   64,   65,   31,   66,   19,
   20,  108,   23,   24,    6,   67,   68,    7,   13,   69,
    2,    3,   10,   11,    4,   15,   35,   16,   36,  169,
  121,  212,  247,  266,  271,  276,  277,  278,  279,  281,
  290,  299,  282,  291,  300,  283,  292,  301,
};
final static short yysindex[] = {                         0,
    0, -294, -151,    0, -249,    0,    0,    0,    0,    0,
    0, -213, -280,    0, -236, -236, -228,    0,    0,    0,
    0,    0, -236,    0,    0,    0,    0,    0,    0,    0,
  -83,    0, -172,    0, -165, -144,    0, -155, -213,    0,
 -247,    1,    0, -125, -247, -118,    0,    0,    0,    0,
 -102,    0, -236,    0,    0,    0,    0,    0,    0,    0,
    0, -133,    0,    0,    0,    0,    0,    0,    0, -278,
    0, -137,    0,    0,    0, -121,    0,    0, -236, -116,
    0,  -69,    0,    0,  -67, -145, -145, -145, -145, -145,
 -145, -145, -145, -145, -145, -236,    0,  -78, -236, -236,
  -64,  -61,  -47, -239,    0,    0, -236, -184,    0,  -82,
    0,    0,    0,    0,    0, -159,    0,    0,    0,    0,
    0,  -70,  -60,  -73,  -59,    0,    0,    0,  -68,  -58,
  -53,  -31,  -26,  -19,  -18,  -17,  -16,   -8,  -40,    0,
 -247,   11,    0,  -20,    0, -234,    0, -236,    0, -236,
 -130,    0,    0,    0,    0,   26,    0,    0, -278,    0,
  -13,   20,   24,   28,   34,   43,   47,    0,   15, -247,
 -247, -247, -247, -247, -213, -236,    0, -236, -236, -236,
 -236, -236, -236, -236, -236, -236, -236, -236,    0,    0,
    0, -236,    0,   44,    0,    0,   52,    0, -130, -130,
 -130, -130, -236,   50,   51,   66,   67,   68,   70,   71,
    0,    0,    0,    0,    0,    0,    0,   48,   55,   74,
   75,   76,   77,   78,   79,   80,   81,   82,   83,    0,
   84,    0, -234,    0, -119, -119,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0, -236, -213,   85,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   49,   53,    0,   89,    0,
 -237,   90,    0,    0,    0,    0,    0,    0,    0,    0,
 -236, -236, -236,    0,    0,    0,    0,    0,    0,   58,
   60,   61, -236, -236, -236,    0,    0,    0,   93,   94,
   95,    0,   57,    0,    0,   59,    0,    0,   62,    0,
 -241,    0,    0,    0,    0,    0,    0, -107,    0,    0,
    0,    0,    0,    0,  -91,    0,    0,    0,    0,    0,
  100,  102,  103,  105,  106,  107,  108,  109,  110,  111,
  112,  113,  114,  115,  116,  117,  118,  119,  120,  121,
  122,  123,  124,  125,  126,  127,  128,  129,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    4,    0,    0,    0,   72,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0, -221, -194,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0, -179,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0, -114,
    0, -250,    0,    0,    0, -223,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  -10,
    3,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  -25,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0, -262,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  -56,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   -1,   35,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
 -178,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0, -231, -231,
 -231,    0, -206,    0,    0, -186,    0,    0, -174,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,
};
final static short yygindex[] = {                         0,
    0,  -74,  351, -142,    0,    0,    0,  -14,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  227,  -98,  344,  232,  -30,    0,    0,    0,
    0,  288,    0,    0,    0,    0,    0,  289,    0,    0,
  218, -143,    0,    0,    0,    0,    0,    0,    0,    0,
  -36,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  385,    0,    0,   -6,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,
};
final static int YYTABLESIZE=395;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         14,
   28,   30,   80,    5,  105,  153,   25,  197,   34,    5,
   81,   26,   32,   12,   97,   21,   52,   40,   81,   26,
   24,  139,   26,   81,  142,   22,   83,   43,   74,   78,
   83,   82,   10,   47,   48,   49,   50,  151,  101,   82,
  331,   24,  332,  273,   82,  333,   10,  193,  334,  192,
   18,  192,  153,   10,    8,  235,  236,  237,  238,   26,
  274,  192,  192,  109,  122,  335,  192,  275,  192,   44,
   26,  160,  192,  192,   49,  192,   49,  159,  192,   18,
   26,   28,  192,  186,   28,  143,  130,   49,  192,  152,
   70,   49,  158,  145,   66,   71,   66,   49,   49,  192,
  153,  153,  153,  153,   39,  192,   83,   66,   83,  192,
  189,   66,  127,   26,  168,   39,   75,   66,   66,   83,
  165,   79,  166,   83,  167,   39,   83,   81,   26,   83,
   83,    8,  104,  194,  261,  195,  196,   96,  218,  213,
  214,  215,  216,  217,   99,    9,  151,  192,   82,  192,
  296,  297,  298,  201,  202,   83,   83,   83,   83,   83,
  100,  122,  110,  220,  221,  222,  223,  224,  225,  226,
  227,  228,  229,  230,  336,   26,  337,  232,  116,  338,
  339,   37,  140,  141,  196,  196,  196,  196,  239,  125,
  341,  124,  342,  126,  340,  343,  170,  171,  172,  147,
  148,  146,  141,  176,  173,  174,  161,   38,  141,   39,
  344,  264,   40,  149,  150,   41,   42,  175,  162,  177,
  163,  188,   43,  178,  164,   44,   45,   46,   47,   48,
   49,   50,  263,  179,  141,  139,  141,  186,  180,  141,
  191,  192,  141,  141,  204,  139,  139,  139,  139,  141,
  192,  192,  141,  141,  141,  141,  141,  141,  141,  134,
  181,  270,  192,  150,  150,  182,  285,  287,  289,  134,
  134,  190,  183,  184,  185,  186,  134,  205,  263,  263,
  263,  206,   86,  187,   87,  207,  198,   88,   89,   90,
   91,  208,  304,  307,  310,  135,  199,  200,  201,  202,
  209,   92,   93,   94,  210,  135,  135,   95,  211,  233,
  240,  241,  135,  130,  131,  132,  133,  134,  135,  136,
  137,  138,  199,  200,  201,  202,  242,  243,  244,  234,
  245,  246,  249,  248,  250,  251,  252,  253,  254,  255,
  256,  257,  258,  259,  260,  265,  272,  269,  267,  293,
  280,  294,  295,  302,  305,  308,  311,  345,  318,  346,
  347,  325,  348,  349,  350,  351,  352,  353,  354,  355,
  356,  357,  358,   33,   53,    2,  359,  360,  361,  362,
  363,  364,  365,  366,  367,  368,  369,  370,  371,  372,
  203,  154,  155,  219,   17,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                          6,
   15,   16,   39,    0,  283,  104,   13,  151,   23,  304,
  258,  259,   19,  263,   45,  296,   31,  296,  258,  259,
  283,   96,  259,  258,   99,  306,   41,  306,   35,   36,
   45,  279,  283,  312,  313,  314,  315,  277,   53,  279,
  282,  304,  284,  281,  279,  287,  297,  146,  290,  281,
  264,  283,  151,  304,  283,  199,  200,  201,  202,  283,
  298,  283,  294,   70,   79,  307,  298,  305,  300,  309,
  294,  108,  304,  305,  281,  297,  283,  262,  300,  264,
  304,   96,  304,  263,   99,  100,  266,  294,  283,  104,
  263,  298,  107,  100,  281,  261,  283,  304,  305,  294,
  199,  200,  201,  202,  283,  300,  281,  294,  283,  304,
  141,  298,  258,  259,  121,  294,  261,  304,  305,  294,
  280,  277,  282,  298,  284,  304,  141,  258,  259,  304,
  305,  283,  266,  148,  233,  150,  151,  263,  175,  170,
  171,  172,  173,  174,  263,  297,  277,  262,  279,  264,
  293,  294,  295,  273,  274,  170,  171,  172,  173,  174,
  263,  176,  300,  178,  179,  180,  181,  182,  183,  184,
  185,  186,  187,  188,  282,  259,  284,  192,  300,  287,
  288,  265,  261,  262,  199,  200,  201,  202,  203,  259,
  282,  308,  284,  261,  302,  287,  267,  268,  269,  261,
  262,  266,  259,  277,  275,  276,  289,  291,  265,  293,
  302,  248,  296,  261,  262,  299,  300,  278,  301,  279,
  303,  262,  306,  292,  307,  309,  310,  311,  312,  313,
  314,  315,  247,  292,  291,  261,  293,  263,  292,  296,
  261,  262,  299,  300,  258,  271,  272,  273,  274,  306,
  261,  262,  309,  310,  311,  312,  313,  314,  315,  261,
  292,  268,  259,  261,  262,  292,  281,  282,  283,  271,
  272,  261,  292,  292,  292,  292,  278,  258,  293,  294,
  295,  258,  282,  292,  284,  258,  261,  287,  288,  289,
  290,  258,  299,  300,  301,  261,  271,  272,  273,  274,
  258,  301,  302,  303,  258,  271,  272,  307,  294,  266,
  261,  261,  278,   87,   88,   89,   90,   91,   92,   93,
   94,   95,  271,  272,  273,  274,  261,  261,  261,  278,
  261,  261,  278,  286,  261,  261,  261,  261,  261,  261,
  261,  261,  261,  261,  261,  261,  258,  295,  300,  292,
  261,  292,  292,  261,  261,  261,  300,  258,  300,  258,
  258,  300,  258,  258,  258,  258,  258,  258,  258,  258,
  258,  258,  258,   23,   31,  304,  261,  261,  261,  261,
  261,  261,  261,  261,  261,  261,  261,  261,  261,  261,
  159,  104,  104,  176,   10,
};
}
final static short YYFINAL=1;
final static short YYMAXTOKEN=315;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,"EMPTY","NUMBER","ID","WP","SEMICOLON","COMMA","COLON",
"OPEN_PARAN","CLOSE_PARAN","EQUAL","NOTEQUAL","LESSEQUAL","GREATEQUAL",
"ISEQUAL","PLUS","MINUS","MUL","DIV","LESS","GREAT","OPEN","CLOSE","Quote",
"Admission","Attraction","Capacity","Crowd","Cost","Duration","Else",
"Employees","EnergyIncrease","EnergyLevel","EnergyLost","If","In","Iterate",
"Land","Location","Number","Park","Restaurant","Return","Set","Size",
"SpendLevel","SpendingCapacity","Start","Store","String","ThrillLevel","Until",
"CalculateRevenue","Print","Simulate","Days","Months","Weeks","Years",
};
final static String yyrule[] = {
"$accept : program",
"program : definitions usercode",
"definitions : crowd_definitions park_definition crowd_definitions",
"crowd_definitions : crowd_definitions crowd_definition",
"crowd_definitions : empty",
"usercode : start functions",
"$$1 :",
"$$2 :",
"crowd_definition : Crowd $$1 crowd_name $$2 crowd_elements",
"crowd_elements : SEMICOLON",
"crowd_elements : crowd_attributes",
"crowd_attributes : crowd_attributes crowd_attribute",
"crowd_attributes : empty",
"crowd_attribute : c1",
"crowd_attribute : c2",
"crowd_attribute : c3",
"crowd_attribute : c4",
"c1 : Set SpendingCapacity NUMBER SEMICOLON",
"c2 : Set Size NUMBER SEMICOLON",
"c3 : Set EnergyLevel NUMBER SEMICOLON",
"c4 : Set ThrillLevel NUMBER SEMICOLON",
"$$3 :",
"$$4 :",
"$$5 :",
"park_definition : Park $$3 park_name $$4 park_elements $$5 land_definitions",
"park_elements : SEMICOLON",
"park_elements : park_attributes",
"park_attributes : park_attributes park_attribute",
"park_attributes : empty",
"park_attribute : p1",
"park_attribute : p2",
"park_attribute : p3",
"p1 : Set Admission NUMBER SEMICOLON",
"p2 : Set Capacity NUMBER SEMICOLON",
"p3 : Set Cost NUMBER SEMICOLON",
"land_definitions : land_definitions land_definition",
"land_definitions : empty",
"$$6 :",
"$$7 :",
"land_definition : Land $$6 land_name $$7 land_attributes land_elements",
"land_attributes : Set Location NUMBER SEMICOLON",
"land_elements : land_elements land_element",
"land_elements : empty",
"land_element : attraction_definition",
"land_element : restaurant_definition",
"land_element : store_definition",
"$$8 :",
"$$9 :",
"$$10 :",
"attraction_definition : Attraction $$8 attraction_name $$9 In land_name $$10 attraction_attributes",
"attraction_attributes : SEMICOLON",
"attraction_attributes : attraction_attributes attraction_attribute",
"attraction_attributes : empty",
"attraction_attribute : a1",
"attraction_attribute : a2",
"attraction_attribute : a3",
"attraction_attribute : a4",
"attraction_attribute : a5",
"a1 : Set Cost NUMBER SEMICOLON",
"a2 : Set Capacity NUMBER SEMICOLON",
"a3 : Set Employees NUMBER SEMICOLON",
"a4 : Set ThrillLevel NUMBER SEMICOLON",
"a5 : Set EnergyLost NUMBER SEMICOLON",
"$$11 :",
"$$12 :",
"$$13 :",
"restaurant_definition : Restaurant $$11 restaurant_name $$12 In land_name $$13 restaurant_attributes",
"restaurant_attributes : SEMICOLON",
"restaurant_attributes : restaurant_attributes restaurant_attribute",
"restaurant_attributes : empty",
"restaurant_attribute : r1",
"restaurant_attribute : r2",
"restaurant_attribute : r3",
"restaurant_attribute : r4",
"restaurant_attribute : r5",
"r1 : Set Cost NUMBER SEMICOLON",
"r2 : Set Capacity NUMBER SEMICOLON",
"r3 : Set Employees NUMBER SEMICOLON",
"r4 : Set SpendLevel NUMBER SEMICOLON",
"r5 : Set EnergyIncrease NUMBER SEMICOLON",
"$$14 :",
"$$15 :",
"$$16 :",
"store_definition : Store $$14 store_name $$15 In land_name $$16 store_attributes",
"store_attributes : SEMICOLON",
"store_attributes : store_attributes store_attribute",
"store_attributes : empty",
"store_attribute : s1",
"store_attribute : s2",
"store_attribute : s3",
"store_attribute : s4",
"s1 : Set Cost NUMBER SEMICOLON",
"s2 : Set Capacity NUMBER SEMICOLON",
"s3 : Set Employees NUMBER SEMICOLON",
"s4 : Set SpendLevel NUMBER SEMICOLON",
"start : Start COLON block",
"functions : functions function",
"functions : empty",
"function : return_type function_name COLON actual_parameters block",
"return_type : Number",
"return_type : String",
"return_type : empty",
"actual_parameters : actual_parameters COMMA data_type variable_name",
"actual_parameters : data_type variable_name",
"actual_parameters : empty",
"block : start_block statements end_block",
"start_block : OPEN_PARAN",
"end_block : CLOSE_PARAN",
"statements : statements statement",
"statements : empty",
"statement : add_attribute",
"statement : assignment",
"statement : condition",
"statement : declaration",
"statement : function_call",
"statement : initialization",
"statement : loop",
"statement : return",
"statement : thrill_functions",
"add_attribute : Set Capacity value In variable_name SEMICOLON",
"add_attribute : Set Cost value In variable_name SEMICOLON",
"add_attribute : Set Employees value In variable_name SEMICOLON",
"add_attribute : Set EnergyIncrease value In variable_name SEMICOLON",
"add_attribute : Set EnergyLevel value In variable_name SEMICOLON",
"add_attribute : Set EnergyLost value In variable_name SEMICOLON",
"add_attribute : Set Size value In variable_name SEMICOLON",
"add_attribute : Set SpendingCapacity value In variable_name SEMICOLON",
"add_attribute : Set SpendLevel value In variable_name SEMICOLON",
"add_attribute : Set ThrillLevel value In variable_name SEMICOLON",
"assignment : left_side EQUAL right_side",
"left_side : variable_name",
"right_side : arithmetic_expression SEMICOLON",
"right_side : function_call",
"right_side : calculate_revenue",
"arithmetic_expression : arithmetic_expression PLUS arithmetic_expression",
"arithmetic_expression : arithmetic_expression MINUS arithmetic_expression",
"arithmetic_expression : arithmetic_expression MUL arithmetic_expression",
"arithmetic_expression : arithmetic_expression DIV arithmetic_expression",
"arithmetic_expression : OPEN arithmetic_expression CLOSE",
"arithmetic_expression : variable_name",
"arithmetic_expression : constant",
"condition : If OPEN relational_expression CLOSE block",
"condition : If OPEN relational_expression CLOSE block Else block",
"relational_expression : variable_name LESSEQUAL constant_or_variable",
"relational_expression : variable_name GREATEQUAL constant_or_variable",
"relational_expression : variable_name NOTEQUAL constant_or_variable",
"relational_expression : variable_name LESS constant_or_variable",
"relational_expression : variable_name GREAT constant_or_variable",
"declaration : primitive_type declaration_list SEMICOLON",
"declaration_list : declaration_list COMMA variable_name",
"declaration_list : variable_name",
"function_call : function_name COLON formal_parameters SEMICOLON",
"formal_parameters : formal_parameters COMMA variable_name",
"formal_parameters : variable_name",
"formal_parameters : empty",
"initialization : primitive_type initialization_list SEMICOLON",
"initialization_list : initialization_list COMMA variable_name EQUAL constant",
"initialization_list : variable_name EQUAL constant",
"loop : Iterate block Until OPEN relational_expression CLOSE SEMICOLON",
"return : Return constant_or_variable SEMICOLON",
"thrill_functions : calculate_revenue",
"thrill_functions : output",
"thrill_functions : simulate",
"calculate_revenue : CalculateRevenue COLON crowd_name COMMA duration_name SEMICOLON",
"output : Print constant_variable_chain SEMICOLON",
"simulate : Simulate COLON crowd_name SEMICOLON",
"constant_variable_chain : constant_variable_chain COMMA constant_or_variable",
"constant_variable_chain : constant_or_variable",
"constant_or_variable : constant",
"constant_or_variable : variable_name",
"data_type : Crowd",
"data_type : primitive_type",
"primitive_type : Number",
"primitive_type : String",
"primitive_type : duration_type",
"duration_type : Days",
"duration_type : Weeks",
"duration_type : Months",
"duration_type : Years",
"constant : NUMBER",
"constant : Quote ID Quote",
"value : NUMBER",
"value : variable_name",
"attraction_name : variable_name",
"crowd_name : variable_name",
"duration_name : variable_name",
"function_name : variable_name",
"land_name : variable_name",
"park_name : variable_name",
"restaurant_name : variable_name",
"store_name : variable_name",
"variable_name : ID",
"empty :",
};

//#line 460 "thrill_grammar.y"
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

	public void createObject(String identifier) throws Exception{
		if(createObj){
			switch(keywordType){

			case Attraction:
				Attraction attraction = new Attraction();
				attraction.setAttractionName(identifier);
				if(thrillObjects.containsKey(identifier))
					ThrillException.RedefinitionException(identifier);
				thrillObjects.put(identifier, attraction);
				break;

			case Crowd:
				Crowd crowd = new Crowd();
				crowd.setCrowdName(identifier);
				// check for redefinition
				if(thrillObjects.containsKey(identifier))
					ThrillException.RedefinitionException(identifier);
				thrillObjects.put(identifier, crowd);

				break;

			case Land:
				if(noOfLands > 6)
					throw new Exception("Number of lands > 6");

				Land land = new Land();
				land.setLandName(identifier);
				// check for redefinition
				if(thrillObjects.containsKey(identifier))
					ThrillException.RedefinitionException(identifier);
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
					ThrillException.RedefinitionException(identifier);
				thrillObjects.put(identifier, park);

				// Need a local copy of Park object in the Land object
				parkObj = park;

				break;

			case Restaurant:
				Restaurant restaurant = new Restaurant();
				restaurant.setRestaurantName(identifier);
				if(thrillObjects.containsKey(identifier))
					ThrillException.RedefinitionException(identifier);
				thrillObjects.put(identifier, restaurant);

			case Store:
				Store store = new Store();
				store .setStoreName(identifier);
				if(thrillObjects.containsKey(identifier))
					ThrillException.RedefinitionException(identifier);
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
				ThrillException.ObjectNotFoundException(identifier);
			setAttractionAttribute(a, allAttributes);
		}		
		else if(obj instanceof Crowd){
			Crowd c = (Crowd)obj;
			if(c == null)
				ThrillException.ObjectNotFoundException(identifier);
			// set the attribute in the crowd object
			setCrowdAttribute(c, allAttributes);
		}
		else if(obj instanceof Land){
			// similar to Crowd			
		}
		else if(obj instanceof Park){
			Park p = (Park)obj;
			if(p == null)
				ThrillException.ObjectNotFoundException(identifier);
			// set the attribute in the crowd object
			setParkAttribute(p, allAttributes);
		}
		else if(obj instanceof Restaurant){
			Restaurant r = (Restaurant)obj;
			if(r == null)
				ThrillException.ObjectNotFoundException(identifier);
			setRestaurantAttribute(r, allAttributes);
		}
		else if(obj instanceof Store){
			Store s = (Store)obj;
			if(s == null)
				ThrillException.ObjectNotFoundException(identifier);
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
	
	public String generateAttribute(String variable, String function, String value) {
		String result = null;
		
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
		String result = null;
		
		double d = Double.parseDouble(value);
		
		if(function.equalsIgnoreCase("Cost")){			
			if(d < 0)
				throw new IllegalArgumentException("Cost cannot be less than zero");
			result = value;
		}
		else{
			int i = (int)d;
			if(function.equalsIgnoreCase("Capacity") || function.equalsIgnoreCase("Employees")){
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
			Crowd c = (Crowd)thrillObjects.get(crowdName);
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

		return result;
	}

	public String generateSimulate(String crowdName) {
		String result = null;
		try {
			Crowd c = (Crowd)thrillObjects.get(crowdName);
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

	public void generateThrillProgram(String buffer){
		try{
			FileWriter writer = new FileWriter(new File("ThrillProgram.java"));
			writer.write(buffer);
			writer.close();
		}catch(IOException io){			
		}		
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
		
		Hashtable<String, Object> objects = yyparser.getThrillObjects();
		//System.out.println("No .of objects = " + objects.size());
	
		System.out.println("\n\n\t\tHave a nice day\n\n");
	}
//#line 1157 "Parser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    yyval = dup_yyval(yyval); //duplicate yyval if ParserVal is used as semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
case 4:
//#line 166 "thrill_grammar.y"
{}
break;
case 5:
//#line 169 "thrill_grammar.y"
{ yyval.sval = val_peek(1).sval + val_peek(0).sval; generateThrillProgram(yyval.sval); /*System.out.print($$);*/ }
break;
case 6:
//#line 171 "thrill_grammar.y"
{ createObj = true; }
break;
case 7:
//#line 172 "thrill_grammar.y"
{ try { createObject(val_peek(0).sval); } catch(Exception ex) {} }
break;
case 8:
//#line 173 "thrill_grammar.y"
{ try{ setAttribute(val_peek(2).sval, val_peek(0).sval);}catch(Exception ex){} }
break;
case 9:
//#line 175 "thrill_grammar.y"
{}
break;
case 10:
//#line 176 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 11:
//#line 179 "thrill_grammar.y"
{ yyval.sval += val_peek(0).sval;}
break;
case 12:
//#line 180 "thrill_grammar.y"
{}
break;
case 13:
//#line 183 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 14:
//#line 184 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 15:
//#line 185 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 16:
//#line 186 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 17:
//#line 189 "thrill_grammar.y"
{ yyval.sval = ":SpendingCapacity:" + val_peek(1).dval;}
break;
case 18:
//#line 190 "thrill_grammar.y"
{ yyval.sval = ":Size:" + val_peek(1).dval; }
break;
case 19:
//#line 191 "thrill_grammar.y"
{ yyval.sval = ":EnergyLevel:" + val_peek(1).dval;}
break;
case 20:
//#line 192 "thrill_grammar.y"
{ yyval.sval = ":ThrillLevel:" + val_peek(1).dval;}
break;
case 21:
//#line 194 "thrill_grammar.y"
{ createObj = true; }
break;
case 22:
//#line 195 "thrill_grammar.y"
{ try { createObject(val_peek(0).sval); } catch(Exception ex) {} }
break;
case 23:
//#line 196 "thrill_grammar.y"
{ try{ setAttribute(val_peek(2).sval, val_peek(0).sval);}catch(Exception ex){} }
break;
case 25:
//#line 199 "thrill_grammar.y"
{}
break;
case 26:
//#line 200 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 27:
//#line 203 "thrill_grammar.y"
{ yyval.sval += val_peek(0).sval; }
break;
case 28:
//#line 204 "thrill_grammar.y"
{}
break;
case 29:
//#line 207 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 30:
//#line 208 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 31:
//#line 209 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 32:
//#line 212 "thrill_grammar.y"
{ yyval.sval = ":Admission:" + val_peek(1).dval;}
break;
case 33:
//#line 213 "thrill_grammar.y"
{ yyval.sval = ":Capacity:" + val_peek(1).dval;}
break;
case 34:
//#line 214 "thrill_grammar.y"
{ yyval.sval = ":Cost:" + val_peek(1).dval;}
break;
case 36:
//#line 217 "thrill_grammar.y"
{}
break;
case 37:
//#line 219 "thrill_grammar.y"
{ createObj = true; }
break;
case 38:
//#line 220 "thrill_grammar.y"
{ try { createObject(val_peek(0).sval); } catch(Exception ex) {} }
break;
case 39:
//#line 221 "thrill_grammar.y"
{ setLocation(val_peek(3).sval, val_peek(1).dval); }
break;
case 40:
//#line 222 "thrill_grammar.y"
{ yyval.dval = val_peek(1).dval; }
break;
case 42:
//#line 224 "thrill_grammar.y"
{}
break;
case 46:
//#line 231 "thrill_grammar.y"
{ createObj = true; }
break;
case 47:
//#line 232 "thrill_grammar.y"
{ try { createObject(val_peek(0).sval); } catch(Exception ex) {} }
break;
case 48:
//#line 234 "thrill_grammar.y"
{ createAttractionDefinition(val_peek(0).sval, val_peek(3).sval); }
break;
case 49:
//#line 235 "thrill_grammar.y"
{ try{ setAttribute(val_peek(5).sval, val_peek(0).sval);}catch(Exception ex){} }
break;
case 50:
//#line 236 "thrill_grammar.y"
{}
break;
case 51:
//#line 237 "thrill_grammar.y"
{ yyval.sval += val_peek(0).sval; }
break;
case 52:
//#line 238 "thrill_grammar.y"
{}
break;
case 53:
//#line 240 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 54:
//#line 241 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 55:
//#line 242 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 56:
//#line 243 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 57:
//#line 244 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 58:
//#line 246 "thrill_grammar.y"
{ yyval.sval = ":Cost:" + val_peek(1).dval;}
break;
case 59:
//#line 247 "thrill_grammar.y"
{ yyval.sval = ":Capacity:" + val_peek(1).dval;}
break;
case 60:
//#line 248 "thrill_grammar.y"
{ yyval.sval = ":Employees:" + val_peek(1).dval;}
break;
case 61:
//#line 249 "thrill_grammar.y"
{ yyval.sval = ":ThrillLevel:" + val_peek(1).dval;}
break;
case 62:
//#line 250 "thrill_grammar.y"
{ yyval.sval = ":EnergyLost:" + val_peek(1).dval;}
break;
case 63:
//#line 252 "thrill_grammar.y"
{ createObj = true; }
break;
case 64:
//#line 253 "thrill_grammar.y"
{ try { createObject(val_peek(0).sval); } catch(Exception ex) {} }
break;
case 65:
//#line 255 "thrill_grammar.y"
{ createRestaurantDefinition(val_peek(0).sval, val_peek(3).sval); }
break;
case 66:
//#line 256 "thrill_grammar.y"
{ try{ setAttribute(val_peek(5).sval, val_peek(0).sval);}catch(Exception ex){} }
break;
case 67:
//#line 258 "thrill_grammar.y"
{}
break;
case 68:
//#line 259 "thrill_grammar.y"
{ yyval.sval += val_peek(0).sval; }
break;
case 69:
//#line 260 "thrill_grammar.y"
{}
break;
case 70:
//#line 262 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 71:
//#line 263 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 72:
//#line 264 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 73:
//#line 265 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 74:
//#line 266 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 75:
//#line 268 "thrill_grammar.y"
{ yyval.sval = ":Cost:" + val_peek(1).dval;}
break;
case 76:
//#line 269 "thrill_grammar.y"
{ yyval.sval = ":Capacity:" + val_peek(1).dval;}
break;
case 77:
//#line 270 "thrill_grammar.y"
{ yyval.sval = ":Employees:" + val_peek(1).dval;}
break;
case 78:
//#line 271 "thrill_grammar.y"
{ yyval.sval = ":SpendLevel:" + val_peek(1).dval;}
break;
case 79:
//#line 272 "thrill_grammar.y"
{ yyval.sval = ":EnergyIncrease:" + val_peek(1).dval;}
break;
case 80:
//#line 274 "thrill_grammar.y"
{ createObj = true; }
break;
case 81:
//#line 275 "thrill_grammar.y"
{ try { createObject(val_peek(0).sval); } catch(Exception ex) {} }
break;
case 82:
//#line 277 "thrill_grammar.y"
{ createStoreDefinition(val_peek(0).sval, val_peek(3).sval); }
break;
case 83:
//#line 278 "thrill_grammar.y"
{ try{ setAttribute(val_peek(5).sval, val_peek(0).sval);}catch(Exception ex){} }
break;
case 84:
//#line 280 "thrill_grammar.y"
{}
break;
case 85:
//#line 281 "thrill_grammar.y"
{ yyval.sval += val_peek(0).sval; }
break;
case 86:
//#line 282 "thrill_grammar.y"
{}
break;
case 87:
//#line 284 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 88:
//#line 285 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 89:
//#line 286 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 90:
//#line 287 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 91:
//#line 290 "thrill_grammar.y"
{ yyval.sval = ":Cost:" + val_peek(1).dval;}
break;
case 92:
//#line 291 "thrill_grammar.y"
{ yyval.sval = ":Capacity:" + val_peek(1).dval;}
break;
case 93:
//#line 292 "thrill_grammar.y"
{ yyval.sval = ":Employees:" + val_peek(1).dval;}
break;
case 94:
//#line 293 "thrill_grammar.y"
{ yyval.sval = ":SpendLevel:" + val_peek(1).dval;}
break;
case 95:
//#line 295 "thrill_grammar.y"
{ yyval.sval = "public static void main()\n" + val_peek(0).sval; }
break;
case 96:
//#line 297 "thrill_grammar.y"
{ yyval.sval = val_peek(1).sval + val_peek(0).sval; }
break;
case 97:
//#line 298 "thrill_grammar.y"
{ yyval.sval = ""; }
break;
case 98:
//#line 302 "thrill_grammar.y"
{ yyval.sval = "\n" + val_peek(4).sval + " " + val_peek(3).sval + "(" + val_peek(1).sval + ")\n" + val_peek(0).sval; }
break;
case 99:
//#line 304 "thrill_grammar.y"
{ yyval.sval = "double"; }
break;
case 100:
//#line 305 "thrill_grammar.y"
{ yyval.sval = "String"; }
break;
case 101:
//#line 306 "thrill_grammar.y"
{yyval.sval = "void"; }
break;
case 102:
//#line 308 "thrill_grammar.y"
{ yyval.sval = val_peek(3).sval + ", " + val_peek(1).sval + " " + val_peek(0).sval; }
break;
case 103:
//#line 309 "thrill_grammar.y"
{ yyval.sval = val_peek(1).sval + " " + val_peek(0).sval; }
break;
case 104:
//#line 310 "thrill_grammar.y"
{ yyval.sval = ""; }
break;
case 105:
//#line 313 "thrill_grammar.y"
{ yyval.sval = "{" + val_peek(1).sval + "\n}"; }
break;
case 108:
//#line 318 "thrill_grammar.y"
{ yyval.sval = val_peek(1).sval + "\n" +  val_peek(0).sval; }
break;
case 109:
//#line 319 "thrill_grammar.y"
{ yyval.sval = ""; }
break;
case 110:
//#line 321 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 111:
//#line 322 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 112:
//#line 323 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 113:
//#line 324 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 114:
//#line 325 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 115:
//#line 326 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 116:
//#line 327 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 117:
//#line 328 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 118:
//#line 329 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 119:
//#line 332 "thrill_grammar.y"
{ yyval.sval = generateAttribute(val_peek(1).sval, "Capacity", val_peek(3).sval); }
break;
case 120:
//#line 333 "thrill_grammar.y"
{ yyval.sval = generateAttribute(val_peek(1).sval, "Cost", val_peek(3).sval); }
break;
case 121:
//#line 334 "thrill_grammar.y"
{ yyval.sval = generateAttribute(val_peek(1).sval, "Employees", val_peek(3).sval); }
break;
case 122:
//#line 335 "thrill_grammar.y"
{ yyval.sval = generateAttribute(val_peek(1).sval, "EnergyIncrease", val_peek(3).sval); }
break;
case 123:
//#line 336 "thrill_grammar.y"
{ yyval.sval = generateAttribute(val_peek(1).sval, "EnergyLevel", val_peek(3).sval); }
break;
case 124:
//#line 337 "thrill_grammar.y"
{ yyval.sval = generateAttribute(val_peek(1).sval, "EnergyLost", val_peek(3).sval); }
break;
case 125:
//#line 338 "thrill_grammar.y"
{ yyval.sval = generateAttribute(val_peek(1).sval, "Size", val_peek(3).sval); }
break;
case 126:
//#line 339 "thrill_grammar.y"
{ yyval.sval = generateAttribute(val_peek(1).sval, "SpendingCapacity", val_peek(3).sval); }
break;
case 127:
//#line 340 "thrill_grammar.y"
{ yyval.sval = generateAttribute(val_peek(1).sval, "SpendLevel", val_peek(3).sval); }
break;
case 128:
//#line 341 "thrill_grammar.y"
{ yyval.sval = generateAttribute(val_peek(1).sval, "ThrillLevel", val_peek(3).sval); }
break;
case 129:
//#line 344 "thrill_grammar.y"
{ yyval.sval = val_peek(2).sval + " = " + val_peek(0).sval;}
break;
case 130:
//#line 345 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 131:
//#line 346 "thrill_grammar.y"
{ yyval.sval = val_peek(1).sval + ";"; }
break;
case 132:
//#line 347 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 133:
//#line 348 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 134:
//#line 350 "thrill_grammar.y"
{ yyval.sval = val_peek(2).sval + "+" + val_peek(0).sval; }
break;
case 135:
//#line 351 "thrill_grammar.y"
{ yyval.sval = val_peek(2).sval + "-" + val_peek(0).sval; }
break;
case 136:
//#line 352 "thrill_grammar.y"
{ yyval.sval = val_peek(2).sval + "*" + val_peek(0).sval; }
break;
case 137:
//#line 353 "thrill_grammar.y"
{ yyval.sval = val_peek(2).sval + "/" + val_peek(0).sval; }
break;
case 138:
//#line 354 "thrill_grammar.y"
{ yyval.sval = "(" + val_peek(1).sval + ")"; }
break;
case 139:
//#line 355 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 140:
//#line 356 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 141:
//#line 360 "thrill_grammar.y"
{ yyval.sval = "if(" + val_peek(2).sval + ")" + val_peek(0).sval; }
break;
case 142:
//#line 362 "thrill_grammar.y"
{ yyval.sval = "if(" + val_peek(4).sval + ")" + val_peek(2).sval + "\nelse" + val_peek(0).sval; }
break;
case 143:
//#line 364 "thrill_grammar.y"
{ yyval.sval = val_peek(2).sval + " <= " + val_peek(0).sval;}
break;
case 144:
//#line 365 "thrill_grammar.y"
{ yyval.sval = val_peek(2).sval + " >= " + val_peek(0).sval;}
break;
case 145:
//#line 366 "thrill_grammar.y"
{ yyval.sval = val_peek(2).sval + " != " + val_peek(0).sval;}
break;
case 146:
//#line 367 "thrill_grammar.y"
{ yyval.sval = val_peek(2).sval + " < " + val_peek(0).sval;}
break;
case 147:
//#line 368 "thrill_grammar.y"
{ yyval.sval = val_peek(2).sval + " > " + val_peek(0).sval;}
break;
case 148:
//#line 371 "thrill_grammar.y"
{ yyval.sval = val_peek(2).sval + " " + val_peek(1).sval + ";"; }
break;
case 149:
//#line 372 "thrill_grammar.y"
{ yyval.sval = val_peek(2).sval + ", " + val_peek(0).sval; }
break;
case 150:
//#line 373 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 151:
//#line 377 "thrill_grammar.y"
{ yyval.sval = val_peek(3).sval + "(" + val_peek(1).sval + ");" ; }
break;
case 152:
//#line 380 "thrill_grammar.y"
{ yyval.sval = yyval.sval + "," + val_peek(0).sval; }
break;
case 153:
//#line 381 "thrill_grammar.y"
{yyval.sval = val_peek(0).sval;}
break;
case 154:
//#line 382 "thrill_grammar.y"
{ yyval.sval = ""; }
break;
case 155:
//#line 386 "thrill_grammar.y"
{ yyval.sval = val_peek(2).sval + " " + val_peek(1).sval + ";"; }
break;
case 156:
//#line 390 "thrill_grammar.y"
{ yyval.sval = val_peek(4).sval + ", " + val_peek(2).sval + "=" + val_peek(0).sval; }
break;
case 157:
//#line 392 "thrill_grammar.y"
{ yyval.sval = val_peek(2).sval + " = " + val_peek(0).sval; }
break;
case 158:
//#line 396 "thrill_grammar.y"
{yyval.sval = "do" + val_peek(5).sval + "while (" + val_peek(2).sval + ");" ; }
break;
case 159:
//#line 399 "thrill_grammar.y"
{ yyval.sval = "return " + val_peek(1).sval + ";"; }
break;
case 160:
//#line 401 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 161:
//#line 402 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 162:
//#line 403 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 163:
//#line 407 "thrill_grammar.y"
{yyval.sval = generateRevenue(val_peek(3).sval, val_peek(1).sval) ; }
break;
case 164:
//#line 410 "thrill_grammar.y"
{ yyval.sval = "System.out.println(" + val_peek(1).sval + ");" ; }
break;
case 165:
//#line 412 "thrill_grammar.y"
{yyval.sval = generateSimulate(val_peek(1).sval); }
break;
case 166:
//#line 415 "thrill_grammar.y"
{ yyval.sval = val_peek(2).sval + "+" + val_peek(0).sval; }
break;
case 167:
//#line 417 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 168:
//#line 420 "thrill_grammar.y"
{ yyval.sval = "\"" + val_peek(0).sval + "\""; }
break;
case 169:
//#line 421 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 170:
//#line 424 "thrill_grammar.y"
{ yyval.sval = "Crowd"; }
break;
case 171:
//#line 425 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 172:
//#line 428 "thrill_grammar.y"
{ yyval.sval = "double"; }
break;
case 173:
//#line 429 "thrill_grammar.y"
{ yyval.sval = "String"; }
break;
case 174:
//#line 430 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 175:
//#line 433 "thrill_grammar.y"
{ yyval.sval = "Days"; }
break;
case 176:
//#line 434 "thrill_grammar.y"
{ yyval.sval = "Weeks"; }
break;
case 177:
//#line 435 "thrill_grammar.y"
{ yyval.sval = "Months"; }
break;
case 178:
//#line 436 "thrill_grammar.y"
{ yyval.sval = "Years"; }
break;
case 179:
//#line 439 "thrill_grammar.y"
{ yyval.sval = new Double(val_peek(0).dval).toString(); }
break;
case 180:
//#line 440 "thrill_grammar.y"
{ yyval.sval = "\"" + val_peek(1).sval + "\""; }
break;
case 181:
//#line 443 "thrill_grammar.y"
{ yyval.sval = new Double(val_peek(0).dval).toString(); }
break;
case 182:
//#line 444 "thrill_grammar.y"
{yyval.sval = val_peek(0).sval; }
break;
case 183:
//#line 447 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 184:
//#line 448 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 185:
//#line 449 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 186:
//#line 450 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 187:
//#line 451 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 188:
//#line 452 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 189:
//#line 453 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 190:
//#line 454 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 191:
//#line 455 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 192:
//#line 457 "thrill_grammar.y"
{ }
break;
//#line 2030 "Parser.java"
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
/**
 * A default run method, used for operating this parser
 * object in the background.  It is intended for extending Thread
 * or implementing Runnable.  Turn off with -Jnorun .
 */
public void run()
{
  yyparse();
}
//## end of method run() ########################################



//## Constructors ###############################################
/**
 * Default constructor.  Turn off with -Jnoconstruct .

 */
public Parser()
{
  //nothing to do
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public Parser(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################
