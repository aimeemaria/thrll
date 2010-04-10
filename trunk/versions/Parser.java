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
    0,   92,   88,   88,   83,   87,   11,   11,    9,    9,
   10,   10,   10,   10,   12,   13,   14,   15,   94,   89,
   18,   18,   16,   16,   17,   17,   17,   19,   20,   21,
   90,   90,   95,   91,   22,   23,   23,   24,   24,   24,
   96,   25,   26,   26,   26,   27,   27,   27,   27,   27,
   28,   29,   30,   31,   32,   97,   33,   34,   34,   34,
   35,   35,   35,   35,   35,   36,   37,   38,   39,   40,
   98,   41,   42,   42,   42,   43,   43,   43,   43,   44,
   45,   46,   47,   80,   84,   84,   79,   78,   78,   78,
   77,   77,   77,   76,   75,   74,   73,   73,   72,   72,
   72,   72,   72,   72,   72,   72,   72,   72,   71,   71,
   71,   71,   71,   71,   71,   71,   71,   71,   70,   68,
   69,   69,   69,   67,   67,   67,   67,   67,   67,   67,
   82,   82,   66,   66,   66,   66,   66,   65,   64,   64,
   63,   62,   62,   62,   61,   60,   60,   86,   81,   85,
   58,   58,   58,   57,   55,   54,   53,   53,   52,   52,
   51,   51,   50,   50,   56,   56,   56,   56,   49,   49,
   48,   48,    1,    2,   59,    3,    4,    5,    6,    7,
    8,   93,
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
    4,    4,    4,    3,    2,    1,    5,    1,    1,    1,
    4,    2,    1,    3,    1,    1,    2,    1,    1,    1,
    1,    1,    1,    1,    1,    1,    1,    1,    6,    6,
    6,    6,    6,    6,    6,    6,    6,    6,    3,    1,
    2,    1,    1,    3,    3,    3,    3,    3,    1,    1,
    5,    7,    3,    3,    3,    3,    3,    3,    3,    1,
    4,    3,    1,    1,    3,    5,    3,    5,    7,    3,
    1,    1,    1,    6,    3,    4,    3,    1,    1,    1,
    1,    1,    1,    1,    1,    1,    1,    1,    1,    3,
    1,    1,    1,    1,    1,    1,    1,    1,    1,    1,
    1,    0,
};
final static short yydefred[] = {                       182,
    0,    0,    0,    4,    0,    0,    3,  182,    0,  182,
    1,  181,    0,  174,    0,  178,    0,    0,    0,   86,
    7,    0,    6,   10,   21,    0,   19,   24,   95,  182,
   84,   88,   89,    0,   85,   90,    0,    9,   11,   12,
   13,   14,    0,   23,   25,   26,   27,  182,    0,   98,
    0,  176,    0,    0,    0,    0,    0,    0,    0,    0,
   32,   96,    0,    0,  163,    0,    0,  164,    0,    0,
    0,  165,  167,  166,  168,    0,    0,    0,  153,  152,
    0,  151,  108,  104,  103,  102,    0,  100,   99,   97,
   94,  106,  101,  107,  105,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   31,    0,    0,  169,    0,  160,
  159,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  158,    0,    0,    0,    0,    0,    0,
    0,    0,  161,  162,    0,    0,   93,   17,   16,   15,
   18,   28,   29,   30,   33,  177,    0,    0,    0,    0,
  150,  171,  172,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  155,    0,    0,  143,    0,  144,
    0,  145,    0,  138,    0,    0,    0,    0,  130,  123,
  122,    0,  119,   92,    0,   87,    0,    0,    0,    0,
    0,    0,    0,    0,  170,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  157,  156,  141,    0,
  147,    0,  139,    0,  129,    0,  121,    0,    0,    0,
    0,    0,    0,  182,  135,  133,  134,  136,  137,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  175,    0,  142,    0,  148,  128,    0,    0,  126,
  127,   91,    0,    0,   37,    0,    0,  109,  110,  111,
  112,  113,  114,  115,  117,  116,  118,  154,  146,    0,
    0,    0,    0,   36,   38,   39,   40,  132,  149,   35,
   41,  173,   56,  179,   71,  180,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   43,    0,   45,   58,    0,
   60,   73,    0,   75,    0,   44,   46,   47,   48,   49,
   50,    0,   59,   61,   62,   63,   64,   65,    0,   74,
   76,   77,   78,   79,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   52,   51,   53,   55,   54,   67,   66,   68,
   70,   69,   81,   80,   82,   83,
};
final static short yydgoto[] = {                          1,
  281,   13,   76,  145,   15,  283,  285,  153,   22,   38,
   23,   39,   40,   41,   42,   26,   44,   27,   45,   46,
   47,  224,  254,  274,  275,  297,  306,  307,  308,  309,
  310,  311,  276,  300,  313,  314,  315,  316,  317,  318,
  277,  303,  320,  321,  322,  323,  324,  154,  111,  134,
  135,  112,  125,   79,   80,   81,   82,   83,  243,  129,
   84,  169,   85,  130,   86,  148,  182,   87,  183,   88,
   89,   90,   49,   91,   30,   31,  136,   34,   35,   10,
   92,   93,   11,   19,   94,   95,    7,    2,    8,   60,
  105,    3,    4,   48,  187,  287,  288,  289,
};
final static short yysindex[] = {                         0,
    0, -217, -276,    0, -238, -238,    0,    0, -206,    0,
    0,    0, -193,    0, -164,    0, -221, -162, -227,    0,
    0, -194,    0,    0,    0, -185,    0,    0,    0,    0,
    0,    0,    0, -238,    0,    0, -283,    0,    0,    0,
    0,    0, -118,    0,    0,    0,    0,    0, -155,    0,
 -137,    0, -121,  -70,  -68,  -56,  -51,  -49,  -40,  -66,
    0,    0,  -46, -162,    0, -244,   -8,    0,  -39, -244,
  -26,    0,    0,    0,    0,  -24,    0, -238,    0,    0,
 -238,    0,    0,    0,    0,    0,  -31,    0,    0,    0,
    0,    0,    0,    0,    0, -274,  -13,  -11,   12,   14,
   17,   27,   28, -238,    0, -238,   -7,    0,   -6,    0,
    0,   36,  -78,  -78,  -78,  -78,  -78,  -78,  -78,  -78,
  -78,  -78, -238,    0,  -50, -238, -238,   11,  -35,  -28,
   34, -254,    0,    0, -238, -168,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0, -156,   24,   26,   25,
    0,    0,    0,   13,   29,   30,   31,   32,   33,   37,
   38,   39,   40,   57,    0, -244,   59,    0,  -15,    0,
 -220,    0, -238,    0, -238,   68, -205,    0,    0,    0,
    0,   -9,    0,    0, -274,    0,   41, -244, -244, -244,
 -244, -244, -162, -238,    0, -238, -238, -238, -238, -238,
 -238, -238, -238, -238, -238, -238,    0,    0,    0, -238,
    0,   61,    0,   67,    0, -125,    0, -205, -205, -205,
 -205, -238,   42,    0,    0,    0,    0,    0,    0,   47,
   56,   74,   75,   77,   78,   79,   81,   82,   83,   84,
   85,    0,   86,    0, -220,    0,    0,   -4,   -4,    0,
    0,    0,   90, -235,    0, -162,   88,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   89,
 -238, -238, -238,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   60,   62,   63, -238,
 -238, -238,   92,   95,   96,    0,   51,    0,    0,   58,
    0,    0,   64,    0, -251,    0,    0,    0,    0,    0,
    0, -159,    0,    0,    0,    0,    0,    0,  -62,    0,
    0,    0,    0,    0,  101,  102,  103,  104,  105,  107,
  108,  109,  110,  111,  112,  113,  114,  115,  116,  117,
  118,  119,  120,  121,  122,  123,  124,  125,  126,  127,
  128,  129,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0, -197,    0, -257,    0,   70,    0,    2,    0,
    0, -219,    0,    0,    0, -199,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0, -216,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0, -149,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  -93,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   10,   22,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   35,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  -55,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  -96,  -29,    0,
    0,    0,    0, -196,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0, -223, -223, -223,    0, -264,    0,    0, -131,
    0,    0,  -75,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,   16,  341, -113,    0,    0,    0,   -5,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  196, -129,  327,
  206,  -57,    0,    0,    0,    0,  260,    0,    0,    0,
    0,    0,  261,    0,    0,  200, -169,    0,    0,    0,
    0,    0,    0,    0,    0,  -53,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  387,    0,    0,
    0,    0,   -3,    0,    0,    0,    0,    0,
};
final static int YYTABLESIZE=395;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         14,
   16,    5,  179,  108,   12,   53,   20,  216,  133,   24,
  107,   28,  124,  108,   12,   36,   42,   54,   42,   55,
   12,   65,  177,   56,  109,  182,   50,    9,   52,   42,
  325,   68,  326,   42,  109,  327,  182,  108,  328,   42,
   42,  211,  182,   77,   61,  271,  182,  179,  248,  249,
  250,  251,  108,   12,   69,  329,   18,  182,  109,  182,
  110,    5,  272,    8,  110,    5,   20,   21,   32,  273,
  182,  177,  128,  109,  182,  131,  182,    8,   33,    6,
  182,  182,  186,   22,    8,  182,   34,   20,  179,  179,
  179,  179,  137,  185,   22,   29,   25,   34,  146,  182,
  147,   29,  182,   12,   22,   37,  182,   34,  207,   62,
  188,  189,  190,  176,   43,  269,  120,   14,  191,  192,
   14,  168,  330,  170,  331,   96,  178,  332,  333,  184,
  225,  226,  227,  228,  229,   63,   97,   64,  164,  230,
   65,  167,  334,   66,   67,  218,  219,  220,  221,   57,
   68,   57,  247,   69,   70,   71,   72,   73,   74,   75,
  110,   57,   57,   58,  124,   59,   57,  212,  182,  213,
  182,  215,   57,   57,  124,  124,  293,  294,  295,  152,
   12,  124,  110,  110,  110,  110,  110,   98,  147,   99,
  232,  233,  234,  235,  236,  237,  238,  239,  240,  241,
  242,  100,  278,  131,  244,   72,  101,   72,  102,  131,
  165,  166,  215,  215,  215,  215,  252,  103,   72,  335,
  255,  336,   72,  123,  337,  172,  173,  104,   72,   72,
  106,  125,  174,  175,  132,  131,  126,  131,  127,  338,
  131,  125,  125,  131,  131,  209,  210,  138,  125,  139,
  131,  217,  150,  131,  131,  131,  131,  131,  131,  131,
  182,  218,  219,  220,  221,  282,  284,  286,  220,  221,
  182,  182,  140,  113,  141,  114,  171,  142,  115,  116,
  117,  118,  140,  140,  146,  146,  146,  143,  144,  298,
  301,  304,  119,  120,  121,  129,  151,  176,  122,  176,
  149,  193,  194,  195,  196,  129,  129,  129,  129,  155,
  156,  157,  158,  159,  160,  161,  162,  163,  206,  208,
  197,  198,  199,  200,  201,  214,  245,  246,  202,  203,
  204,  205,  256,  257,  258,  259,  253,  260,  261,  262,
  223,  263,  264,  265,  266,  267,  268,  270,  279,  280,
  305,  290,  296,  291,  292,  299,  302,  312,  339,  340,
  341,  342,  343,  319,  344,  345,  346,  347,  348,  349,
  350,  351,  352,    2,   51,   78,  353,  354,  355,  356,
  357,  358,  359,  360,  361,  362,  363,  364,  365,  366,
  222,  180,  181,  231,   17,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                          5,
    6,    0,  132,  258,  259,  289,   10,  177,  283,   13,
   64,   15,   70,  258,  259,   19,  281,  301,  283,  303,
  259,  296,  277,  307,  279,  283,   30,  304,   34,  294,
  282,  306,  284,  298,  279,  287,  294,  258,  290,  304,
  305,  171,  300,   49,   48,  281,  304,  177,  218,  219,
  220,  221,  258,  259,  309,  307,  263,  281,  279,  283,
   66,  283,  298,  283,   70,  283,  283,  261,  296,  305,
  294,  277,   78,  279,  298,   81,  300,  297,  306,  297,
  304,  305,  136,  283,  304,  283,  283,  304,  218,  219,
  220,  221,   96,  262,  294,  264,  261,  294,  104,  297,
  106,  264,  300,  259,  304,  300,  304,  304,  166,  265,
  267,  268,  269,  263,  300,  245,  266,  123,  275,  276,
  126,  127,  282,  127,  284,  263,  132,  287,  288,  135,
  188,  189,  190,  191,  192,  291,  258,  293,  123,  193,
  296,  126,  302,  299,  300,  271,  272,  273,  274,  281,
  306,  283,  278,  309,  310,  311,  312,  313,  314,  315,
  166,  280,  294,  282,  261,  284,  298,  173,  262,  175,
  264,  177,  304,  305,  271,  272,  290,  291,  292,  258,
  259,  278,  188,  189,  190,  191,  192,  258,  194,  258,
  196,  197,  198,  199,  200,  201,  202,  203,  204,  205,
  206,  258,  256,  259,  210,  281,  258,  283,  258,  265,
  261,  262,  218,  219,  220,  221,  222,  258,  294,  282,
  224,  284,  298,  263,  287,  261,  262,  294,  304,  305,
  277,  261,  261,  262,  266,  291,  263,  293,  263,  302,
  296,  271,  272,  299,  300,  261,  262,  261,  278,  261,
  306,  261,  259,  309,  310,  311,  312,  313,  314,  315,
  259,  271,  272,  273,  274,  271,  272,  273,  273,  274,
  261,  262,  261,  282,  261,  284,  266,  261,  287,  288,
  289,  290,  261,  262,  290,  291,  292,  261,  261,  293,
  294,  295,  301,  302,  303,  261,  261,  263,  307,  266,
  308,  278,  277,  279,  292,  271,  272,  273,  274,  114,
  115,  116,  117,  118,  119,  120,  121,  122,  262,  261,
  292,  292,  292,  292,  292,  258,  266,  261,  292,  292,
  292,  292,  286,  278,  261,  261,  295,  261,  261,  261,
  300,  261,  261,  261,  261,  261,  261,  258,  261,  261,
  300,  292,  261,  292,  292,  261,  261,  300,  258,  258,
  258,  258,  258,  300,  258,  258,  258,  258,  258,  258,
  258,  258,  258,  304,   34,   49,  261,  261,  261,  261,
  261,  261,  261,  261,  261,  261,  261,  261,  261,  261,
  185,  132,  132,  194,    8,
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
"crowd_definition : Crowd crowd_name crowd_elements",
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
"$$1 :",
"park_definition : Park park_name park_elements $$1 land_definitions",
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
"$$2 :",
"land_definition : Land land_name $$2 land_attributes land_elements",
"land_attributes : Set Location NUMBER SEMICOLON",
"land_elements : land_elements land_element",
"land_elements : empty",
"land_element : attraction_definition",
"land_element : restaurant_definition",
"land_element : store_definition",
"$$3 :",
"attraction_definition : Attraction attraction_name $$3 In land_name attraction_attributes",
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
"$$4 :",
"restaurant_definition : Restaurant restaurant_name $$4 In land_name restaurant_attributes",
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
"$$5 :",
"store_definition : Store store_name $$5 In land_name store_attributes",
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
"initialize_duration : duration_type variable_name EQUAL NUMBER SEMICOLON",
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

//#line 470 "thrill_grammar.y"
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
		String result = "";
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
//#line 1052 "Parser.java"
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
case 1:
//#line 175 "thrill_grammar.y"
{ generateThrillProgram(val_peek(1).sval, val_peek(0).sval);}
break;
case 2:
//#line 177 "thrill_grammar.y"
{yyval.sval = val_peek(2).sval + val_peek(1).sval + val_peek(0).sval; }
break;
case 3:
//#line 178 "thrill_grammar.y"
{ yyval.sval += val_peek(0).sval; }
break;
case 4:
//#line 179 "thrill_grammar.y"
{ yyval.sval = "";}
break;
case 5:
//#line 182 "thrill_grammar.y"
{ yyval.sval = val_peek(1).sval + val_peek(0).sval; }
break;
case 6:
//#line 185 "thrill_grammar.y"
{ addToHashtable(val_peek(1).sval, "Crowd"); 
			  yyval.sval = "\nCrowd " + val_peek(1).sval + " = " + "new Crowd();\n" + generateSetAttribute(val_peek(1).sval, val_peek(0).sval); 
			}
break;
case 7:
//#line 189 "thrill_grammar.y"
{}
break;
case 8:
//#line 190 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 9:
//#line 193 "thrill_grammar.y"
{ yyval.sval += val_peek(0).sval;}
break;
case 10:
//#line 194 "thrill_grammar.y"
{ yyval.sval = "";}
break;
case 11:
//#line 197 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 12:
//#line 198 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 13:
//#line 199 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 14:
//#line 200 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 15:
//#line 203 "thrill_grammar.y"
{ yyval.sval = ":SpendingCapacity:" + val_peek(1).dval;}
break;
case 16:
//#line 204 "thrill_grammar.y"
{ yyval.sval = ":Size:" + val_peek(1).dval; }
break;
case 17:
//#line 205 "thrill_grammar.y"
{ yyval.sval = ":EnergyLevel:" + val_peek(1).dval;}
break;
case 18:
//#line 206 "thrill_grammar.y"
{ yyval.sval = ":ThrillLevel:" + val_peek(1).dval;}
break;
case 19:
//#line 208 "thrill_grammar.y"
{ addToHashtable(val_peek(1).sval, "Park"); }
break;
case 20:
//#line 209 "thrill_grammar.y"
{ yyval.sval = "\nPark " + val_peek(3).sval + " = " + "new Park();\n" + generateSetAttribute(val_peek(3).sval, val_peek(2).sval) + val_peek(0).sval; }
break;
case 21:
//#line 211 "thrill_grammar.y"
{}
break;
case 22:
//#line 212 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 23:
//#line 215 "thrill_grammar.y"
{ yyval.sval += val_peek(0).sval; }
break;
case 24:
//#line 216 "thrill_grammar.y"
{ yyval.sval = ""; }
break;
case 25:
//#line 219 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 26:
//#line 220 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 27:
//#line 221 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 28:
//#line 224 "thrill_grammar.y"
{ yyval.sval = ":Admission:" + val_peek(1).dval;}
break;
case 29:
//#line 225 "thrill_grammar.y"
{ yyval.sval = ":Capacity:" + val_peek(1).dval;}
break;
case 30:
//#line 226 "thrill_grammar.y"
{ yyval.sval = ":Cost:" + val_peek(1).dval;}
break;
case 31:
//#line 228 "thrill_grammar.y"
{ yyval.sval += val_peek(0).sval; }
break;
case 32:
//#line 229 "thrill_grammar.y"
{ yyval.sval = ""; }
break;
case 33:
//#line 231 "thrill_grammar.y"
{ addToHashtable(val_peek(0).sval, "Land"); }
break;
case 34:
//#line 233 "thrill_grammar.y"
{
					   yyval.sval = "\nLand " + val_peek(3).sval + " = " + "new Land();\n" + generateSetAttribute(val_peek(3).sval, val_peek(1).sval) + val_peek(0).sval; 					   
					 }
break;
case 35:
//#line 236 "thrill_grammar.y"
{ yyval.sval = ":Location:" + val_peek(1).dval; }
break;
case 36:
//#line 237 "thrill_grammar.y"
{ yyval.sval += val_peek(0).sval; }
break;
case 37:
//#line 238 "thrill_grammar.y"
{ yyval.sval = ""; }
break;
case 38:
//#line 240 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 39:
//#line 241 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 40:
//#line 242 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 41:
//#line 245 "thrill_grammar.y"
{ addToHashtable(val_peek(0).sval, "Attraction"); }
break;
case 42:
//#line 247 "thrill_grammar.y"
{ yyval.sval = createAttractionDefinition(val_peek(1).sval, val_peek(4).sval) + generateSetAttribute(val_peek(4).sval, val_peek(0).sval); }
break;
case 43:
//#line 248 "thrill_grammar.y"
{ yyval.sval = ""; }
break;
case 44:
//#line 249 "thrill_grammar.y"
{ yyval.sval += val_peek(0).sval; }
break;
case 45:
//#line 250 "thrill_grammar.y"
{ yyval.sval = ""; }
break;
case 46:
//#line 252 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 47:
//#line 253 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 48:
//#line 254 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 49:
//#line 255 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 50:
//#line 256 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 51:
//#line 258 "thrill_grammar.y"
{ yyval.sval = ":Cost:" + val_peek(1).dval;}
break;
case 52:
//#line 259 "thrill_grammar.y"
{ yyval.sval = ":Capacity:" + val_peek(1).dval;}
break;
case 53:
//#line 260 "thrill_grammar.y"
{ yyval.sval = ":Employees:" + val_peek(1).dval;}
break;
case 54:
//#line 261 "thrill_grammar.y"
{ yyval.sval = ":ThrillLevel:" + val_peek(1).dval;}
break;
case 55:
//#line 262 "thrill_grammar.y"
{ yyval.sval = ":EnergyLost:" + val_peek(1).dval;}
break;
case 56:
//#line 264 "thrill_grammar.y"
{ addToHashtable(val_peek(0).sval, "Restaurant"); }
break;
case 57:
//#line 266 "thrill_grammar.y"
{ yyval.sval = createRestaurantDefinition(val_peek(1).sval, val_peek(4).sval) + generateSetAttribute(val_peek(4).sval, val_peek(0).sval); }
break;
case 58:
//#line 268 "thrill_grammar.y"
{ yyval.sval = ""; }
break;
case 59:
//#line 269 "thrill_grammar.y"
{ yyval.sval += val_peek(0).sval; }
break;
case 60:
//#line 270 "thrill_grammar.y"
{ yyval.sval = ""; }
break;
case 61:
//#line 272 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 62:
//#line 273 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 63:
//#line 274 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 64:
//#line 275 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 65:
//#line 276 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 66:
//#line 278 "thrill_grammar.y"
{ yyval.sval = ":Cost:" + val_peek(1).dval;}
break;
case 67:
//#line 279 "thrill_grammar.y"
{ yyval.sval = ":Capacity:" + val_peek(1).dval;}
break;
case 68:
//#line 280 "thrill_grammar.y"
{ yyval.sval = ":Employees:" + val_peek(1).dval;}
break;
case 69:
//#line 281 "thrill_grammar.y"
{ yyval.sval = ":SpendLevel:" + val_peek(1).dval;}
break;
case 70:
//#line 282 "thrill_grammar.y"
{ yyval.sval = ":EnergyIncrease:" + val_peek(1).dval;}
break;
case 71:
//#line 284 "thrill_grammar.y"
{ addToHashtable(val_peek(0).sval, "Store"); }
break;
case 72:
//#line 286 "thrill_grammar.y"
{ yyval.sval = createStoreDefinition(val_peek(1).sval, val_peek(4).sval) + generateSetAttribute(val_peek(4).sval, val_peek(0).sval); }
break;
case 73:
//#line 288 "thrill_grammar.y"
{ yyval.sval = ""; }
break;
case 74:
//#line 289 "thrill_grammar.y"
{ yyval.sval += val_peek(0).sval; }
break;
case 75:
//#line 290 "thrill_grammar.y"
{ yyval.sval = ""; }
break;
case 76:
//#line 292 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 77:
//#line 293 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 78:
//#line 294 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 79:
//#line 295 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 80:
//#line 298 "thrill_grammar.y"
{ yyval.sval = ":Cost:" + val_peek(1).dval;}
break;
case 81:
//#line 299 "thrill_grammar.y"
{ yyval.sval = ":Capacity:" + val_peek(1).dval;}
break;
case 82:
//#line 300 "thrill_grammar.y"
{ yyval.sval = ":Employees:" + val_peek(1).dval;}
break;
case 83:
//#line 301 "thrill_grammar.y"
{ yyval.sval = ":SpendLevel:" + val_peek(1).dval;}
break;
case 84:
//#line 303 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 85:
//#line 305 "thrill_grammar.y"
{ yyval.sval = val_peek(1).sval + val_peek(0).sval; }
break;
case 86:
//#line 306 "thrill_grammar.y"
{ yyval.sval = ""; }
break;
case 87:
//#line 310 "thrill_grammar.y"
{ yyval.sval = "\n" + val_peek(4).sval + " " + val_peek(3).sval + "(" + val_peek(1).sval + ")\n" + val_peek(0).sval; }
break;
case 88:
//#line 312 "thrill_grammar.y"
{ yyval.sval = "double"; }
break;
case 89:
//#line 313 "thrill_grammar.y"
{ yyval.sval = "String"; }
break;
case 90:
//#line 314 "thrill_grammar.y"
{yyval.sval = "void"; }
break;
case 91:
//#line 316 "thrill_grammar.y"
{ yyval.sval = val_peek(3).sval + ", " + val_peek(1).sval + " " + val_peek(0).sval; }
break;
case 92:
//#line 317 "thrill_grammar.y"
{ yyval.sval = val_peek(1).sval + " " + val_peek(0).sval; }
break;
case 93:
//#line 318 "thrill_grammar.y"
{ yyval.sval = ""; }
break;
case 94:
//#line 321 "thrill_grammar.y"
{ yyval.sval = "{" + val_peek(1).sval + "\n}"; }
break;
case 97:
//#line 326 "thrill_grammar.y"
{ yyval.sval = yyval.sval + "\n" +  val_peek(0).sval; }
break;
case 98:
//#line 327 "thrill_grammar.y"
{ yyval.sval = ""; }
break;
case 99:
//#line 329 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 100:
//#line 330 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 101:
//#line 331 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 102:
//#line 332 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 103:
//#line 333 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 104:
//#line 334 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 105:
//#line 335 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 106:
//#line 336 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 107:
//#line 337 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 108:
//#line 338 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 109:
//#line 341 "thrill_grammar.y"
{ yyval.sval = generateAttribute(val_peek(1).sval, "Capacity", val_peek(3).sval); }
break;
case 110:
//#line 342 "thrill_grammar.y"
{ yyval.sval = generateAttribute(val_peek(1).sval, "Cost", val_peek(3).sval); }
break;
case 111:
//#line 343 "thrill_grammar.y"
{ yyval.sval = generateAttribute(val_peek(1).sval, "Employees", val_peek(3).sval); }
break;
case 112:
//#line 344 "thrill_grammar.y"
{ yyval.sval = generateAttribute(val_peek(1).sval, "EnergyIncrease", val_peek(3).sval); }
break;
case 113:
//#line 345 "thrill_grammar.y"
{ yyval.sval = generateAttribute(val_peek(1).sval, "EnergyLevel", val_peek(3).sval); }
break;
case 114:
//#line 346 "thrill_grammar.y"
{ yyval.sval = generateAttribute(val_peek(1).sval, "EnergyLost", val_peek(3).sval); }
break;
case 115:
//#line 347 "thrill_grammar.y"
{ yyval.sval = generateAttribute(val_peek(1).sval, "Size", val_peek(3).sval); }
break;
case 116:
//#line 348 "thrill_grammar.y"
{ yyval.sval = generateAttribute(val_peek(1).sval, "SpendingCapacity", val_peek(3).sval); }
break;
case 117:
//#line 349 "thrill_grammar.y"
{ yyval.sval = generateAttribute(val_peek(1).sval, "SpendLevel", val_peek(3).sval); }
break;
case 118:
//#line 350 "thrill_grammar.y"
{ yyval.sval = generateAttribute(val_peek(1).sval, "ThrillLevel", val_peek(3).sval); }
break;
case 119:
//#line 353 "thrill_grammar.y"
{ yyval.sval = val_peek(2).sval + " = " + val_peek(0).sval;}
break;
case 120:
//#line 354 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 121:
//#line 355 "thrill_grammar.y"
{ yyval.sval = val_peek(1).sval + ";"; }
break;
case 122:
//#line 356 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 123:
//#line 357 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; System.out.print(yyval.sval);}
break;
case 124:
//#line 359 "thrill_grammar.y"
{ yyval.sval = val_peek(2).sval + "+" + val_peek(0).sval; }
break;
case 125:
//#line 360 "thrill_grammar.y"
{ yyval.sval = val_peek(2).sval + "-" + val_peek(0).sval; }
break;
case 126:
//#line 361 "thrill_grammar.y"
{ yyval.sval = val_peek(2).sval + "*" + val_peek(0).sval; }
break;
case 127:
//#line 362 "thrill_grammar.y"
{ yyval.sval = val_peek(2).sval + "/" + val_peek(0).sval; }
break;
case 128:
//#line 363 "thrill_grammar.y"
{ yyval.sval = "(" + val_peek(1).sval + ")"; }
break;
case 129:
//#line 364 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 130:
//#line 365 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 131:
//#line 369 "thrill_grammar.y"
{ yyval.sval = "if(" + val_peek(2).sval + ")" + val_peek(0).sval; }
break;
case 132:
//#line 371 "thrill_grammar.y"
{ yyval.sval = "if(" + val_peek(4).sval + ")" + val_peek(2).sval + "\nelse" + val_peek(0).sval; }
break;
case 133:
//#line 373 "thrill_grammar.y"
{ yyval.sval = val_peek(2).sval + " <= " + val_peek(0).sval;}
break;
case 134:
//#line 374 "thrill_grammar.y"
{ yyval.sval = val_peek(2).sval + " >= " + val_peek(0).sval;}
break;
case 135:
//#line 375 "thrill_grammar.y"
{ yyval.sval = val_peek(2).sval + " != " + val_peek(0).sval;}
break;
case 136:
//#line 376 "thrill_grammar.y"
{ yyval.sval = val_peek(2).sval + " < " + val_peek(0).sval;}
break;
case 137:
//#line 377 "thrill_grammar.y"
{ yyval.sval = val_peek(2).sval + " > " + val_peek(0).sval;}
break;
case 138:
//#line 380 "thrill_grammar.y"
{ yyval.sval = val_peek(2).sval + " " + val_peek(1).sval + ";"; }
break;
case 139:
//#line 381 "thrill_grammar.y"
{ yyval.sval = val_peek(2).sval + ", " + val_peek(0).sval; }
break;
case 140:
//#line 382 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 141:
//#line 386 "thrill_grammar.y"
{ yyval.sval = val_peek(3).sval + "(" + val_peek(1).sval + ");" ; }
break;
case 142:
//#line 389 "thrill_grammar.y"
{ yyval.sval = yyval.sval + "," + val_peek(0).sval; }
break;
case 143:
//#line 390 "thrill_grammar.y"
{yyval.sval = val_peek(0).sval;}
break;
case 144:
//#line 391 "thrill_grammar.y"
{ yyval.sval = ""; }
break;
case 145:
//#line 395 "thrill_grammar.y"
{ yyval.sval = val_peek(2).sval + " " + val_peek(1).sval + ";"; }
break;
case 146:
//#line 399 "thrill_grammar.y"
{ yyval.sval = val_peek(4).sval + ", " + val_peek(2).sval + "=" + val_peek(0).sval; }
break;
case 147:
//#line 401 "thrill_grammar.y"
{ yyval.sval = val_peek(2).sval + " = " + val_peek(0).sval; }
break;
case 148:
//#line 404 "thrill_grammar.y"
{ yyval.sval = initializeDuration(val_peek(4).sval, val_peek(3).sval, new Double(val_peek(1).dval).toString() ); }
break;
case 149:
//#line 407 "thrill_grammar.y"
{yyval.sval = "do" + val_peek(5).sval + "while (" + val_peek(2).sval + ");" ; }
break;
case 150:
//#line 410 "thrill_grammar.y"
{ yyval.sval = "return " + val_peek(1).sval + ";"; }
break;
case 151:
//#line 412 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 152:
//#line 413 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 153:
//#line 414 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 154:
//#line 418 "thrill_grammar.y"
{yyval.sval = generateRevenue(val_peek(3).sval, val_peek(1).sval) ; }
break;
case 155:
//#line 421 "thrill_grammar.y"
{ yyval.sval = "System.out.println(" + val_peek(1).sval + ");" ; }
break;
case 156:
//#line 423 "thrill_grammar.y"
{yyval.sval = generateSimulate(val_peek(1).sval); }
break;
case 157:
//#line 426 "thrill_grammar.y"
{ yyval.sval = val_peek(2).sval + "+" + val_peek(0).sval; }
break;
case 158:
//#line 428 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 159:
//#line 431 "thrill_grammar.y"
{ yyval.sval = "\"" + val_peek(0).sval + "\""; }
break;
case 160:
//#line 432 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 161:
//#line 435 "thrill_grammar.y"
{ yyval.sval = "Crowd"; }
break;
case 162:
//#line 436 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 163:
//#line 439 "thrill_grammar.y"
{ yyval.sval = "double"; }
break;
case 164:
//#line 440 "thrill_grammar.y"
{ yyval.sval = "String"; }
break;
case 165:
//#line 443 "thrill_grammar.y"
{ yyval.sval = "Days"; }
break;
case 166:
//#line 444 "thrill_grammar.y"
{ yyval.sval = "Weeks"; }
break;
case 167:
//#line 445 "thrill_grammar.y"
{ yyval.sval = "Months"; }
break;
case 168:
//#line 446 "thrill_grammar.y"
{ yyval.sval = "Years"; }
break;
case 169:
//#line 449 "thrill_grammar.y"
{ yyval.sval = new Double(val_peek(0).dval).toString(); }
break;
case 170:
//#line 450 "thrill_grammar.y"
{ yyval.sval = "\"" + val_peek(1).sval + "\""; }
break;
case 171:
//#line 453 "thrill_grammar.y"
{ yyval.sval = new Double(val_peek(0).dval).toString(); }
break;
case 172:
//#line 454 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 173:
//#line 457 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 174:
//#line 458 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 175:
//#line 459 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 176:
//#line 460 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 177:
//#line 461 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 178:
//#line 462 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 179:
//#line 463 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 180:
//#line 464 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 181:
//#line 465 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 182:
//#line 467 "thrill_grammar.y"
{ yyval.sval = ""; }
break;
//#line 1925 "Parser.java"
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
