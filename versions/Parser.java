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
    0,   90,   86,   86,   81,   85,   11,   11,    9,    9,
   10,   10,   10,   10,   12,   13,   14,   15,   93,   87,
   18,   18,   16,   16,   17,   17,   17,   19,   20,   21,
   88,   88,   94,   89,   22,   23,   23,   24,   24,   24,
   95,   25,   26,   26,   26,   27,   27,   27,   27,   27,
   28,   29,   30,   31,   32,   96,   33,   34,   34,   34,
   35,   35,   35,   35,   35,   36,   37,   38,   39,   40,
   97,   41,   42,   42,   42,   43,   43,   43,   43,   44,
   45,   46,   47,   98,   78,   82,   82,   99,   77,   76,
   76,   76,   75,   75,   75,   74,  100,  101,   73,   73,
   72,   72,   72,   72,   72,   72,   72,   72,   72,   72,
   71,   71,   71,   71,   71,   71,   71,   71,   71,   71,
   70,   68,   69,   69,   69,   69,   67,   67,   67,   67,
   67,   67,   67,   80,   80,   66,   66,   66,   66,   66,
   65,   64,   64,   63,   62,   62,   62,   61,   60,   60,
   84,   79,   83,   83,   58,   58,   58,   57,   55,   54,
   53,   53,   52,   52,   51,   51,   50,   50,   56,   56,
   56,   56,   49,   49,   48,   48,    1,    2,   59,    3,
    4,    5,    6,    7,    8,   91,   92,
};
final static short yylen[] = {                            2,
    2,    3,    2,    1,    2,    3,    1,    1,    2,    1,
    1,    1,    1,    1,    4,    4,    4,    4,    0,    5,
    1,    1,    2,    1,    1,    1,    1,    4,    4,    4,
    2,    1,    0,    5,    4,    2,    1,    1,    1,    1,
    0,    6,    1,    2,    1,    1,    1,    1,    1,    1,
    4,    4,    4,    4,    4,    0,    6,    1,    2,    1,
    1,    1,    1,    1,    1,    4,    4,    4,    4,    4,
    0,    6,    1,    2,    1,    1,    1,    1,    1,    4,
    4,    4,    4,    0,    4,    2,    1,    0,    6,    1,
    1,    1,    4,    2,    1,    3,    1,    1,    2,    1,
    1,    1,    1,    1,    1,    1,    1,    1,    1,    1,
    6,    6,    6,    6,    6,    6,    6,    6,    6,    6,
    3,    1,    2,    2,    1,    1,    3,    3,    3,    3,
    3,    1,    1,    5,    7,    3,    3,    3,    3,    3,
    3,    3,    1,    4,    3,    1,    1,    3,    5,    3,
    5,    7,    3,    2,    1,    1,    1,    6,    3,    4,
    3,    1,    1,    1,    1,    1,    1,    1,    1,    1,
    1,    1,    1,    1,    1,    1,    1,    1,    1,    1,
    1,    1,    1,    1,    1,    1,    0,
};
final static short yydefred[] = {                       187,
    0,    0,    0,    4,  186,    0,    0,    3,  187,   84,
  187,    1,  185,    0,  178,    0,  182,    0,    0,    0,
   87,    7,    0,    6,   10,   21,    0,   19,   24,    0,
   90,   91,    0,   86,   92,    0,    9,   11,   12,   13,
   14,    0,   23,   25,   26,   27,  187,   97,   85,  187,
   88,  180,    0,    0,    0,    0,    0,    0,    0,    0,
   32,    0,  100,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   31,   98,    0,    0,  167,    0,    0,  168,
    0,    0,    0,  169,  171,  170,  172,    0,    0,    0,
  157,  156,    0,  155,  110,  106,  105,  104,    0,  102,
  101,   99,  108,  103,  109,  107,   96,    0,   17,   16,
   15,   18,   28,   29,   30,   33,  181,    0,    0,  173,
  154,  174,  164,  163,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  162,    0,    0,    0,
    0,    0,    0,    0,    0,  165,  166,    0,    0,   95,
    0,    0,    0,    0,  153,  175,  176,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  159,    0,
    0,  146,    0,  147,    0,  148,    0,  141,    0,    0,
    0,    0,  133,  126,  125,    0,  121,   94,    0,   89,
    0,  187,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  161,  160,  144,    0,  150,    0,  142,    0,  132,    0,
  123,  124,    0,    0,    0,    0,    0,    0,    0,   37,
  138,  136,  137,  139,  140,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  179,    0,  145,
    0,  151,  131,    0,    0,  129,  130,   93,    0,    0,
    0,    0,   36,   38,   39,   40,    0,    0,  111,  112,
  113,  114,  115,  116,  117,  119,  118,  120,  158,  149,
   35,   41,  177,   56,  183,   71,  184,  135,  152,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   43,    0,
   45,   58,    0,   60,   73,    0,   75,    0,   44,   46,
   47,   48,   49,   50,    0,   59,   61,   62,   63,   64,
   65,    0,   74,   76,   77,   78,   79,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   52,   51,   53,   55,   54,
   67,   66,   68,   70,   69,   81,   80,   82,   83,
};
final static short yydgoto[] = {                          1,
  282,   14,   88,  116,   16,  284,  286,  157,   23,   37,
   24,   38,   39,   40,   41,   27,   43,   28,   44,   45,
   46,  192,  229,  263,  264,  300,  309,  310,  311,  312,
  313,  314,  265,  303,  316,  317,  318,  319,  320,  321,
  266,  306,  323,  324,  325,  326,  327,  158,  124,  147,
  148,  125,  138,   91,   92,   93,   94,   95,  249,  142,
   96,  173,   97,  143,   98,  153,  186,   99,  187,  100,
  101,  102,   62,   49,  149,   33,   34,   11,  103,  104,
   12,   20,  105,  106,    8,    2,    9,   60,   73,    3,
    4,    5,   47,  151,  290,  291,  292,   19,   64,   50,
  107,
};
final static short yysindex[] = {                         0,
    0, -233, -300,    0,    0, -248, -248,    0,    0,    0,
    0,    0,    0, -244,    0, -187,    0, -271, -182, -229,
    0,    0, -211,    0,    0,    0, -193,    0,    0, -151,
    0,    0, -248,    0,    0, -284,    0,    0,    0,    0,
    0, -210,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0, -153, -139, -134,  -98,  -84,  -81,  -72, -164,
    0,  -35,    0, -100,  -56,  -48,  -33,  -29,  -18,   -9,
   -3, -248,    0,    0,  -85, -151,    0, -249, -250,    0,
  -41, -130,   11,    0,    0,    0,    0,   22,    0, -248,
    0,    0, -248,    0,    0,    0,    0,    0,   18,    0,
    0,    0,    0,    0,    0,    0,    0, -186,    0,    0,
    0,    0,    0,    0,    0,    0,    0, -248,  -49,    0,
    0,    0,    0,    0,   25, -236, -236, -236, -236, -236,
 -236, -236, -236, -236, -236, -248,    0,  -95, -248, -248,
   33,  -27,  -11,   35, -251,    0,    0, -248,  -52,    0,
    3,  -31,   28,   26,    0,    0,    0,   20,   23,   24,
   32,   34,   36,   37,   38,   39,   40,   45,    0, -130,
   53,    0,    7,    0, -243,    0, -248,    0, -248,   68,
 -132,   67,    0,    0,    0,  -89,    0,    0, -186,    0,
   41,    0, -130, -130, -130, -130, -130, -151, -248, -248,
 -248, -248, -248, -248, -248, -248, -248, -248, -248, -248,
    0,    0,    0, -248,    0,   69,    0,   73,    0,  -25,
    0,    0, -132, -132, -132, -132, -248,   78, -237,    0,
    0,    0,    0,    0,    0,   51,   61,   79,   80,   81,
   82,   83,   84,   85,   86,   87,   88,    0,   89,    0,
 -243,    0,    0,  -10,  -10,    0,    0,    0,   90, -248,
 -248, -248,    0,    0,    0,    0, -151,   91,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   60,
   62,   63, -248, -248, -248,   93,   96,   97,    0,   58,
    0,    0,   59,    0,    0,   64,    0, -228,    0,    0,
    0,    0,    0,    0,  -74,    0,    0,    0,    0,    0,
    0, -206,    0,    0,    0,    0,    0,  103,  104,  105,
  106,  108,  109,  110,  111,  112,  113,  114,  115,  116,
  117,  118,  119,  120,  121,  122,  123,  124,  125,  126,
  127,  128,  129,  130,  131,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0, -149,    0, -140,    0,   71,    0,    2,
    0,    0, -147,    0,    0,    0, -152,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0, -143,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0, -162,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  -39,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    9,
   21,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  -32,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0, -127,    0,
    0,    0,    0,    0,    0,    8,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0, -102,  -45,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0, -199, -199, -199,    0, -263,
    0,    0, -196,    0,    0, -159,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,  -69,  344, -114,    0,    0,    0,   -6,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  163, -131,  316,
  204,  -79,    0,    0,    0,    0,  249,    0,    0,    0,
    0,    0,  250,    0,    0,  197, -177,    0,    0,    0,
    0,    0,    0,  -60,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  388,    0,    0,    0,    0,
   14,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,
};
final static int YYTABLESIZE=397;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         15,
   17,    5,  137,  220,   10,   53,  120,   13,  120,   13,
   13,  121,    6,  183,  120,  119,   22,   54,   42,   55,
   42,  156,   13,   56,   21,  181,   52,   25,  122,   29,
  122,   42,  126,   35,  127,   42,  122,  128,  129,  130,
  131,   42,   42,  215,  260,  254,  255,  256,  257,  183,
    6,  132,  133,  134,  328,   89,  329,  135,   81,  330,
   61,  261,  331,   63,    7,  117,  168,   31,  262,  171,
   57,  123,   58,   26,   59,  123,  338,   32,  339,  332,
   30,  340,  187,  141,  187,   57,  144,   57,  190,   36,
  211,  183,  183,  183,  183,  187,  341,  146,   57,  187,
  180,  187,   57,  122,   65,  187,  187,   42,   57,   57,
   77,  152,   48,  231,  232,  233,  234,  235,   66,  280,
   80,  150,   72,   67,   72,  120,   13,  120,   13,   15,
   72,   22,   15,  172,  187,   72,    8,  236,  182,   72,
   20,  188,   22,  187,  181,   72,   72,  122,  187,  122,
    8,  187,   22,  174,  187,  187,   34,    8,  127,   68,
  187,   20,  108,  123,  187,  169,  170,   34,  127,  127,
  216,  222,  217,   69,  219,  127,   70,   34,  296,  297,
  298,  223,  224,  225,  226,   71,  123,  123,  123,  123,
  123,  118,  152,  238,  239,  240,  241,  242,  243,  244,
  245,  246,  247,  248,  109,  230,  288,  250,  333,  189,
  334,   48,  110,  335,  336,  128,  219,  219,  219,  219,
  258,  136,  187,   13,  187,  128,  128,  111,  337,   74,
  180,  112,  128,  176,  177,  193,  194,  195,  132,  132,
  132,  132,  113,  196,  197,  223,  224,  225,  226,  178,
  179,  114,  253,  283,  285,  287,   75,  115,   76,  154,
  187,   77,  225,  226,   78,   79,  134,  213,  214,  187,
  187,   80,  134,  139,   81,   82,   83,   84,   85,   86,
   87,  143,  143,  145,  140,  155,  117,  117,  117,  159,
  160,  161,  162,  163,  164,  165,  166,  167,  175,  134,
  180,  134,  199,  191,  134,  198,  210,  134,  134,  301,
  304,  307,  200,  212,  134,  201,  202,  134,  134,  134,
  134,  134,  134,  134,  203,  218,  204,  221,  205,  206,
  207,  208,  209,  252,  251,  259,  228,  267,  268,  269,
  270,  271,  272,  273,  274,  275,  276,  277,  278,  279,
  281,  289,  293,  299,  294,  295,  302,  305,  308,  315,
  342,  343,  344,  345,  322,  346,  347,  348,  349,  350,
  351,  352,  353,  354,  355,    2,   51,   90,  356,  357,
  358,  359,  360,  361,  362,  363,  364,  365,  366,  367,
  368,  369,  227,  184,  185,  237,   18,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                          6,
    7,    0,   82,  181,  305,  290,  258,  259,  258,  259,
  259,  261,  284,  145,  258,   76,  261,  302,  282,  304,
  284,  258,  259,  308,   11,  277,   33,   14,  280,   16,
  280,  295,  283,   20,  285,  299,  280,  288,  289,  290,
  291,  305,  306,  175,  282,  223,  224,  225,  226,  181,
  284,  302,  303,  304,  283,   62,  285,  308,  310,  288,
   47,  299,  291,   50,  298,   72,  136,  297,  306,  139,
  281,   78,  283,  261,  285,   82,  283,  307,  285,  308,
  263,  288,  282,   90,  284,  282,   93,  284,  149,  301,
  170,  223,  224,  225,  226,  295,  303,  284,  295,  299,
  263,  301,  299,  266,  258,  305,  306,  301,  305,  306,
  297,  118,  264,  193,  194,  195,  196,  197,  258,  251,
  307,  108,  282,  258,  284,  258,  259,  258,  259,  136,
  295,  284,  139,  140,  284,  295,  284,  198,  145,  299,
  284,  148,  295,  284,  277,  305,  306,  280,  298,  280,
  298,  301,  305,  140,  295,  305,  284,  305,  261,  258,
  301,  305,  263,  170,  305,  261,  262,  295,  271,  272,
  177,  261,  179,  258,  181,  278,  258,  305,  293,  294,
  295,  271,  272,  273,  274,  258,  193,  194,  195,  196,
  197,  277,  199,  200,  201,  202,  203,  204,  205,  206,
  207,  208,  209,  210,  261,  192,  267,  214,  283,  262,
  285,  264,  261,  288,  289,  261,  223,  224,  225,  226,
  227,  263,  262,  259,  264,  271,  272,  261,  303,  265,
  263,  261,  278,  261,  262,  267,  268,  269,  271,  272,
  273,  274,  261,  275,  276,  271,  272,  273,  274,  261,
  262,  261,  278,  260,  261,  262,  292,  261,  294,  309,
  259,  297,  273,  274,  300,  301,  259,  261,  262,  261,
  262,  307,  265,  263,  310,  311,  312,  313,  314,  315,
  316,  261,  262,  266,  263,  261,  293,  294,  295,  127,
  128,  129,  130,  131,  132,  133,  134,  135,  266,  292,
  266,  294,  277,  301,  297,  278,  262,  300,  301,  296,
  297,  298,  293,  261,  307,  293,  293,  310,  311,  312,
  313,  314,  315,  316,  293,  258,  293,  261,  293,  293,
  293,  293,  293,  261,  266,  258,  296,  287,  278,  261,
  261,  261,  261,  261,  261,  261,  261,  261,  261,  261,
  261,  261,  293,  261,  293,  293,  261,  261,  301,  301,
  258,  258,  258,  258,  301,  258,  258,  258,  258,  258,
  258,  258,  258,  258,  258,  305,   33,   62,  261,  261,
  261,  261,  261,  261,  261,  261,  261,  261,  261,  261,
  261,  261,  189,  145,  145,  199,    9,
};
}
final static short YYFINAL=1;
final static short YYMAXTOKEN=316;
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
"STRING_CONST","Admission","Attraction","Capacity","Crowd","Cost","Duration",
"Else","Employees","EnergyIncrease","EnergyLevel","EnergyLost","If","In",
"Iterate","Land","Location","Number","Park","Restaurant","Return","Set","Size",
"SpendLevel","SpendingCapacity","Start","Store","String","ThrillLevel","Until",
"CalculateRevenue","Print","Simulate","Days","Months","Weeks","Years",
};
final static String yyrule[] = {
"$accept : program",
"program : definitions usercode",
"definitions : crowd_definitions park_definition crowd_definitions",
"crowd_definitions : crowd_definitions crowd_definition",
"crowd_definitions : error_production",
"usercode : start functions",
"crowd_definition : Crowd crowd_name crowd_elements",
"crowd_elements : SEMICOLON",
"crowd_elements : crowd_attributes",
"crowd_attributes : crowd_attributes crowd_attribute",
"crowd_attributes : error_production",
"crowd_attribute : c1",
"crowd_attribute : c2",
"crowd_attribute : c3",
"crowd_attribute : c4",
"c1 : Set SpendingCapacity NUMBER SEMICOLON",
"c2 : Set Size NUMBER SEMICOLON",
"c3 : Set EnergyLevel NUMBER SEMICOLON",
"c4 : Set ThrillLevel NUMBER SEMICOLON",
"$$1 :",
"park_definition : Park park_name park_elements $$1 land_definitions",
"park_elements : SEMICOLON",
"park_elements : park_attributes",
"park_attributes : park_attributes park_attribute",
"park_attributes : error_production",
"park_attribute : p1",
"park_attribute : p2",
"park_attribute : p3",
"p1 : Set Admission NUMBER SEMICOLON",
"p2 : Set Capacity NUMBER SEMICOLON",
"p3 : Set Cost NUMBER SEMICOLON",
"land_definitions : land_definitions land_definition",
"land_definitions : error_production",
"$$2 :",
"land_definition : Land land_name $$2 land_attributes land_elements",
"land_attributes : Set Location NUMBER SEMICOLON",
"land_elements : land_elements land_element",
"land_elements : error_production",
"land_element : attraction_definition",
"land_element : restaurant_definition",
"land_element : store_definition",
"$$3 :",
"attraction_definition : Attraction attraction_name $$3 In land_name attraction_attributes",
"attraction_attributes : SEMICOLON",
"attraction_attributes : attraction_attributes attraction_attribute",
"attraction_attributes : error_production",
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
"$$4 :",
"restaurant_definition : Restaurant restaurant_name $$4 In land_name restaurant_attributes",
"restaurant_attributes : SEMICOLON",
"restaurant_attributes : restaurant_attributes restaurant_attribute",
"restaurant_attributes : error_production",
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
"$$5 :",
"store_definition : Store store_name $$5 In land_name store_attributes",
"store_attributes : SEMICOLON",
"store_attributes : store_attributes store_attribute",
"store_attributes : error_production",
"store_attribute : s1",
"store_attribute : s2",
"store_attribute : s3",
"store_attribute : s4",
"s1 : Set Cost NUMBER SEMICOLON",
"s2 : Set Capacity NUMBER SEMICOLON",
"s3 : Set Employees NUMBER SEMICOLON",
"s4 : Set SpendLevel NUMBER SEMICOLON",
"$$6 :",
"start : Start $$6 COLON block",
"functions : functions function",
"functions : error_production",
"$$7 :",
"function : return_type function_name $$7 COLON actual_parameters block",
"return_type : Number",
"return_type : String",
"return_type : error_production",
"actual_parameters : actual_parameters COMMA data_type variable_name",
"actual_parameters : data_type variable_name",
"actual_parameters : error_production",
"block : start_block statements end_block",
"start_block : OPEN_PARAN",
"end_block : CLOSE_PARAN",
"statements : statements statement",
"statements : error_production",
"statement : add_attribute",
"statement : assignment",
"statement : condition",
"statement : declaration",
"statement : function_call",
"statement : initialization",
"statement : initialize_duration",
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
"right_side : variable_name SEMICOLON",
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
"formal_parameters : error_production",
"initialization : primitive_type initialization_list SEMICOLON",
"initialization_list : initialization_list COMMA variable_name EQUAL constant",
"initialization_list : variable_name EQUAL constant",
"initialize_duration : duration_type variable_name EQUAL NUMBER SEMICOLON",
"loop : Iterate block Until OPEN relational_expression CLOSE SEMICOLON",
"return : Return constant_or_variable SEMICOLON",
"return : Return SEMICOLON",
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
"duration_type : Days",
"duration_type : Weeks",
"duration_type : Months",
"duration_type : Years",
"constant : NUMBER",
"constant : STRING_CONST",
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
"error_production : empty",
"empty :",
};

//#line 480 "thrill_grammar.y"
	private Yylex lexer;
	public int yyline = 1;
	public int yycolumn = 0;
	private Hashtable<String, String> thrillObjects = new Hashtable<String, String>();
	int noOfParks = 0, noOfLands = 0;
	String parkName = null;
	String scopeName = null;
        private int actualParams = 0;
	final int MAX_LIMIT_PARK = 1;
	final int MAX_LIMIT_LANDS = 6;

	private int yylex () {
		int yyl_return = -1;
		try {
			yylval = new ParserVal(0);
			yyl_return = lexer.yylex();
		}
		catch (IOException e) {
			System.err.println("IO error: " + e.getMessage());
		}
		return yyl_return;
	}
	
	public void yyerror(String error) {
		try{			
			if(stateptr > 0) {
				String line = "line(" + yyline + ")";
				String column = "column(" + yycolumn + ")";
				System.out.print(error + " on " + line + " and " + column);
                System.out.println(": Illegal token '" + lexer.yytext() + "'");
			}
		}
		catch(Exception ex){			
		}
	}

	public Parser(Reader r) {
		lexer = new Yylex(r, this);
	}

	static boolean interactive;

	public String createAttractionDefinition(String landName, String attractionName) throws ThrillException{ 
		String result = "\nAttraction " + attractionName + " = new Attraction();\n";
		String key = "Global." + landName;

		if(!thrillObjects.containsKey(key)){
			ThrillException.ObjectNotFoundException("Error on line(" + yyline +"): ", landName);
		}

		String setName = attractionName + ".setAttractionName(\"" + attractionName + "\");\n";
		String setLand = attractionName + ".setLand(" + landName + ");\n";
		String addAttraction = landName + ".addAttraction(" + attractionName + ");\n";
		result += setName + setLand + addAttraction;

		return result;
	}

	public String createRestaurantDefinition(String landName, String restaurantName) throws ThrillException { 
		String result = "\nRestaurant " + restaurantName + " = new Restaurant();\n";
		String key = "Global." + landName;

		if(!thrillObjects.containsKey(key)){
			ThrillException.ObjectNotFoundException("Error on line(" + yyline +"): ", landName);
		}

		String setName = restaurantName + ".setRestaurantName(\"" + restaurantName + "\");\n";
		String setLand = restaurantName + ".setLand(" + landName + ");\n";
		String addRestaurant = landName + ".addRestaurant(" + restaurantName + ");\n";
		result += setName + setLand + addRestaurant;

		return result;
	}

	public String createStoreDefinition(String landName, String storeName) throws ThrillException{ 
		String result = "\nStore " + storeName + " = new Store();\n";
		String key = "Global." + landName;

		if(!thrillObjects.containsKey(key)){
			ThrillException.ObjectNotFoundException("Error on line(" + yyline +"): ", landName);
		}

		String setName = storeName + ".setStoreName(\"" + storeName + "\");\n";
		String setLand = storeName + ".setLand(" + landName + ");\n";
		String addStore = landName + ".addStore(" + storeName + ");\n";
		result += setName + setLand + addStore;

		return result;
	}

	public void addToHashtable(String identifier, String type) throws ThrillException{
		String key = identifier;
		if(scopeName != null){
			key = scopeName + "." + identifier;
		}
		
		if(thrillObjects.containsKey(key)){
			ThrillException.RedefinitionException("Error on line(" + yyline + "): ", identifier);
		}

		if(type == "Park"){
			if(noOfParks > 1){
				ThrillException.ExceededObjectLimitException("Error on line(" + yyline + "): ", "Park", MAX_LIMIT_PARK);
			}
			else{
				parkName = identifier;
			}
		
		}
		else if(type == "Land"){
			if(noOfLands > 6)
				ThrillException.ExceededObjectLimitException("Error on line(" + yyline + "): ", "Land", MAX_LIMIT_LANDS);
		}
		else if(type == "double"){
			type = "Number";
		}

		thrillObjects.put(key, type);
	}

	public boolean checkHashtable(String identifier) {
		boolean result = false;
		String key = scopeName + "." + identifier;

		if(thrillObjects.containsKey(key)){
			result = true;
		}
		return result;
	}

	public void addDeclVariables(String type, String allVariables) throws ThrillException{
		String[] variables = allVariables.split(",");

		for(int i = 0; i < variables.length; ++i){
			addToHashtable(variables[i].trim(), type);
		}
	}

	// we have a small problem here. An initialization might be of the form
	// Number a = 10; 
	// OR
	// Number a = 10, b = 10;
	// So we need to check for both equal to and comma before splitting
	// Not a good way, but there is no choice
	public void addInitVariables(String type, String allVariables) throws ThrillException{
		String[] variables = null;

		if(allVariables.contains(",")){
			String[] temp = allVariables.split(",");
			for(int i = 0; i < temp.length; ++i){
				variables = temp[i].split("=");
				addToHashtable(variables[0].trim(), type);
			}
		}
		else{
			variables = allVariables.split("=");
			addToHashtable(variables[0].trim(), type);
		}
	}

	public String generateSetAttribute(String identifier, String allAttributes) throws ThrillException{
		String result = "";
		String key = scopeName + "." + identifier;
		String obj = thrillObjects.get(key);
		if(obj == null){
			ThrillException.ObjectNotFoundException("Error on line(" + yyline +"): ", identifier);
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

	public String generateAttractionAttribute(String a, String allAttributes) throws ThrillException{
		String result = "";
		String regex = ":";		
		String[] attributes = allAttributes.split(regex);

		for(int i = 1; i < attributes.length; i+=2){
			String value = validateAttributeValue(attributes[i], attributes[i+1]);
			result += a + ".set" + attributes[i] + "(" + value + ");\n";
		}

		return result;
	}

	public String generateCrowdAttribute(String c, String allAttributes) throws ThrillException{		
		String regex = ":";
		String result = c + ".setCrowdName(\"" + c + "\");\n";
		String[] attributes = allAttributes.split(regex);

		for(int i = 1; i < attributes.length; i+=2){
			String value = validateAttributeValue(attributes[i], attributes[i+1]);
			result += c + ".set" + attributes[i] + "(" + value + ");\n";
		}

		return result;
	}

	public String generateLandAttribute(String l, String allAttributes)throws ThrillException{
		String regex = ":";
		String[] attributes = allAttributes.split(regex);
		String result = l + ".setLandName(\"" + l + "\");\n";
		String setPark = l + ".setPark(" + parkName + ");\n";
		String addLand = parkName + ".addLand(" + l + ");\n";
		result += setPark + addLand;

		for(int i = 1; i < attributes.length; i+=2){
			String value = validateAttributeValue(attributes[i], attributes[i+1]);
			result += l + ".set" + attributes[i] + "(" + value + ");\n";
		}		
		return result;
	}

	public String generateParkAttribute(String p, String allAttributes) throws ThrillException{
		String regex = ":";
		String[] attributes = allAttributes.split(regex);
		String result = p + ".setParkName(\"" + p + "\");\n"; 

		for(int i = 1; i < attributes.length; i+=2){
			String value = validateAttributeValue(attributes[i], attributes[i+1]);
			result += p + ".set" + attributes[i] + "(" + value + ");\n";
		}

		return result;
	}

	public String generateRestaurantAttribute(String r, String allAttributes) throws ThrillException{
		String regex = ":";
		String[] attributes = allAttributes.split(regex);
		String result = "";

		for(int i = 1; i < attributes.length; i+=2){
			String value = validateAttributeValue(attributes[i], attributes[i+1]);
			result += r + ".set" + attributes[i] + "(" + value + ");\n";
		}		

		return result;
	}

	public String generateStoreAttribute(String s, String allAttributes) throws ThrillException{
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

	public String generateAttribute(String variable, String function, String value) throws ThrillException{
		String result = "";;
		String key = "Global." + variable;
		value = validateAttributeValue(function, value);

		String obj = thrillObjects.get(key);

		if(obj == null){
			ThrillException.ObjectNotFoundException("Error on line(" + yyline +"): ", variable);
		}
		else{
			if(function.equalsIgnoreCase("Capacity")){
				if(obj.equalsIgnoreCase("Crowd")){
					ThrillException.UnexpectedTypeException("Error on line(" + yyline + "): ", variable, "Crowd");
				}
				result = variable.concat(".set" + function + "(" + value + ");");
			}
			else if(function.equalsIgnoreCase("Cost")){
				if(obj.equalsIgnoreCase("Crowd")){
					ThrillException.UnexpectedTypeException("Error on line(" + yyline + "): ", variable, "Crowd");
				}
				result = variable.concat(".set" + function + "(" + value + ");");
			}
			else if(function.equalsIgnoreCase("Employees")){
				if(obj.equalsIgnoreCase("Crowd")){
					ThrillException.UnexpectedTypeException("Error on line(" + yyline + "): ", variable, "Crowd");
				}
				result = variable.concat(".set" + function + "(" + value + ");");
			}
			else if(function.equalsIgnoreCase("EnergyIncrease")){
				if(!(obj.equalsIgnoreCase("Restaurant"))){
					ThrillException.UnexpectedTypeException("Error on line(" + yyline + "): ", "Restaurant", variable);
				}
				result = variable.concat(".set" + function + "(" + value + ");");

			}
			else if(function.equalsIgnoreCase("EnergyLevel")){
				if(!(obj.equalsIgnoreCase("Crowd"))){
					ThrillException.UnexpectedTypeException("Error on line(" + yyline + "): ", "Crowd", variable);
				}
				result = variable.concat(".set" + function + "(" + value + ");");				
			}
			else if(function.equalsIgnoreCase("EnergyLost")){
				if(!(obj.equalsIgnoreCase("Attraction"))){
					ThrillException.UnexpectedTypeException("Error on line(" + yyline + "): ", "Attraction", variable);
				}
				result = variable.concat(".set" + function + "(" + value + ");");
			}
			else if(function.equalsIgnoreCase("Size")){
				if(!(obj.equalsIgnoreCase("Crowd"))){
					ThrillException.UnexpectedTypeException("Error on line(" + yyline + "): ", "Crowd", variable);
				}
				result = variable.concat(".set" + function + "(" + value + ");");				
			}
			else if(function.equalsIgnoreCase("SpendingCapacity")){
				if(!(obj.equalsIgnoreCase("Crowd"))){
					ThrillException.UnexpectedTypeException("Error on line(" + yyline + "): ", "Crowd", variable);
				}
				result = variable.concat(".set" + function + "(" + value + ");");				
			}
			else if(function.equalsIgnoreCase("SpendLevel")){
				if(!(obj.equalsIgnoreCase("Restaurant") || obj.equalsIgnoreCase("Store"))){
					ThrillException.UnexpectedTypeException("Error on line(" + yyline + "): ", "Restaurant/Store", variable);
				}
				result = variable.concat(".set" + function + "(" + value + ");");				
			}
			else if(function.equalsIgnoreCase("ThrillLevel")){
				if(!(obj.equalsIgnoreCase("Attraction") || obj.equalsIgnoreCase("Crowd"))){
					ThrillException.UnexpectedTypeException("Error on line(" + yyline + "): ", "Attraction/Crowd", variable);
				}
				result = variable.concat(".set" + function + "(" + value + ");");				
			}
			else{
				// error condition
				ThrillException.UnexpectedTypeException("Error on line(" + yyline + "): ", "Attraction/Crowd/Restaurant/Store", variable);
			}
		}

		return result;
	}

	public String checkSemanticValue(String value) throws ThrillException{
		String key = scopeName + "." + value;
		String type = thrillObjects.get(key);
		if(type == null){
			ThrillException.ObjectNotFoundException("Error on line(" + yyline +"): ", value);
			return null;
		}
		else if(!type.equalsIgnoreCase("Number")){			
			ThrillException.UnexpectedTypeException("Error on line(" + yyline + "): ", "Number", type);
		}
		return value;
	}

	public boolean checkDivideByZero(String value1, String value2){
		return true;
	}

	public String generateFunction(String returnType, String functionName, String parameters, String block) throws ThrillException{
		String result = null;
		boolean checkReturn = false;
		String returnStmt = null;
		int beginIndex = 0;
		int endIndex = 0;
		String[] params = parameters.split(",");
		if(actualParams != params.length){
			ThrillException.InsufficientParamsException(functionName, actualParams);
		}

		/*
		if(checkParametersType()){
			//ThrillException.IllegalParamTypeException(lineInfo, functionName, actualParams, params.length);
		}
		*/

		if(block.contains("return")){
			beginIndex = block.indexOf("return");
			endIndex = block.indexOf(";", beginIndex);
			returnStmt = block.substring(beginIndex, endIndex + 1);	
			checkReturn = true;
		}

		if(checkReturn && !checkReturnType(returnType, returnStmt) 
		|| !returnType.equalsIgnoreCase("void") && returnStmt == null){
			ThrillException.MissingReturnStatementException("Error on line(" + yyline + ") and column(" + yycolumn + "): ", "Invalid/Missing return statement");
		}

		result = returnType + " " + functionName + "(" + parameters + ")\n" + block;

		return result;
	}

	boolean checkReturnType(String returnType, String returnStmt) throws ThrillException{
		boolean result = false;
		String temp = returnType.equalsIgnoreCase("double") ? "Number" : returnType; 
		String retVal = returnStmt.substring(7, returnStmt.length() - 1);		

		if(!returnType.equalsIgnoreCase("void")){
			String key = scopeName + "." + retVal;
			String type = thrillObjects.get(key);
			if(retVal.length() > 0){

				if(type == null){
					ThrillException.ObjectNotFoundException("Error on line(" + yyline + ") and column(" + yycolumn + "): ", retVal);
				}

				if(Character.isDigit(retVal.charAt(0)) && type.equalsIgnoreCase("Number")){
					result = true;
				}
				else if(type.equalsIgnoreCase(temp)){
					result = true;
				}
				else{
					ThrillException.UnexpectedTypeException("Error on line(" + yyline + ") and column(" + yycolumn + "): ", returnType, type);
				}				
			}
			else{
				ThrillException.UnexpectedTypeException("Error on line(" + yyline + ") and column(" + yycolumn + "): ", returnType, "void");
			}
		}
		else{			
			if(retVal.length() == 0)
				return true;
			else
				return false;
		}

		return result;
	}

	public String validateAttributeValue(String attribute, String value) throws ThrillException{
		String result = "";;

		double d = Double.parseDouble(value);

		if(attribute.equalsIgnoreCase("Admission") ||
				attribute.equalsIgnoreCase("Cost")){	
			if(d < 0)
				ThrillException.InvalidArgumentException("Error on line(" + yyline + ") and column(" + yycolumn + "): ", attribute + " cannot be less than zero");
			result = value;
		}
		else{
			int i = (int)d;
			if(attribute.equalsIgnoreCase("Location")){
				if(i < 1 || i > 6)
					ThrillException.InvalidArgumentException("Error on line(" + yyline + ") and column(" + yycolumn + "): ", attribute + " should be a value between 1 and 6");			
			}
			else if(attribute.equalsIgnoreCase("Capacity")  || 
					attribute.equalsIgnoreCase("Employees") ||
					attribute.equalsIgnoreCase("Size")){
				if(i < 0)
					ThrillException.InvalidArgumentException("Error on line(" + yyline + ") and column(" + yycolumn + "): ", attribute + " cannot be less than zero");

			}
			else {
				if(i < 0 || i > 20)
					ThrillException.InvalidArgumentException("Error on line(" + yyline + ") and column(" + yycolumn + "): ", attribute + " cannot be less than zero or greater than 20");
			}
			result = new Integer(i).toString();
		}
		return result;
	}

	// have to check the second argument as well
	public String generateRevenue(String crowdName, String duration) throws ThrillException {		
		String result = null;
		String c = thrillObjects.get("Global." + crowdName);
		if(c == null){
			ThrillException.ObjectNotFoundException("Error on line(" + yyline + ") and column(" + yycolumn + "): ", crowdName);
		}		
		
		String d = thrillObjects.get(scopeName + "." + duration);
		if(d == null){
			ThrillException.ObjectNotFoundException("Error on line(" + yyline + ") and column(" + yycolumn + "): ", duration);	
		}
		
		result = parkName + ".calculateRevenue(" + crowdName + ", " + duration + ");";
		return result;
	}

	public String generateSimulate(String crowdName) throws ThrillException{
		String result = null;
		String c = thrillObjects.get("Global." + crowdName);
		if(c == null){
			ThrillException.ObjectNotFoundException("Error on line(" + yyline + ") and column(" + yycolumn + "): ", crowdName);
		}		
		result = parkName + ".simulate(" + crowdName + ");";
		return result;
	}

	public void generateThrillProgram(String definitions, String usercode){
		String classStart = "public class ThrillProgram {\n";
		String classEnd = "\n}";
		String main = "public static void main(String[] args){\n";		
		usercode = usercode.substring(1);

		try{
			FileWriter writer = new FileWriter(new File("ThrillProgram.java"));
			String buffer = classStart + main + definitions +  usercode + classEnd;
			writer.write(buffer);
			writer.close();
		}catch(IOException io){			
		}		
	}

	public String initializeDuration(String durationType, String durationName, String value) throws ThrillException{
		String result = null;
		if(!checkHashtable(durationName)){
			ThrillException.ObjectNotFoundException("Error on line(" + yyline + ") and column(" + yycolumn + "): ", durationName);
		}

		double temp = Double.parseDouble(value);
		int days = (int)temp;
		result = durationType + " " + durationName + " = new " + durationType + "(" + days + ");"; 
		return result;
	}

	public boolean checkParseErrors(){
		boolean flag = false;
		if(yynerrs > 0) {
			flag = true;
		}
		return flag;
	}

	public static void main(String args[]) throws IOException {

		Parser yyparser;
		if(args.length < 1){
			System.out.println("Usage: java Parser <thrill_program.txt>");
			return;
		}

		// parse a file
		yyparser = new Parser(new FileReader(args[0]));

		System.out.println("\nCompiling ...\n");

		try{
			yyparser.yyparse();
			if(yyparser.checkParseErrors()){
				System.out.println("\nCompilation failed!!\n");
			}
			else{
				System.out.println("\nThrillProgram.java generated successfully.\n");;
			}

		}catch(ThrillException ex){
			System.out.println(ex.getMessage());			
		}
	}
//#line 1215 "Parser.java"
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
throws ThrillException
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
case 1:
//#line 176 "thrill_grammar.y"
{ generateThrillProgram(val_peek(1).sval, val_peek(0).sval); System.out.println("Total number of lines in the input: " + (yyline-1)); }
break;
case 2:
//#line 178 "thrill_grammar.y"
{ yyval.sval = val_peek(2).sval + val_peek(1).sval + val_peek(0).sval; }
break;
case 3:
//#line 179 "thrill_grammar.y"
{ yyval.sval += val_peek(0).sval; }
break;
case 4:
//#line 180 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 5:
//#line 183 "thrill_grammar.y"
{ yyval.sval = val_peek(1).sval + val_peek(0).sval; }
break;
case 6:
//#line 186 "thrill_grammar.y"
{ scopeName = "Global"; addToHashtable(val_peek(1).sval, "Crowd"); 
			  yyval.sval = "\nCrowd " + val_peek(1).sval + " = " + "new Crowd();\n" + generateSetAttribute(val_peek(1).sval, val_peek(0).sval); 
			}
break;
case 7:
//#line 190 "thrill_grammar.y"
{}
break;
case 8:
//#line 191 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 9:
//#line 194 "thrill_grammar.y"
{ yyval.sval += val_peek(0).sval;}
break;
case 10:
//#line 195 "thrill_grammar.y"
{ yyval.sval = "";}
break;
case 11:
//#line 198 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 12:
//#line 199 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 13:
//#line 200 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 14:
//#line 201 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 15:
//#line 204 "thrill_grammar.y"
{ yyval.sval = ":SpendingCapacity:" + val_peek(1).dval;}
break;
case 16:
//#line 205 "thrill_grammar.y"
{ yyval.sval = ":Size:" + val_peek(1).dval; }
break;
case 17:
//#line 206 "thrill_grammar.y"
{ yyval.sval = ":EnergyLevel:" + val_peek(1).dval;}
break;
case 18:
//#line 207 "thrill_grammar.y"
{ yyval.sval = ":ThrillLevel:" + val_peek(1).dval;}
break;
case 19:
//#line 209 "thrill_grammar.y"
{ scopeName = "Global"; addToHashtable(val_peek(1).sval, "Park"); }
break;
case 20:
//#line 210 "thrill_grammar.y"
{ yyval.sval = "\nPark " + val_peek(3).sval + " = " + "new Park();\n" + generateSetAttribute(val_peek(3).sval, val_peek(2).sval) + val_peek(0).sval; }
break;
case 21:
//#line 212 "thrill_grammar.y"
{}
break;
case 22:
//#line 213 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 23:
//#line 216 "thrill_grammar.y"
{ yyval.sval += val_peek(0).sval; }
break;
case 24:
//#line 217 "thrill_grammar.y"
{ yyval.sval = ""; }
break;
case 25:
//#line 220 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 26:
//#line 221 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 27:
//#line 222 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 28:
//#line 225 "thrill_grammar.y"
{ yyval.sval = ":Admission:" + val_peek(1).dval;}
break;
case 29:
//#line 226 "thrill_grammar.y"
{ yyval.sval = ":Capacity:" + val_peek(1).dval;}
break;
case 30:
//#line 227 "thrill_grammar.y"
{ yyval.sval = ":Cost:" + val_peek(1).dval;}
break;
case 31:
//#line 229 "thrill_grammar.y"
{ yyval.sval += val_peek(0).sval; }
break;
case 32:
//#line 230 "thrill_grammar.y"
{ yyval.sval = ""; }
break;
case 33:
//#line 232 "thrill_grammar.y"
{ addToHashtable(val_peek(0).sval, "Land"); }
break;
case 34:
//#line 234 "thrill_grammar.y"
{
					   yyval.sval = "\nLand " + val_peek(3).sval + " = " + "new Land();\n" + generateSetAttribute(val_peek(3).sval, val_peek(1).sval) + val_peek(0).sval; 					   
					 }
break;
case 35:
//#line 237 "thrill_grammar.y"
{ yyval.sval = ":Location:" + val_peek(1).dval; }
break;
case 36:
//#line 238 "thrill_grammar.y"
{ yyval.sval += val_peek(0).sval; }
break;
case 37:
//#line 239 "thrill_grammar.y"
{ yyval.sval = ""; }
break;
case 38:
//#line 241 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 39:
//#line 242 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 40:
//#line 243 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 41:
//#line 246 "thrill_grammar.y"
{ addToHashtable(val_peek(0).sval, "Attraction"); }
break;
case 42:
//#line 248 "thrill_grammar.y"
{ yyval.sval = createAttractionDefinition(val_peek(1).sval, val_peek(4).sval) + generateSetAttribute(val_peek(4).sval, val_peek(0).sval); }
break;
case 43:
//#line 249 "thrill_grammar.y"
{ yyval.sval = ""; }
break;
case 44:
//#line 250 "thrill_grammar.y"
{ yyval.sval += val_peek(0).sval; }
break;
case 45:
//#line 251 "thrill_grammar.y"
{ yyval.sval = ""; }
break;
case 46:
//#line 253 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 47:
//#line 254 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 48:
//#line 255 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 49:
//#line 256 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 50:
//#line 257 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 51:
//#line 259 "thrill_grammar.y"
{ yyval.sval = ":Cost:" + val_peek(1).dval;}
break;
case 52:
//#line 260 "thrill_grammar.y"
{ yyval.sval = ":Capacity:" + val_peek(1).dval;}
break;
case 53:
//#line 261 "thrill_grammar.y"
{ yyval.sval = ":Employees:" + val_peek(1).dval;}
break;
case 54:
//#line 262 "thrill_grammar.y"
{ yyval.sval = ":ThrillLevel:" + val_peek(1).dval;}
break;
case 55:
//#line 263 "thrill_grammar.y"
{ yyval.sval = ":EnergyLost:" + val_peek(1).dval;}
break;
case 56:
//#line 265 "thrill_grammar.y"
{ addToHashtable(val_peek(0).sval, "Restaurant"); }
break;
case 57:
//#line 267 "thrill_grammar.y"
{ yyval.sval = createRestaurantDefinition(val_peek(1).sval, val_peek(4).sval) + generateSetAttribute(val_peek(4).sval, val_peek(0).sval); }
break;
case 58:
//#line 269 "thrill_grammar.y"
{ yyval.sval = ""; }
break;
case 59:
//#line 270 "thrill_grammar.y"
{ yyval.sval += val_peek(0).sval; }
break;
case 60:
//#line 271 "thrill_grammar.y"
{ yyval.sval = ""; }
break;
case 61:
//#line 273 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 62:
//#line 274 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 63:
//#line 275 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 64:
//#line 276 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 65:
//#line 277 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 66:
//#line 279 "thrill_grammar.y"
{ yyval.sval = ":Cost:" + val_peek(1).dval;}
break;
case 67:
//#line 280 "thrill_grammar.y"
{ yyval.sval = ":Capacity:" + val_peek(1).dval;}
break;
case 68:
//#line 281 "thrill_grammar.y"
{ yyval.sval = ":Employees:" + val_peek(1).dval;}
break;
case 69:
//#line 282 "thrill_grammar.y"
{ yyval.sval = ":SpendLevel:" + val_peek(1).dval;}
break;
case 70:
//#line 283 "thrill_grammar.y"
{ yyval.sval = ":EnergyIncrease:" + val_peek(1).dval;}
break;
case 71:
//#line 285 "thrill_grammar.y"
{ addToHashtable(val_peek(0).sval, "Store"); }
break;
case 72:
//#line 287 "thrill_grammar.y"
{ yyval.sval = createStoreDefinition(val_peek(1).sval, val_peek(4).sval) + generateSetAttribute(val_peek(4).sval, val_peek(0).sval); }
break;
case 73:
//#line 289 "thrill_grammar.y"
{ yyval.sval = ""; }
break;
case 74:
//#line 290 "thrill_grammar.y"
{ yyval.sval += val_peek(0).sval; }
break;
case 75:
//#line 291 "thrill_grammar.y"
{ yyval.sval = ""; }
break;
case 76:
//#line 293 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 77:
//#line 294 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 78:
//#line 295 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 79:
//#line 296 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 80:
//#line 299 "thrill_grammar.y"
{ yyval.sval = ":Cost:" + val_peek(1).dval;}
break;
case 81:
//#line 300 "thrill_grammar.y"
{ yyval.sval = ":Capacity:" + val_peek(1).dval;}
break;
case 82:
//#line 301 "thrill_grammar.y"
{ yyval.sval = ":Employees:" + val_peek(1).dval;}
break;
case 83:
//#line 302 "thrill_grammar.y"
{ yyval.sval = ":SpendLevel:" + val_peek(1).dval;}
break;
case 84:
//#line 304 "thrill_grammar.y"
{ scopeName = "Start"; }
break;
case 85:
//#line 304 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 86:
//#line 306 "thrill_grammar.y"
{ yyval.sval = val_peek(1).sval + val_peek(0).sval; }
break;
case 87:
//#line 307 "thrill_grammar.y"
{ yyval.sval = ""; }
break;
case 88:
//#line 310 "thrill_grammar.y"
{ scopeName = val_peek(0).sval; addToHashtable(val_peek(0).sval, "Function"); }
break;
case 89:
//#line 311 "thrill_grammar.y"
{
		yyval.sval = "\n" + "public static " + generateFunction(val_peek(5).sval, val_peek(4).sval, val_peek(1).sval, val_peek(0).sval);
		actualParams = 0;
	  }
break;
case 90:
//#line 316 "thrill_grammar.y"
{ yyval.sval = "double"; }
break;
case 91:
//#line 317 "thrill_grammar.y"
{ yyval.sval = "String"; }
break;
case 92:
//#line 318 "thrill_grammar.y"
{yyval.sval = "void"; }
break;
case 93:
//#line 320 "thrill_grammar.y"
{ addToHashtable(val_peek(0).sval, val_peek(1).sval); yyval.sval = val_peek(3).sval + ", " + val_peek(1).sval + " " + val_peek(0).sval; ++actualParams; System.out.println("Number of parameters = " + actualParams);}
break;
case 94:
//#line 321 "thrill_grammar.y"
{ addToHashtable(val_peek(0).sval, val_peek(1).sval); yyval.sval = val_peek(1).sval + " " + val_peek(0).sval; ++actualParams; }
break;
case 95:
//#line 322 "thrill_grammar.y"
{ yyval.sval = ""; }
break;
case 96:
//#line 325 "thrill_grammar.y"
{ yyval.sval = "{" + val_peek(1).sval + "\n}"; }
break;
case 99:
//#line 330 "thrill_grammar.y"
{ yyval.sval = yyval.sval + "\n" +  val_peek(0).sval; }
break;
case 100:
//#line 331 "thrill_grammar.y"
{ yyval.sval = ""; }
break;
case 101:
//#line 333 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 102:
//#line 334 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 103:
//#line 335 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 104:
//#line 336 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 105:
//#line 337 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 106:
//#line 338 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 107:
//#line 339 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 108:
//#line 340 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 109:
//#line 341 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 110:
//#line 342 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 111:
//#line 345 "thrill_grammar.y"
{ yyval.sval = generateAttribute(val_peek(1).sval, "Capacity", val_peek(3).sval); }
break;
case 112:
//#line 346 "thrill_grammar.y"
{ yyval.sval = generateAttribute(val_peek(1).sval, "Cost", val_peek(3).sval); }
break;
case 113:
//#line 347 "thrill_grammar.y"
{ yyval.sval = generateAttribute(val_peek(1).sval, "Employees", val_peek(3).sval); }
break;
case 114:
//#line 348 "thrill_grammar.y"
{ yyval.sval = generateAttribute(val_peek(1).sval, "EnergyIncrease", val_peek(3).sval); }
break;
case 115:
//#line 349 "thrill_grammar.y"
{ yyval.sval = generateAttribute(val_peek(1).sval, "EnergyLevel", val_peek(3).sval); }
break;
case 116:
//#line 350 "thrill_grammar.y"
{ yyval.sval = generateAttribute(val_peek(1).sval, "EnergyLost", val_peek(3).sval); }
break;
case 117:
//#line 351 "thrill_grammar.y"
{ yyval.sval = generateAttribute(val_peek(1).sval, "Size", val_peek(3).sval); }
break;
case 118:
//#line 352 "thrill_grammar.y"
{ yyval.sval = generateAttribute(val_peek(1).sval, "SpendingCapacity", val_peek(3).sval); }
break;
case 119:
//#line 353 "thrill_grammar.y"
{ yyval.sval = generateAttribute(val_peek(1).sval, "SpendLevel", val_peek(3).sval); }
break;
case 120:
//#line 354 "thrill_grammar.y"
{ yyval.sval = generateAttribute(val_peek(1).sval, "ThrillLevel", val_peek(3).sval); }
break;
case 121:
//#line 357 "thrill_grammar.y"
{ yyval.sval = val_peek(2).sval + " = " + val_peek(0).sval;}
break;
case 122:
//#line 358 "thrill_grammar.y"
{ boolean exists = checkHashtable(val_peek(0).sval); if(exists) { yyval.sval = val_peek(0).sval; } else{ ThrillException.ObjectNotFoundException("Error on line(" + yyline + ") and column(" + yycolumn + "): ", val_peek(0).sval); } }
break;
case 123:
//#line 359 "thrill_grammar.y"
{ boolean exists = checkHashtable(val_peek(1).sval); if(exists) { yyval.sval = val_peek(1).sval + ";"; } else{ ThrillException.ObjectNotFoundException("Error on line(" + yyline + ") and column(" + yycolumn + "): ", val_peek(1).sval); } }
break;
case 124:
//#line 360 "thrill_grammar.y"
{ yyval.sval = val_peek(1).sval + ";"; }
break;
case 125:
//#line 361 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 126:
//#line 362 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 127:
//#line 364 "thrill_grammar.y"
{ yyval.sval = val_peek(2).sval + "+" + val_peek(0).sval; }
break;
case 128:
//#line 365 "thrill_grammar.y"
{ yyval.sval = val_peek(2).sval + "-" + val_peek(0).sval; }
break;
case 129:
//#line 366 "thrill_grammar.y"
{ yyval.sval = val_peek(2).sval + "*" + val_peek(0).sval; }
break;
case 130:
//#line 367 "thrill_grammar.y"
{ checkDivideByZero(val_peek(2).sval, val_peek(0).sval); yyval.sval = val_peek(2).sval + "/" + val_peek(0).sval; }
break;
case 131:
//#line 368 "thrill_grammar.y"
{ yyval.sval = "(" + val_peek(1).sval + ")"; }
break;
case 132:
//#line 369 "thrill_grammar.y"
{ 
												   boolean exists = checkHashtable(val_peek(0).sval); 
												   if(exists){ yyval.sval = checkSemanticValue(val_peek(0).sval); } 
												   else{ ThrillException.ObjectNotFoundException("Error on line(" + yyline + ") and column(" + yycolumn + "): ", val_peek(0).sval); }
												 }
break;
case 133:
//#line 374 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 134:
//#line 377 "thrill_grammar.y"
{ yyval.sval = "if(" + val_peek(2).sval + ")" + val_peek(0).sval; }
break;
case 135:
//#line 378 "thrill_grammar.y"
{ yyval.sval = "if(" + val_peek(4).sval + ")" + val_peek(2).sval + "\nelse" + val_peek(0).sval; }
break;
case 136:
//#line 380 "thrill_grammar.y"
{ yyval.sval = val_peek(2).sval + " <= " + val_peek(0).sval;}
break;
case 137:
//#line 381 "thrill_grammar.y"
{ yyval.sval = val_peek(2).sval + " >= " + val_peek(0).sval;}
break;
case 138:
//#line 382 "thrill_grammar.y"
{ yyval.sval = val_peek(2).sval + " != " + val_peek(0).sval;}
break;
case 139:
//#line 383 "thrill_grammar.y"
{ yyval.sval = val_peek(2).sval + " < " + val_peek(0).sval;}
break;
case 140:
//#line 384 "thrill_grammar.y"
{ yyval.sval = val_peek(2).sval + " > " + val_peek(0).sval;}
break;
case 141:
//#line 387 "thrill_grammar.y"
{ addDeclVariables(val_peek(2).sval, val_peek(1).sval); yyval.sval = val_peek(2).sval + " " + val_peek(1).sval + ";"; }
break;
case 142:
//#line 388 "thrill_grammar.y"
{ yyval.sval = val_peek(2).sval + ", " + val_peek(0).sval; }
break;
case 143:
//#line 389 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 144:
//#line 393 "thrill_grammar.y"
{ yyval.sval = val_peek(3).sval + "(" + val_peek(1).sval + ");" ; }
break;
case 145:
//#line 396 "thrill_grammar.y"
{ yyval.sval = yyval.sval + "," + val_peek(0).sval; }
break;
case 146:
//#line 397 "thrill_grammar.y"
{yyval.sval = val_peek(0).sval;}
break;
case 147:
//#line 398 "thrill_grammar.y"
{ yyval.sval = ""; }
break;
case 148:
//#line 402 "thrill_grammar.y"
{ addInitVariables(val_peek(2).sval, val_peek(1).sval); yyval.sval = val_peek(2).sval + " " + val_peek(1).sval + ";"; }
break;
case 149:
//#line 406 "thrill_grammar.y"
{ yyval.sval = val_peek(4).sval + ", " + val_peek(2).sval + " = " + val_peek(0).sval; }
break;
case 150:
//#line 408 "thrill_grammar.y"
{ yyval.sval = val_peek(2).sval + " = " + val_peek(0).sval; }
break;
case 151:
//#line 411 "thrill_grammar.y"
{ addToHashtable(val_peek(3).sval, val_peek(4).sval); yyval.sval = initializeDuration(val_peek(4).sval, val_peek(3).sval, new Double(val_peek(1).dval).toString() ); }
break;
case 152:
//#line 414 "thrill_grammar.y"
{yyval.sval = "do" + val_peek(5).sval + "while (" + val_peek(2).sval + ");" ; }
break;
case 153:
//#line 417 "thrill_grammar.y"
{ yyval.sval = "return " + val_peek(1).sval + ";"; }
break;
case 154:
//#line 418 "thrill_grammar.y"
{ yyval.sval = "return ;"; }
break;
case 155:
//#line 421 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 156:
//#line 422 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 157:
//#line 423 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 158:
//#line 427 "thrill_grammar.y"
{yyval.sval = generateRevenue(val_peek(3).sval, val_peek(1).sval) ; }
break;
case 159:
//#line 430 "thrill_grammar.y"
{ yyval.sval = "System.out.println(" + val_peek(1).sval + ");" ; }
break;
case 160:
//#line 432 "thrill_grammar.y"
{yyval.sval = generateSimulate(val_peek(1).sval); }
break;
case 161:
//#line 434 "thrill_grammar.y"
{ yyval.sval = val_peek(2).sval + "+" + val_peek(0).sval;}
break;
case 162:
//#line 435 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 163:
//#line 438 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 164:
//#line 439 "thrill_grammar.y"
{ boolean exists = checkHashtable(val_peek(0).sval); if(exists){ yyval.sval = val_peek(0).sval; } else{ ThrillException.ObjectNotFoundException("Error on line(" + yyline + ") and column(" + yycolumn + "): ", val_peek(0).sval); } }
break;
case 165:
//#line 442 "thrill_grammar.y"
{ yyval.sval = "Crowd"; }
break;
case 166:
//#line 443 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 167:
//#line 446 "thrill_grammar.y"
{ yyval.sval = "double"; }
break;
case 168:
//#line 447 "thrill_grammar.y"
{ yyval.sval = "String"; }
break;
case 169:
//#line 450 "thrill_grammar.y"
{ yyval.sval = "Days"; }
break;
case 170:
//#line 451 "thrill_grammar.y"
{ yyval.sval = "Weeks"; }
break;
case 171:
//#line 452 "thrill_grammar.y"
{ yyval.sval = "Months"; }
break;
case 172:
//#line 453 "thrill_grammar.y"
{ yyval.sval = "Years"; }
break;
case 173:
//#line 456 "thrill_grammar.y"
{ yyval.sval = new Double(val_peek(0).dval).toString(); }
break;
case 174:
//#line 457 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 175:
//#line 460 "thrill_grammar.y"
{ yyval.sval = new Double(val_peek(0).dval).toString(); }
break;
case 176:
//#line 461 "thrill_grammar.y"
{ boolean exists = checkHashtable(val_peek(0).sval); if(exists){ yyval.sval = val_peek(0).sval; } else{ ThrillException.ObjectNotFoundException("Error on line(" + yyline + ") and column(" + yycolumn + "): ", val_peek(0).sval); } }
break;
case 177:
//#line 464 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 178:
//#line 465 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 179:
//#line 466 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 180:
//#line 467 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 181:
//#line 468 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 182:
//#line 469 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 183:
//#line 470 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 184:
//#line 471 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 185:
//#line 472 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 186:
//#line 474 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 187:
//#line 477 "thrill_grammar.y"
{ yyval.sval = ""; }
break;
//#line 2115 "Parser.java"
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
//## The -Jnorun option was used ##
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
