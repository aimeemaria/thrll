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
import java.util.Hashtable;
import java.util.ArrayList;
import java.util.Iterator;
//#line 23 "Parser.java"




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
   66,   65,   64,   64,   63,   62,   62,   62,   61,   60,
   60,   84,   79,   83,   83,   58,   58,   58,   57,   55,
   54,   53,   53,   52,   52,   51,   51,   50,   50,   56,
   56,   56,   56,   49,   49,   48,   48,    1,    2,   59,
    3,    4,    5,    6,    7,    8,   91,   92,
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
    3,    3,    3,    1,    4,    3,    1,    1,    3,    5,
    3,    5,    7,    3,    2,    1,    1,    1,    6,    3,
    4,    3,    1,    1,    1,    1,    1,    1,    1,    1,
    1,    1,    1,    1,    1,    1,    1,    1,    1,    1,
    1,    1,    1,    1,    1,    1,    1,    0,
};
final static short yydefred[] = {                       188,
    0,    0,    0,    4,  187,    0,    0,    3,  188,   84,
  188,    1,  186,    0,  179,    0,  183,    0,    0,    0,
   87,    7,    0,    6,   10,   21,    0,   19,   24,    0,
   90,   91,    0,   86,   92,    0,    9,   11,   12,   13,
   14,    0,   23,   25,   26,   27,  188,   97,   85,  188,
   88,  181,    0,    0,    0,    0,    0,    0,    0,    0,
   32,    0,  100,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   31,   98,    0,    0,  168,    0,    0,  169,
    0,    0,    0,  170,  172,  171,  173,    0,    0,    0,
  158,  157,    0,  156,  110,  106,  105,  104,    0,  102,
  101,   99,  108,  103,  109,  107,   96,    0,   17,   16,
   15,   18,   28,   29,   30,   33,  182,    0,    0,  174,
  155,  175,  165,  164,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  163,    0,    0,    0,
    0,    0,    0,    0,    0,  166,  167,    0,    0,   95,
    0,    0,    0,    0,  154,  176,  177,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  160,    0,
    0,  147,    0,  148,    0,  149,    0,  142,    0,    0,
    0,    0,  133,  126,  125,    0,  121,   94,    0,   89,
    0,  188,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  162,  161,  145,    0,  151,    0,  143,    0,  132,
    0,  123,  124,    0,    0,    0,    0,    0,    0,    0,
   37,  138,  136,  137,  141,  139,  140,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  180,
    0,  146,    0,  152,  131,    0,    0,  129,  130,   93,
    0,    0,    0,    0,   36,   38,   39,   40,    0,    0,
  111,  112,  113,  114,  115,  116,  117,  119,  118,  120,
  159,  150,   35,   41,  178,   56,  184,   71,  185,  135,
  153,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   43,    0,   45,   58,    0,   60,   73,    0,   75,    0,
   44,   46,   47,   48,   49,   50,    0,   59,   61,   62,
   63,   64,   65,    0,   74,   76,   77,   78,   79,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   52,   51,   53,
   55,   54,   67,   66,   68,   70,   69,   81,   80,   82,
   83,
};
final static short yydgoto[] = {                          1,
  284,   14,   88,  116,   16,  286,  288,  157,   23,   37,
   24,   38,   39,   40,   41,   27,   43,   28,   44,   45,
   46,  192,  230,  265,  266,  302,  311,  312,  313,  314,
  315,  316,  267,  305,  318,  319,  320,  321,  322,  323,
  268,  308,  325,  326,  327,  328,  329,  158,  124,  147,
  148,  125,  138,   91,   92,   93,   94,   95,  251,  142,
   96,  173,   97,  143,   98,  153,  186,   99,  187,  100,
  101,  102,   62,   49,  149,   33,   34,   11,  103,  104,
   12,   20,  105,  106,    8,    2,    9,   60,   73,    3,
    4,    5,   47,  151,  292,  293,  294,   19,   64,   50,
  107,
};
final static short yysindex[] = {                         0,
    0, -253, -301,    0,    0, -243, -243,    0,    0,    0,
    0,    0,    0, -225,    0, -219,    0, -237, -206, -273,
    0,    0, -290,    0,    0,    0, -238,    0,    0, -195,
    0,    0, -243,    0,    0,  -28,    0,    0,    0,    0,
    0, -180,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0, -183, -161, -130, -127, -120, -113, -109, -134,
    0, -233,    0,  -77,  -89,  -50,  -45,  -37,  -29,  -15,
  -13, -243,    0,    0,  -36, -195,    0, -188, -123,    0,
  -18, -170,   12,    0,    0,    0,    0,   14,    0, -243,
    0,    0, -243,    0,    0,    0,    0,    0,   -3,    0,
    0,    0,    0,    0,    0,    0,    0, -274,    0,    0,
    0,    0,    0,    0,    0,    0,    0, -243,  -59,    0,
    0,    0,    0,    0,   41, -138, -138, -138, -138, -138,
 -138, -138, -138, -138, -138, -243,    0, -110, -243, -243,
   32,  -92,  -54,   42, -252,    0,    0, -243, -164,    0,
    8,   37,   45,   34,    0,    0,    0,   17,   35,   36,
   38,   39,   40,   43,   44,   46,   47,   62,    0, -170,
   64,    0,  -22,    0, -250,    0, -243,    0, -243,   68,
 -239,   66,    0,    0,    0, -148,    0,    0, -274,    0,
   48,    0, -170, -170, -170, -170, -170, -170, -195, -243,
 -243, -243, -243, -243, -243, -243, -243, -243, -243, -243,
 -243,    0,    0,    0, -243,    0,   69,    0,   73,    0,
   10,    0,    0, -239, -239, -239, -239, -243,   72, -277,
    0,    0,    0,    0,    0,    0,    0,   51,   63,   81,
   82,   84,   85,   86,   87,   88,   89,   90,   91,    0,
   92,    0, -250,    0,    0,  -91,  -91,    0,    0,    0,
   93, -243, -243, -243,    0,    0,    0,    0, -195,   94,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   65,   67,   70, -243, -243, -243,   95,   96,   98,
    0,   60,    0,    0,   61,    0,    0,   71,    0, -248,
    0,    0,    0,    0,    0,    0,  -16,    0,    0,    0,
    0,    0,    0, -156,    0,    0,    0,    0,    0,  106,
  107,  108,  109,  110,  111,  112,  113,  115,  116,  117,
  118,  119,  120,  121,  122,  123,  124,  125,  126,  127,
  128,  129,  130,  131,  132,  133,  134,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0, -194,    0,  -70,    0,   74,    0,    2,
    0,    0, -199,    0,    0,    0, -121,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0, -266,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0, -217,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0, -116,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   31,
   33,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  -20,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  -67,
    0,    0,    0,    0,    0,    0,    0, -157,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    7,   25,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  -72,  -72,  -72,
    0,  -69,    0,    0,  -40,    0,    0,  -35,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,
};
final static short yygindex[] = {                         0,
    0,  -74,  347, -119,    0,    0,    0,   -6,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  187, -131,  319,
  207,  -79,    0,    0,    0,    0,  252,    0,    0,    0,
    0,    0,  253,    0,    0,  199, -172,    0,    0,    0,
    0,    0,    0,  -63,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  391,    0,    0,    0,    0,
    1,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,
};
final static int YYTABLESIZE=400;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         15,
   17,    5,  137,   10,  262,  120,   13,  120,  221,  146,
   36,   21,  119,  183,   25,   13,   29,   20,  120,   13,
   35,  263,   77,   31,  181,   13,   52,  122,  264,  122,
    6,   74,   80,   32,  330,   22,  331,  181,   20,  332,
  122,   26,  333,  216,    7,  181,    6,   61,  122,  183,
   63,  256,  257,  258,  259,   89,   30,   81,   75,  334,
   76,  168,   42,   77,  171,  117,   78,   79,   48,  120,
   13,  123,  121,   80,   65,  123,   81,   82,   83,   84,
   85,   86,   87,  141,    8,  190,  144,  120,   13,  188,
  212,  122,  183,  183,  183,  183,   66,  189,    8,   48,
   57,  134,   58,  188,   59,    8,  188,  134,  150,  122,
  188,  152,  223,  232,  233,  234,  235,  236,  237,  156,
   13,  282,  224,  225,  226,  227,  340,   67,  341,   15,
   68,  342,   15,  172,  134,  238,  134,   69,  182,  134,
  174,  188,  134,  134,   70,  188,  343,  188,   71,  134,
  169,  170,  134,  134,  134,  134,  134,  134,  134,  126,
   72,  127,   22,  123,  128,  129,  130,  131,  176,  177,
  217,  109,  218,   22,  220,  298,  299,  300,  132,  133,
  134,  226,  227,   22,  135,  108,  123,  123,  123,  123,
  123,  123,  231,  152,  240,  241,  242,  243,  244,  245,
  246,  247,  248,  249,  250,  290,  178,  179,  252,  188,
  110,  188,   42,  188,   42,  111,   34,  220,  220,  220,
  220,  260,  188,  112,  188,   42,  188,   34,  188,   42,
  188,  113,  188,  188,  188,   42,   42,   34,  214,  215,
  118,   57,  181,   57,  136,  114,   72,  115,   72,  154,
  132,  132,  132,  132,   57,  285,  287,  289,   57,   72,
  188,   53,  145,   72,   57,   57,  335,  127,  336,   72,
   72,  337,  338,   54,  139,   55,  140,  127,  127,   56,
  224,  225,  226,  227,  127,  128,  339,  255,  117,  117,
  117,  188,  188,  144,  144,  128,  128,  175,  303,  306,
  309,  155,  128,  193,  194,  195,  196,  180,  191,  201,
  200,  197,  198,  159,  160,  161,  162,  163,  164,  165,
  166,  167,  199,  211,  213,  219,  222,  202,  203,  261,
  204,  205,  206,  254,  253,  207,  208,  269,  209,  210,
  270,  271,  272,  229,  273,  274,  275,  276,  277,  278,
  279,  280,  281,  283,  291,  301,  304,  295,  307,  296,
  310,  317,  297,  344,  345,  346,  347,  348,  349,  350,
  351,  324,  352,  353,  354,  355,  356,  357,    2,   51,
   90,  358,  359,  360,  361,  362,  363,  364,  365,  366,
  367,  368,  369,  370,  371,  228,  184,  185,  239,   18,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                          6,
    7,    0,   82,  305,  282,  258,  259,  258,  181,  284,
  301,   11,   76,  145,   14,  259,   16,  284,  258,  259,
   20,  299,  297,  297,  277,  259,   33,  280,  306,  280,
  284,  265,  307,  307,  283,  261,  285,  277,  305,  288,
  280,  261,  291,  175,  298,  263,  284,   47,  266,  181,
   50,  224,  225,  226,  227,   62,  263,  310,  292,  308,
  294,  136,  301,  297,  139,   72,  300,  301,  264,  258,
  259,   78,  261,  307,  258,   82,  310,  311,  312,  313,
  314,  315,  316,   90,  284,  149,   93,  258,  259,  284,
  170,  280,  224,  225,  226,  227,  258,  262,  298,  264,
  281,  259,  283,  298,  285,  305,  301,  265,  108,  280,
  305,  118,  261,  193,  194,  195,  196,  197,  198,  258,
  259,  253,  271,  272,  273,  274,  283,  258,  285,  136,
  258,  288,  139,  140,  292,  199,  294,  258,  145,  297,
  140,  148,  300,  301,  258,  262,  303,  264,  258,  307,
  261,  262,  310,  311,  312,  313,  314,  315,  316,  283,
  295,  285,  284,  170,  288,  289,  290,  291,  261,  262,
  177,  261,  179,  295,  181,  295,  296,  297,  302,  303,
  304,  273,  274,  305,  308,  263,  193,  194,  195,  196,
  197,  198,  192,  200,  201,  202,  203,  204,  205,  206,
  207,  208,  209,  210,  211,  269,  261,  262,  215,  282,
  261,  284,  282,  284,  284,  261,  284,  224,  225,  226,
  227,  228,  295,  261,  295,  295,  299,  295,  301,  299,
  301,  261,  305,  306,  305,  305,  306,  305,  261,  262,
  277,  282,  263,  284,  263,  261,  282,  261,  284,  309,
  271,  272,  273,  274,  295,  262,  263,  264,  299,  295,
  259,  290,  266,  299,  305,  306,  283,  261,  285,  305,
  306,  288,  289,  302,  263,  304,  263,  271,  272,  308,
  271,  272,  273,  274,  278,  261,  303,  278,  295,  296,
  297,  261,  262,  261,  262,  271,  272,  266,  298,  299,
  300,  261,  278,  267,  268,  269,  270,  266,  301,  293,
  277,  275,  276,  127,  128,  129,  130,  131,  132,  133,
  134,  135,  278,  262,  261,  258,  261,  293,  293,  258,
  293,  293,  293,  261,  266,  293,  293,  287,  293,  293,
  278,  261,  261,  296,  261,  261,  261,  261,  261,  261,
  261,  261,  261,  261,  261,  261,  261,  293,  261,  293,
  301,  301,  293,  258,  258,  258,  258,  258,  258,  258,
  258,  301,  258,  258,  258,  258,  258,  258,  305,   33,
   62,  261,  261,  261,  261,  261,  261,  261,  261,  261,
  261,  261,  261,  261,  261,  189,  145,  145,  200,    9,
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
"relational_expression : variable_name ISEQUAL constant_or_variable",
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

//#line 509 "thrill_grammar.y"
	private Yylex lexer;
	public int yyline = 1;
	public int yycolumn = 0;
	private Hashtable<String, String> thrillObjects = new Hashtable<String, String>();
	private ArrayList<ThrillUserFunction> userFunctions = new ArrayList<ThrillUserFunction>();
	private Hashtable<String, String[]> definedFunctions = new Hashtable<String, String[]>();
	private ArrayList<ThrillException> errorList = new ArrayList<ThrillException>();
	int noOfParks = 0, noOfLands = 0;
	boolean[] locationValues = new boolean[6];
	String parkName = null;
	String scopeName = null;
	private int actualParams = 0;
	private int formalParams = 0;
	final int MAX_LIMIT_PARK = 1;
	final int MAX_LIMIT_LANDS = 6;
	boolean createPositionFile = false;

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

	public String getErrorLocationInfo(boolean onlyLineInfo){
		if(onlyLineInfo)
			return "Error on line(" + yyline + "): ";
		else
			return "Error on line(" + yyline + ") and column(" + yycolumn + "): ";		
	}

	public void yyerror(String error) {
		try{			
			if(stateptr > 0) {
				System.out.print("Syntax " + getErrorLocationInfo(true));
				System.out.println(": Illegal token '" + lexer.yytext() + "'");
			}
		}
		catch(Exception ex){			
		}
	}

	public Parser(Reader r, boolean createFile) {
		lexer = new Yylex(r, this);
		this.createPositionFile = createFile;
	}

	static boolean interactive;

	public String createAttractionDefinition(String landName, String attractionName) throws ThrillException{ 
		String result = "\nAttraction " + attractionName + " = new Attraction();\n";
		String key = "Global." + landName;

		if(!thrillObjects.containsKey(key)){
			errorList.add(ThrillException.ObjectNotFoundException(getErrorLocationInfo(false), landName));
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
			errorList.add(ThrillException.ObjectNotFoundException(getErrorLocationInfo(false), landName));
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
			errorList.add(ThrillException.ObjectNotFoundException(getErrorLocationInfo(false), landName));
		}

		String setName = storeName + ".setStoreName(\"" + storeName + "\");\n";
		String setLand = storeName + ".setLand(" + landName + ");\n";
		String addStore = landName + ".addStore(" + storeName + ");\n";
		result += setName + setLand + addStore;

		return result;
	}

	public void addFunctionToHashtable(String functionName, String parameters) throws ThrillException{
		String[] formalParameters = null;

		if(parameters != null) {
			formalParameters = parameters.split(",");
		}
		
		ThrillUserFunction userFunction = new ThrillUserFunction(functionName);
		userFunction.setLine(yyline);
		userFunction.setFormalParameters(formalParameters);
		userFunction.setParameters(formalParams);
		userFunction.setScopeName(scopeName);
				
		// adding the function and the parameters to the list of user functions		
		userFunctions.add(userFunction);				
		formalParams = 0;
	}

	public void addToHashtable(String identifier, String type) throws ThrillException{
		String key = identifier;
		if(scopeName != null){
			key = scopeName + "." + identifier;
		}

		if(thrillObjects.containsKey(key)){
			errorList.add(ThrillException.RedefinitionException(getErrorLocationInfo(false), identifier));
		}

		if(type == "Park"){
			if(noOfParks > 1){
				errorList.add(ThrillException.ExceededObjectLimitException(getErrorLocationInfo(false), "Park", MAX_LIMIT_PARK));
			}
			else{
				parkName = identifier;
			}

		}
		else if(type == "Land"){
			if(noOfLands > 6)
				errorList.add(ThrillException.ExceededObjectLimitException(getErrorLocationInfo(false), "Land", MAX_LIMIT_LANDS));
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
			ThrillException.ObjectNotFoundException(getErrorLocationInfo(false), identifier);
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
			int location = Integer.parseInt(value);
			if(locationValues[location - 1]) {
				errorList.add(ThrillException.AlreadyDefinedLocationException(getErrorLocationInfo(true), location));
			}
			else{
				locationValues[location - 1] = true; 
			}
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

		if(createPositionFile){
			String createFileString = p + ".setCreateFile(true);\n";
			result += createFileString;
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
			errorList.add(ThrillException.ObjectNotFoundException(getErrorLocationInfo(false), variable));
		}
		else{
			if(function.equalsIgnoreCase("Capacity")){
				if(obj.equalsIgnoreCase("Crowd")){
					errorList.add(ThrillException.UnexpectedTypeException(getErrorLocationInfo(false), variable, "Crowd"));
				}
				result = variable.concat(".set" + function + "(" + value + ");");
			}
			else if(function.equalsIgnoreCase("Cost")){
				if(obj.equalsIgnoreCase("Crowd")){
					errorList.add(ThrillException.UnexpectedTypeException(getErrorLocationInfo(false), variable, "Crowd"));
				}
				result = variable.concat(".set" + function + "(" + value + ");");
			}
			else if(function.equalsIgnoreCase("Employees")){
				if(obj.equalsIgnoreCase("Crowd")){
					errorList.add(ThrillException.UnexpectedTypeException(getErrorLocationInfo(false), variable, "Crowd"));
				}
				result = variable.concat(".set" + function + "(" + value + ");");
			}
			else if(function.equalsIgnoreCase("EnergyIncrease")){
				if(!(obj.equalsIgnoreCase("Restaurant"))){
					errorList.add(ThrillException.UnexpectedTypeException(getErrorLocationInfo(false), "Restaurant", variable));
				}
				result = variable.concat(".set" + function + "(" + value + ");");

			}
			else if(function.equalsIgnoreCase("EnergyLevel")){
				if(!(obj.equalsIgnoreCase("Crowd"))){
					errorList.add(ThrillException.UnexpectedTypeException(getErrorLocationInfo(false), "Crowd", variable));
				}
				result = variable.concat(".set" + function + "(" + value + ");");				
			}
			else if(function.equalsIgnoreCase("EnergyLost")){
				if(!(obj.equalsIgnoreCase("Attraction"))){
					errorList.add(ThrillException.UnexpectedTypeException(getErrorLocationInfo(false), "Attraction", variable));
				}
				result = variable.concat(".set" + function + "(" + value + ");");
			}
			else if(function.equalsIgnoreCase("Size")){
				if(!(obj.equalsIgnoreCase("Crowd"))){
					errorList.add(ThrillException.UnexpectedTypeException(getErrorLocationInfo(false), "Crowd", variable));
				}
				result = variable.concat(".set" + function + "(" + value + ");");				
			}
			else if(function.equalsIgnoreCase("SpendingCapacity")){
				if(!(obj.equalsIgnoreCase("Crowd"))){
					errorList.add(ThrillException.UnexpectedTypeException(getErrorLocationInfo(false), "Crowd", variable));
				}
				result = variable.concat(".set" + function + "(" + value + ");");				
			}
			else if(function.equalsIgnoreCase("SpendLevel")){
				if(!(obj.equalsIgnoreCase("Restaurant") || obj.equalsIgnoreCase("Store"))){
					errorList.add(ThrillException.UnexpectedTypeException(getErrorLocationInfo(false), "Restaurant/Store", variable));
				}
				result = variable.concat(".set" + function + "(" + value + ");");				
			}
			else if(function.equalsIgnoreCase("ThrillLevel")){
				if(!(obj.equalsIgnoreCase("Attraction") || obj.equalsIgnoreCase("Crowd"))){
					errorList.add(ThrillException.UnexpectedTypeException(getErrorLocationInfo(false), "Attraction/Crowd", variable));
				}
				result = variable.concat(".set" + function + "(" + value + ");");				
			}
			else{
				// error condition
				errorList.add(ThrillException.UnexpectedTypeException(getErrorLocationInfo(false), "Attraction/Crowd/Restaurant/Store", variable));
			}
		}

		return result;
	}

	public String generateRelationalExpression(String value1, String relOp, String value2) throws ThrillException{    
		String result = "";
		boolean val1 = checkHashtable(value1);
		boolean val2 = checkHashtable(value2);
		if(val1 == true && val2 == true) 
		{ 
			if(checkSemanticTypes(value1, value2)){
				result = value1 + relOp + value2;
			}
			else{
				String type1 = thrillObjects.get(scopeName + "." + value1);
				String type2 = thrillObjects.get(scopeName + "." + value2);
				errorList.add(ThrillException.TypesMismatchException(getErrorLocationInfo(false), type1, type2));
			}
		}
		else 
		{ 
			if(val1 == false)
				errorList.add(ThrillException.ObjectNotFoundException(getErrorLocationInfo(false), value1));      
			if(val2 == false)
				errorList.add(ThrillException.ObjectNotFoundException(getErrorLocationInfo(false), value2));	
		}

		return result;
	}

	public String checkSemanticValue(String value) throws ThrillException{
		String key = scopeName + "." + value;
		String type = thrillObjects.get(key);
		if(type == null){
			errorList.add(ThrillException.ObjectNotFoundException(getErrorLocationInfo(false), value));
			return null;
		}
		else if(!type.equalsIgnoreCase("Number")){			
			errorList.add(ThrillException.UnexpectedTypeException(getErrorLocationInfo(false), "Number", type));
		}
		return value;
	}

	public boolean checkSemanticTypes(String value1, String value2){
		String key1 = scopeName + "." + value1;
		String key2 = scopeName + "." + value2;

		if(thrillObjects.get(key1).equalsIgnoreCase(thrillObjects.get(key2))){
			return true;
		}
		else{
			return false;
		}
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

		if(block.contains("return")){
			beginIndex = block.indexOf("return");
			endIndex = block.indexOf(";", beginIndex);
			returnStmt = block.substring(beginIndex, endIndex + 1);	
			checkReturn = true;
		}

		if(checkReturn && !checkReturnType(returnType, returnStmt) || 
				!returnType.equalsIgnoreCase("void") && returnStmt == null){
			errorList.add(ThrillException.MissingReturnStatementException(getErrorLocationInfo(false), "Invalid/Missing return statement"));
		}

		if(!definedFunctions.containsKey(functionName)){
			definedFunctions.put(functionName, parameters.trim().split(","));
		}

		result = returnType + " " + functionName + "(" + parameters + ")\n" + block;

		return result;
	}

	boolean validParametersType(String functionName, String[] formalParameters, String[] actualParameters, int line) throws ThrillException{
		String[] paramTypes = null;

		if(formalParameters.length != actualParameters.length){
			errorList.add(ThrillException.InsufficientParamsException("Error on line(" + line +"):", functionName));
		}
		else{
			paramTypes = new String[formalParameters.length];
			for(int i = 0; i < formalParameters.length; ++i){
				String type = actualParameters[i].trim().split(" ")[0];
				paramTypes[i] = (type.equalsIgnoreCase("double")) ? "Number" : "String"; 

				String identifier = "Start.".concat(formalParameters[i]);
				if(thrillObjects.containsKey(identifier)){
					type = thrillObjects.get(identifier);
					if(!type.equalsIgnoreCase(paramTypes[i])){
						return false;
					}
				}
			}
		}

		return true;
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
					errorList.add(ThrillException.ObjectNotFoundException(getErrorLocationInfo(false), retVal));
				}

				if(Character.isDigit(retVal.charAt(0)) && type.equalsIgnoreCase("Number")){
					result = true;
				}
				else if(type.equalsIgnoreCase(temp)){
					result = true;
				}
				else{
					errorList.add(ThrillException.UnexpectedTypeException(getErrorLocationInfo(false), returnType, type));
				}				
			}
			else{
				errorList.add(ThrillException.UnexpectedTypeException(getErrorLocationInfo(false), returnType, "void"));
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

	public String[] generateParamTypes(String[] params) {
		String[] types = new String[params.length];
		for(int i = 0; i < params.length; ++i){
			types[i] = thrillObjects.get("Start.".concat(params[i]));
		}
		return types;
	}
	
	public String validateAttributeValue(String attribute, String value) throws ThrillException{
		String result = "";;
		double d = 0;
		
		if(Character.isDigit(value.charAt(0))) {
			d = Double.parseDouble(value);

			if(attribute.equalsIgnoreCase("Admission") ||
					attribute.equalsIgnoreCase("Cost")){	
				if(d < 0)
					errorList.add(ThrillException.InvalidArgumentException(getErrorLocationInfo(false), attribute + " cannot be less than zero"));
				result = value;
			}
			else{
				int i = (int)d;
				if(attribute.equalsIgnoreCase("Location")){
					if(i < 1 || i > 6)
						errorList.add(ThrillException.InvalidArgumentException(getErrorLocationInfo(false), attribute + " should be a value between 1 and 6"));			
				}
				else if(attribute.equalsIgnoreCase("Capacity")  || 
						attribute.equalsIgnoreCase("Employees") ||
						attribute.equalsIgnoreCase("Size")){
					if(i < 0)
						errorList.add(ThrillException.InvalidArgumentException(getErrorLocationInfo(false), attribute + " cannot be less than zero"));

				}
				else {
					if(i < 0 || i > 20)
						errorList.add(ThrillException.InvalidArgumentException(getErrorLocationInfo(false), attribute + " cannot be less than zero or greater than 20"));
				}
				result = new Integer(i).toString();
			}
		}
		else{
			result = value;
		}
		return result;
	}

	// have to check the second argument as well
	public String generateRevenue(String crowdName, String duration) throws ThrillException {		
		String result = null;
		String c = thrillObjects.get("Global." + crowdName);
		if(c == null){
			errorList.add(ThrillException.ObjectNotFoundException(getErrorLocationInfo(false), crowdName));
		}		

		String d = thrillObjects.get(scopeName + "." + duration);
		if(d == null){
			errorList.add(ThrillException.ObjectNotFoundException(getErrorLocationInfo(false), duration));	
		}

		result = parkName + ".calculateRevenue(" + crowdName + ", " + duration + ");";
		return result;
	}

	public String generateSimulate(String crowdName) throws ThrillException{
		String result = null;
		String c = thrillObjects.get("Global." + crowdName);
		if(c == null){
			errorList.add(ThrillException.ObjectNotFoundException(getErrorLocationInfo(false), crowdName));
		}		
		result = parkName + ".simulate(" + crowdName + ");";
		return result;
	}

	public void generateThrillProgram(String definitions, String usercode) throws ThrillException{
		String classStart = "public class ThrillProgram {\n";
		String classEnd = "\n}";
		String main = "public static void main(String[] args){\n";		
		usercode = usercode.substring(1);

		validateAllUserFunctions();
		
		if(errorList.size() != 0){
			listParserErrors();
			throw new ThrillException("Compilation Failed");
		}

		try{
			FileWriter writer = new FileWriter(new File("ThrillProgram.java"));
			String buffer = classStart + main + definitions +  usercode + classEnd;
			writer.write(buffer);
			writer.close();
		}catch(IOException io){			
		}		
	}

	public void validateAllUserFunctions() throws ThrillException{
	
		// Need to check all the function definitions before generating the intermediate code.
		Iterator<ThrillUserFunction> userFunctionsList = userFunctions.iterator();
		
		while(userFunctionsList.hasNext()){
			ThrillUserFunction userFunction = userFunctionsList.next();
			
			// Get all the relevant info
			String[] formalParameters = userFunction.getFormalParameters();
			String functionName = userFunction.getFunctionName();
			
			if(definedFunctions.containsKey(functionName)){
				// We need to compare the actual and formal parameters
				// We need to compare the parameters of these functions				
				String[] actualParameters = definedFunctions.get(functionName);
				actualParameters = (actualParameters[0].equalsIgnoreCase("") ? null : actualParameters);
	
				if(formalParameters == null && actualParameters == null){
					// found a match
					continue;
				}
				else if(formalParameters != null && actualParameters != null) {
					boolean valid = validParametersType(functionName, formalParameters, actualParameters, userFunction.getLine());
					if(valid){
						continue;
					}
					else{
						String[] types = generateParamTypes(formalParameters);
						String lineInfo = "Error on line(" + userFunction.getLine() + "): ";
						errorList.add(ThrillException.UndefinedFunctionException(lineInfo, functionName, types));
					}
				}
				else{
				    // function has not been defined.
				    String[] types = generateParamTypes(formalParameters);
				    String lineInfo = "Error on line(" + userFunction.getLine() + "): ";
				    errorList.add(ThrillException.UndefinedFunctionException(lineInfo, functionName, types));
				}					
			}
			else{
				// function has not been defined.
				String[] types = generateParamTypes(formalParameters);						
			    String lineInfo = "Error on line(" + userFunction.getLine() + "): ";
			    errorList.add(ThrillException.UndefinedFunctionException(lineInfo, functionName, types));
			}
		}		
	}

	public String initializeDuration(String durationType, String durationName, String value) throws ThrillException{
		String result = null;
		if(!checkHashtable(durationName)){
			errorList.add(ThrillException.ObjectNotFoundException(getErrorLocationInfo(false), durationName));
		}

		double temp = Double.parseDouble(value);
		int days = (int)temp;
		result = durationType + " " + durationName + " = new " + durationType + "(" + days + ");"; 
		return result;
	}
	
	public void listParserErrors(){
		for(int i = 0; i < errorList.size(); ++i){
			System.out.println(errorList.get(i).getMessage());
		}
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
		boolean createFile = false;
		
		if(args.length < 1){
			System.out.println("Usage: java Parser <thrill_program.txt>");
			return;
		}
		else if(args.length == 2){
			createFile = Boolean.parseBoolean(args[1]);
		}

		// parse a file
		yyparser = new Parser(new FileReader(args[0]), createFile);

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
			System.out.println(ex.getMessage() + "\n");			
		}
	}
//#line 1400 "Parser.java"
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
//#line 177 "thrill_grammar.y"
{ generateThrillProgram(val_peek(1).sval, val_peek(0).sval); System.out.println("Total number of lines in the input: " + (yyline-1)); }
break;
case 2:
//#line 179 "thrill_grammar.y"
{ yyval.sval = val_peek(2).sval + val_peek(1).sval + val_peek(0).sval; }
break;
case 3:
//#line 180 "thrill_grammar.y"
{ yyval.sval += val_peek(0).sval; }
break;
case 4:
//#line 181 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 5:
//#line 184 "thrill_grammar.y"
{ yyval.sval = val_peek(1).sval + val_peek(0).sval; }
break;
case 6:
//#line 187 "thrill_grammar.y"
{ scopeName = "Global"; addToHashtable(val_peek(1).sval, "Crowd"); 
			  yyval.sval = "\nCrowd " + val_peek(1).sval + " = " + "new Crowd();\n" + generateSetAttribute(val_peek(1).sval, val_peek(0).sval); 
			}
break;
case 7:
//#line 191 "thrill_grammar.y"
{}
break;
case 8:
//#line 192 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 9:
//#line 195 "thrill_grammar.y"
{ yyval.sval += val_peek(0).sval;}
break;
case 10:
//#line 196 "thrill_grammar.y"
{ yyval.sval = "";}
break;
case 11:
//#line 199 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 12:
//#line 200 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 13:
//#line 201 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 14:
//#line 202 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 15:
//#line 205 "thrill_grammar.y"
{ yyval.sval = ":SpendingCapacity:" + val_peek(1).dval;}
break;
case 16:
//#line 206 "thrill_grammar.y"
{ yyval.sval = ":Size:" + val_peek(1).dval; }
break;
case 17:
//#line 207 "thrill_grammar.y"
{ yyval.sval = ":EnergyLevel:" + val_peek(1).dval;}
break;
case 18:
//#line 208 "thrill_grammar.y"
{ yyval.sval = ":ThrillLevel:" + val_peek(1).dval;}
break;
case 19:
//#line 210 "thrill_grammar.y"
{ scopeName = "Global"; addToHashtable(val_peek(1).sval, "Park"); }
break;
case 20:
//#line 211 "thrill_grammar.y"
{ yyval.sval = "\nPark " + val_peek(3).sval + " = " + "new Park();\n" + generateSetAttribute(val_peek(3).sval, val_peek(2).sval) + val_peek(0).sval; }
break;
case 21:
//#line 213 "thrill_grammar.y"
{}
break;
case 22:
//#line 214 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 23:
//#line 217 "thrill_grammar.y"
{ yyval.sval += val_peek(0).sval; }
break;
case 24:
//#line 218 "thrill_grammar.y"
{ yyval.sval = ""; }
break;
case 25:
//#line 221 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 26:
//#line 222 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 27:
//#line 223 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 28:
//#line 226 "thrill_grammar.y"
{ yyval.sval = ":Admission:" + val_peek(1).dval;}
break;
case 29:
//#line 227 "thrill_grammar.y"
{ yyval.sval = ":Capacity:" + val_peek(1).dval;}
break;
case 30:
//#line 228 "thrill_grammar.y"
{ yyval.sval = ":Cost:" + val_peek(1).dval;}
break;
case 31:
//#line 230 "thrill_grammar.y"
{ yyval.sval += val_peek(0).sval; }
break;
case 32:
//#line 231 "thrill_grammar.y"
{ yyval.sval = ""; }
break;
case 33:
//#line 233 "thrill_grammar.y"
{ addToHashtable(val_peek(0).sval, "Land"); }
break;
case 34:
//#line 236 "thrill_grammar.y"
{
			        yyval.sval = "\nLand " + val_peek(3).sval + " = " + "new Land();\n" + generateSetAttribute(val_peek(3).sval, val_peek(1).sval) + val_peek(0).sval; 					   					 }
break;
case 35:
//#line 239 "thrill_grammar.y"
{ yyval.sval = ":Location:" + val_peek(1).dval; }
break;
case 36:
//#line 240 "thrill_grammar.y"
{ yyval.sval += val_peek(0).sval; }
break;
case 37:
//#line 241 "thrill_grammar.y"
{ yyval.sval = ""; }
break;
case 38:
//#line 244 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 39:
//#line 245 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 40:
//#line 246 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 41:
//#line 249 "thrill_grammar.y"
{ addToHashtable(val_peek(0).sval, "Attraction"); }
break;
case 42:
//#line 251 "thrill_grammar.y"
{ yyval.sval = createAttractionDefinition(val_peek(1).sval, val_peek(4).sval) + generateSetAttribute(val_peek(4).sval, val_peek(0).sval); }
break;
case 43:
//#line 252 "thrill_grammar.y"
{ yyval.sval = ""; }
break;
case 44:
//#line 253 "thrill_grammar.y"
{ yyval.sval += val_peek(0).sval; }
break;
case 45:
//#line 254 "thrill_grammar.y"
{ yyval.sval = ""; }
break;
case 46:
//#line 256 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 47:
//#line 257 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 48:
//#line 258 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 49:
//#line 259 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 50:
//#line 260 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 51:
//#line 262 "thrill_grammar.y"
{ yyval.sval = ":Cost:" + val_peek(1).dval;}
break;
case 52:
//#line 263 "thrill_grammar.y"
{ yyval.sval = ":Capacity:" + val_peek(1).dval;}
break;
case 53:
//#line 264 "thrill_grammar.y"
{ yyval.sval = ":Employees:" + val_peek(1).dval;}
break;
case 54:
//#line 265 "thrill_grammar.y"
{ yyval.sval = ":ThrillLevel:" + val_peek(1).dval;}
break;
case 55:
//#line 266 "thrill_grammar.y"
{ yyval.sval = ":EnergyLost:" + val_peek(1).dval;}
break;
case 56:
//#line 268 "thrill_grammar.y"
{ addToHashtable(val_peek(0).sval, "Restaurant"); }
break;
case 57:
//#line 270 "thrill_grammar.y"
{ yyval.sval = createRestaurantDefinition(val_peek(1).sval, val_peek(4).sval) + generateSetAttribute(val_peek(4).sval, val_peek(0).sval); }
break;
case 58:
//#line 272 "thrill_grammar.y"
{ yyval.sval = ""; }
break;
case 59:
//#line 273 "thrill_grammar.y"
{ yyval.sval += val_peek(0).sval; }
break;
case 60:
//#line 274 "thrill_grammar.y"
{ yyval.sval = ""; }
break;
case 61:
//#line 276 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 62:
//#line 277 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 63:
//#line 278 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 64:
//#line 279 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 65:
//#line 280 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 66:
//#line 282 "thrill_grammar.y"
{ yyval.sval = ":Cost:" + val_peek(1).dval;}
break;
case 67:
//#line 283 "thrill_grammar.y"
{ yyval.sval = ":Capacity:" + val_peek(1).dval;}
break;
case 68:
//#line 284 "thrill_grammar.y"
{ yyval.sval = ":Employees:" + val_peek(1).dval;}
break;
case 69:
//#line 285 "thrill_grammar.y"
{ yyval.sval = ":SpendLevel:" + val_peek(1).dval;}
break;
case 70:
//#line 286 "thrill_grammar.y"
{ yyval.sval = ":EnergyIncrease:" + val_peek(1).dval;}
break;
case 71:
//#line 288 "thrill_grammar.y"
{ addToHashtable(val_peek(0).sval, "Store"); }
break;
case 72:
//#line 290 "thrill_grammar.y"
{ yyval.sval = createStoreDefinition(val_peek(1).sval, val_peek(4).sval) + generateSetAttribute(val_peek(4).sval, val_peek(0).sval); }
break;
case 73:
//#line 292 "thrill_grammar.y"
{ yyval.sval = ""; }
break;
case 74:
//#line 293 "thrill_grammar.y"
{ yyval.sval += val_peek(0).sval; }
break;
case 75:
//#line 294 "thrill_grammar.y"
{ yyval.sval = ""; }
break;
case 76:
//#line 296 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 77:
//#line 297 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 78:
//#line 298 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 79:
//#line 299 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 80:
//#line 302 "thrill_grammar.y"
{ yyval.sval = ":Cost:" + val_peek(1).dval; }
break;
case 81:
//#line 303 "thrill_grammar.y"
{ yyval.sval = ":Capacity:" + val_peek(1).dval;}
break;
case 82:
//#line 304 "thrill_grammar.y"
{ yyval.sval = ":Employees:" + val_peek(1).dval;}
break;
case 83:
//#line 305 "thrill_grammar.y"
{ yyval.sval = ":SpendLevel:" + val_peek(1).dval;}
break;
case 84:
//#line 307 "thrill_grammar.y"
{ scopeName = "Start"; }
break;
case 85:
//#line 307 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 86:
//#line 309 "thrill_grammar.y"
{ yyval.sval = val_peek(1).sval + val_peek(0).sval; }
break;
case 87:
//#line 310 "thrill_grammar.y"
{ yyval.sval = ""; }
break;
case 88:
//#line 313 "thrill_grammar.y"
{ scopeName = val_peek(0).sval; addToHashtable(val_peek(0).sval, "Function"); }
break;
case 89:
//#line 314 "thrill_grammar.y"
{
		yyval.sval = "\n" + "public static " + generateFunction(val_peek(5).sval, val_peek(4).sval, val_peek(1).sval, val_peek(0).sval);
		actualParams = 0;
	  }
break;
case 90:
//#line 319 "thrill_grammar.y"
{ yyval.sval = "double"; }
break;
case 91:
//#line 320 "thrill_grammar.y"
{ yyval.sval = "String"; }
break;
case 92:
//#line 321 "thrill_grammar.y"
{yyval.sval = "void"; }
break;
case 93:
//#line 324 "thrill_grammar.y"
{ addToHashtable(val_peek(0).sval, val_peek(1).sval); yyval.sval = val_peek(3).sval + ", " + val_peek(1).sval + " " + val_peek(0).sval; ++actualParams; }
break;
case 94:
//#line 325 "thrill_grammar.y"
{ addToHashtable(val_peek(0).sval, val_peek(1).sval); yyval.sval = val_peek(1).sval + " " + val_peek(0).sval; ++actualParams; }
break;
case 95:
//#line 326 "thrill_grammar.y"
{ yyval.sval = ""; }
break;
case 96:
//#line 329 "thrill_grammar.y"
{ yyval.sval = "{" + val_peek(1).sval + "\n}"; }
break;
case 99:
//#line 334 "thrill_grammar.y"
{ yyval.sval = yyval.sval + "\n" +  val_peek(0).sval; }
break;
case 100:
//#line 335 "thrill_grammar.y"
{ yyval.sval = ""; }
break;
case 101:
//#line 337 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 102:
//#line 338 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 103:
//#line 339 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 104:
//#line 340 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 105:
//#line 341 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 106:
//#line 342 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 107:
//#line 343 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 108:
//#line 344 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 109:
//#line 345 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 110:
//#line 346 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 111:
//#line 349 "thrill_grammar.y"
{ yyval.sval = generateAttribute(val_peek(1).sval, "Capacity", val_peek(3).sval); }
break;
case 112:
//#line 350 "thrill_grammar.y"
{ yyval.sval = generateAttribute(val_peek(1).sval, "Cost", val_peek(3).sval); }
break;
case 113:
//#line 351 "thrill_grammar.y"
{ yyval.sval = generateAttribute(val_peek(1).sval, "Employees", val_peek(3).sval); }
break;
case 114:
//#line 352 "thrill_grammar.y"
{ yyval.sval = generateAttribute(val_peek(1).sval, "EnergyIncrease", val_peek(3).sval); }
break;
case 115:
//#line 353 "thrill_grammar.y"
{ yyval.sval = generateAttribute(val_peek(1).sval, "EnergyLevel", val_peek(3).sval); }
break;
case 116:
//#line 354 "thrill_grammar.y"
{ yyval.sval = generateAttribute(val_peek(1).sval, "EnergyLost", val_peek(3).sval); }
break;
case 117:
//#line 355 "thrill_grammar.y"
{ yyval.sval = generateAttribute(val_peek(1).sval, "Size", val_peek(3).sval); }
break;
case 118:
//#line 356 "thrill_grammar.y"
{ yyval.sval = generateAttribute(val_peek(1).sval, "SpendingCapacity", val_peek(3).sval); }
break;
case 119:
//#line 357 "thrill_grammar.y"
{ yyval.sval = generateAttribute(val_peek(1).sval, "SpendLevel", val_peek(3).sval); }
break;
case 120:
//#line 358 "thrill_grammar.y"
{ yyval.sval = generateAttribute(val_peek(1).sval, "ThrillLevel", val_peek(3).sval); }
break;
case 121:
//#line 361 "thrill_grammar.y"
{ yyval.sval = val_peek(2).sval + " = " + val_peek(0).sval;}
break;
case 122:
//#line 364 "thrill_grammar.y"
{ 
    boolean exists = checkHashtable(val_peek(0).sval); 
    if(exists) { yyval.sval = val_peek(0).sval; } 
    else{ ThrillException.ObjectNotFoundException("Error on line(" + yyline + ") and column(" + yycolumn + "): ", val_peek(0).sval); } 
}
break;
case 123:
//#line 371 "thrill_grammar.y"
{ 
    boolean exists = checkHashtable(val_peek(1).sval); 
    if(exists) { yyval.sval = val_peek(1).sval + ";"; } 
    else{ ThrillException.ObjectNotFoundException("Error on line(" + yyline + ") and column(" + yycolumn + "): ", val_peek(1).sval); } 
}
break;
case 124:
//#line 376 "thrill_grammar.y"
{ yyval.sval = val_peek(1).sval + ";"; }
break;
case 125:
//#line 377 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 126:
//#line 378 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 127:
//#line 381 "thrill_grammar.y"
{ yyval.sval = val_peek(2).sval + "+" + val_peek(0).sval; }
break;
case 128:
//#line 382 "thrill_grammar.y"
{ yyval.sval = val_peek(2).sval + "-" + val_peek(0).sval; }
break;
case 129:
//#line 383 "thrill_grammar.y"
{ yyval.sval = val_peek(2).sval + "*" + val_peek(0).sval; }
break;
case 130:
//#line 384 "thrill_grammar.y"
{ checkDivideByZero(val_peek(2).sval, val_peek(0).sval); yyval.sval = val_peek(2).sval + "/" + val_peek(0).sval; }
break;
case 131:
//#line 385 "thrill_grammar.y"
{ yyval.sval = "(" + val_peek(1).sval + ")"; }
break;
case 132:
//#line 387 "thrill_grammar.y"
{ 
                        boolean exists = checkHashtable(val_peek(0).sval); 
                        if(exists){ yyval.sval = checkSemanticValue(val_peek(0).sval); } 
                        else{ ThrillException.ObjectNotFoundException("Error on line(" + yyline + ") and column(" + yycolumn + "): ", val_peek(0).sval); }
                     }
break;
case 133:
//#line 392 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 134:
//#line 395 "thrill_grammar.y"
{ yyval.sval = "if(" + val_peek(2).sval + ")" + val_peek(0).sval; }
break;
case 135:
//#line 396 "thrill_grammar.y"
{ yyval.sval = "if(" + val_peek(4).sval + ")" + val_peek(2).sval + "\nelse" + val_peek(0).sval; }
break;
case 136:
//#line 398 "thrill_grammar.y"
{ yyval.sval = generateRelationalExpression(val_peek(2).sval, " <= ", val_peek(0).sval); }
break;
case 137:
//#line 399 "thrill_grammar.y"
{ yyval.sval = generateRelationalExpression(val_peek(2).sval, " >= ", val_peek(0).sval); }
break;
case 138:
//#line 400 "thrill_grammar.y"
{ yyval.sval = generateRelationalExpression(val_peek(2).sval, " != ", val_peek(0).sval); }
break;
case 139:
//#line 401 "thrill_grammar.y"
{ yyval.sval = generateRelationalExpression(val_peek(2).sval, " < ", val_peek(0).sval);  }
break;
case 140:
//#line 402 "thrill_grammar.y"
{ yyval.sval = generateRelationalExpression(val_peek(2).sval, " > ", val_peek(0).sval);  }
break;
case 141:
//#line 403 "thrill_grammar.y"
{ yyval.sval = generateRelationalExpression(val_peek(2).sval, " == ", val_peek(0).sval); }
break;
case 142:
//#line 406 "thrill_grammar.y"
{ addDeclVariables(val_peek(2).sval, val_peek(1).sval); yyval.sval = val_peek(2).sval + " " + val_peek(1).sval + ";"; }
break;
case 143:
//#line 407 "thrill_grammar.y"
{ yyval.sval = val_peek(2).sval + ", " + val_peek(0).sval; }
break;
case 144:
//#line 408 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 145:
//#line 411 "thrill_grammar.y"
{ 
                                                                 yyval.sval = val_peek(3).sval + "(" + val_peek(1).sval + ");" ;
                                                                 addFunctionToHashtable(val_peek(3).sval, val_peek(1).sval);
                                                               }
break;
case 146:
//#line 417 "thrill_grammar.y"
{ yyval.sval = yyval.sval + "," + val_peek(0).sval; ++formalParams; }
break;
case 147:
//#line 418 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; ++formalParams; }
break;
case 148:
//#line 419 "thrill_grammar.y"
{ yyval.sval = ""; }
break;
case 149:
//#line 423 "thrill_grammar.y"
{ 
                    addInitVariables(val_peek(2).sval, val_peek(1).sval); yyval.sval = val_peek(2).sval + " " + val_peek(1).sval + ";"; 
                }
break;
case 150:
//#line 429 "thrill_grammar.y"
{ yyval.sval = val_peek(4).sval + ", " + val_peek(2).sval + " = " + val_peek(0).sval; }
break;
case 151:
//#line 431 "thrill_grammar.y"
{ 
			            yyval.sval = val_peek(2).sval + " = " + val_peek(0).sval; 
			         }
break;
case 152:
//#line 436 "thrill_grammar.y"
{ addToHashtable(val_peek(3).sval, val_peek(4).sval); yyval.sval = initializeDuration(val_peek(4).sval, val_peek(3).sval, new Double(val_peek(1).dval).toString() ); }
break;
case 153:
//#line 439 "thrill_grammar.y"
{yyval.sval = "do" + val_peek(5).sval + "while (" + val_peek(2).sval + ");" ; }
break;
case 154:
//#line 442 "thrill_grammar.y"
{ yyval.sval = "return " + val_peek(1).sval + ";"; }
break;
case 155:
//#line 443 "thrill_grammar.y"
{ yyval.sval = "return ;"; }
break;
case 156:
//#line 446 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 157:
//#line 447 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 158:
//#line 448 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 159:
//#line 452 "thrill_grammar.y"
{yyval.sval = generateRevenue(val_peek(3).sval, val_peek(1).sval) ; }
break;
case 160:
//#line 455 "thrill_grammar.y"
{ yyval.sval = "System.out.println(" + val_peek(1).sval + ");" ; }
break;
case 161:
//#line 457 "thrill_grammar.y"
{yyval.sval = generateSimulate(val_peek(1).sval); }
break;
case 162:
//#line 459 "thrill_grammar.y"
{ yyval.sval = val_peek(2).sval + "+" + val_peek(0).sval;}
break;
case 163:
//#line 460 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 164:
//#line 463 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 165:
//#line 464 "thrill_grammar.y"
{ boolean exists = checkHashtable(val_peek(0).sval); if(exists){ yyval.sval = val_peek(0).sval; } else{ ThrillException.ObjectNotFoundException("Error on line(" + yyline + ") and column(" + yycolumn + "): ", val_peek(0).sval); } }
break;
case 166:
//#line 467 "thrill_grammar.y"
{ yyval.sval = "Crowd"; }
break;
case 167:
//#line 468 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 168:
//#line 471 "thrill_grammar.y"
{ yyval.sval = "double"; }
break;
case 169:
//#line 472 "thrill_grammar.y"
{ yyval.sval = "String"; }
break;
case 170:
//#line 475 "thrill_grammar.y"
{ yyval.sval = "Days"; }
break;
case 171:
//#line 476 "thrill_grammar.y"
{ yyval.sval = "Weeks"; }
break;
case 172:
//#line 477 "thrill_grammar.y"
{ yyval.sval = "Months"; }
break;
case 173:
//#line 478 "thrill_grammar.y"
{ yyval.sval = "Years"; }
break;
case 174:
//#line 481 "thrill_grammar.y"
{ yyval.sval = new Double(val_peek(0).dval).toString(); }
break;
case 175:
//#line 482 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 176:
//#line 485 "thrill_grammar.y"
{ yyval.sval = new Double(val_peek(0).dval).toString(); }
break;
case 177:
//#line 487 "thrill_grammar.y"
{ 
        boolean exists = checkHashtable(val_peek(0).sval); if(exists){ yyval.sval = val_peek(0).sval; } 
        else{ ThrillException.ObjectNotFoundException("Error on line(" + yyline + ") and column(" + yycolumn + "): ", val_peek(0).sval); } 
     }
break;
case 178:
//#line 493 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 179:
//#line 494 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 180:
//#line 495 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 181:
//#line 496 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 182:
//#line 497 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 183:
//#line 498 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 184:
//#line 499 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 185:
//#line 500 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 186:
//#line 501 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 187:
//#line 503 "thrill_grammar.y"
{ yyval.sval = val_peek(0).sval; }
break;
case 188:
//#line 506 "thrill_grammar.y"
{ yyval.sval = ""; }
break;
//#line 2321 "Parser.java"
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
