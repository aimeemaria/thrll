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






//#line 2 "grammar_thrill.y"
import java.lang.Math;
import java.io.*;
import java.util.StringTokenizer;
//#line 21 "Parser.java"




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
    0,    1,    3,    3,    2,    5,    9,    9,   10,   10,
   11,   11,   11,   11,   12,   13,   14,   15,    4,   17,
   17,   19,   19,   20,   20,   20,   21,   22,   23,   18,
   18,   24,   26,   26,   27,   27,   28,   28,   28,   29,
   33,   33,   33,   34,   34,   34,   34,   34,   35,   36,
   37,   38,   39,   30,   41,   41,   41,   42,   42,   42,
   42,   42,   43,   44,   45,   46,   47,   31,   49,   49,
   49,   50,   50,   50,   50,   51,   52,   53,   54,    7,
   56,   56,   57,   57,   57,   59,   59,   62,   62,   55,
   63,   65,   64,   64,   66,   66,   66,   66,   66,   66,
   66,   66,   66,   66,   67,   67,   67,   67,   67,   68,
   68,   68,   68,   69,   69,   69,   69,   69,   70,   70,
   70,   70,   71,   78,   79,   79,   80,   80,   80,   80,
   80,   80,   80,   72,   72,   82,   82,   82,   82,   82,
   82,   82,   82,   73,   84,   84,   74,   85,   85,   86,
   86,   75,   87,   87,   76,   76,   76,   88,   91,   91,
   91,   91,   89,   90,   92,   92,   93,   93,   60,   60,
   94,   94,   83,   83,   81,   81,   77,   77,   32,    8,
   58,   25,   16,   40,   48,   61,    6,
};
final static short yylen[] = {                            2,
    2,    3,    2,    1,    1,    3,    1,    1,    2,    1,
    1,    1,    1,    1,    4,    4,    4,    4,    4,    1,
    1,    2,    1,    1,    1,    1,    4,    4,    4,    2,
    1,    4,    1,    4,    2,    1,    1,    1,    1,    5,
    1,    2,    1,    1,    1,    1,    1,    1,    4,    4,
    4,    4,    4,    5,    1,    2,    1,    1,    1,    1,
    1,    1,    4,    4,    4,    4,    4,    5,    1,    2,
    1,    1,    1,    1,    1,    4,    4,    4,    4,    3,
    6,    1,    1,    1,    1,    3,    1,    2,    1,    3,
    1,    1,    2,    1,    1,    1,    1,    1,    1,    1,
    1,    1,    1,    1,    6,    6,    6,    6,    6,    6,
    6,    6,    6,    6,    6,    6,    6,    6,    6,    6,
    6,    6,    3,    1,    2,    1,    3,    3,    3,    3,
    3,    1,    1,    5,    7,    3,    3,    3,    3,    3,
    3,    1,    1,    2,    3,    2,    4,    2,    1,    2,
    1,    2,    4,    5,    1,    1,    1,    7,    1,    1,
    1,    1,    2,    3,    3,    1,    1,    1,    1,    1,
    1,    1,    1,    1,    1,    3,    1,    1,    1,    1,
    1,    1,    1,    1,    1,    1,    0,
};
final static short yydefred[] = {                       187,
    0,    0,    0,    4,    0,    1,    5,    0,    0,  187,
    3,    0,  180,    0,  183,    0,    0,   91,   80,  187,
    7,   10,    6,    0,   20,   23,  187,    0,   94,    0,
    0,    9,   11,   12,   13,   14,   31,    0,    0,   22,
   24,   25,   26,    0,   92,    0,  173,    0,  174,    0,
    0,    0,    0,  124,   90,   93,   95,   96,   97,   98,
   99,  100,  101,  102,  103,  104,    0,    0,  155,  156,
  157,    0,    0,    0,    0,    0,   30,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  175,  186,    0,  168,  167,  163,    0,    0,
    0,    0,    0,  144,  152,    0,    0,    0,    0,  182,
    0,    0,    0,    0,    0,  142,  143,    0,  177,  178,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  164,  149,    0,    0,    0,  132,  126,
  123,    0,  133,  146,    0,    0,   17,   16,   15,   18,
   33,    0,  187,   27,   28,   29,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  176,  165,    0,  151,  148,  147,
    0,  125,    0,    0,    0,    0,    0,  145,    0,    0,
   36,    0,  141,  138,  136,  137,  139,  140,    0,  179,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  184,
    0,    0,  179,    0,    0,  184,    0,    0,    0,  179,
    0,    0,    0,  150,  131,    0,    0,  129,  130,  153,
    0,    0,    0,    0,    0,   35,   37,   38,   39,    0,
  106,  115,  120,  105,  114,  119,  107,  116,  121,  118,
  111,  109,  110,  117,  122,  113,  112,  108,    0,    0,
  154,   34,    0,    0,  185,    0,  135,  159,  161,  160,
  162,  158,    0,    0,    0,    0,    0,    0,   41,   43,
    0,   55,   57,    0,   69,   71,    0,    0,   42,   44,
   45,   46,   47,   48,    0,   56,   58,   59,   60,   61,
   62,    0,   70,   72,   73,   74,   75,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   50,   49,   51,   53,   52,
   64,   63,   65,   67,   66,   77,   76,   78,   79,
};
final static short yydgoto[] = {                          1,
    2,    6,    3,   10,   11,    4,    7,   14,   23,   24,
   32,   33,   34,   35,   36,   16,   27,   38,   28,   40,
   41,   42,   43,   77,  111,  153,  192,  236,  237,  238,
  239,  201,  281,  289,  290,  291,  292,  293,  294,  202,
  284,  296,  297,  298,  299,  300,  301,  203,  287,  303,
  304,  305,  306,  307,   19,    0,    0,   53,    0,    0,
  120,    0,   20,   30,   55,   56,   57,   58,   59,   60,
   61,   62,   63,   64,   65,   66,  121,   67,  141,  142,
  117,  118,   68,  104,  137,  179,  105,   69,   70,   71,
  272,   98,   99,    0,
};
final static short yysindex[] = {                         0,
    0, -297, -230,    0, -236,    0,    0, -228, -214,    0,
    0, -241,    0, -212,    0, -177, -184,    0,    0,    0,
    0,    0,    0, -193,    0,    0,    0, -188,    0, -240,
 -119,    0,    0,    0,    0,    0,    0, -175,   10,    0,
    0,    0,    0,    0,    0, -154,    0, -116,    0, -136,
 -115, -105, -101,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0, -124,  -70,    0,    0,
    0,  -62,  -59,  -57,  -46,  -44,    0,  -35,  -30,  -27,
 -218, -225, -225, -225, -225, -225, -225, -225, -225, -225,
 -225,  -54,    0,    0,  -23,    0,    0,    0,  -25,  -19,
  -70, -176,  -41,    0,    0,   -1,    6,   15,   30,    0,
 -243,   32,   38,   39, -218,    0,    0,  -10,    0,    0,
  -45,  -31,    9,   11,   12,   13,   14,   16,   17,   18,
 -228,   23, -115,    0,    0,   45,   50, -118,    0,    0,
    0,  -22,    0,    0,  -70, -221,    0,    0,    0,    0,
    0,   19,    0,    0,    0,    0,    2, -218, -218, -218,
 -218, -218, -241,   53,   53,   53,   54, -228,   56, -228,
   57, -228,   58,   59,    0,    0,  -70,    0,    0,    0,
    1,    0, -118, -118, -118, -118, -111,    0,  -85,   60,
    0, -255,    0,    0,    0,    0,    0,    0,   33,    0,
   61,   62,   63,   64,   65,   66,   67,   68,   69,    0,
   70,   71,    0,   72,   73,    0,   74,   75,   76,    0,
   77,   78,   35,    0,    0, -128, -128,    0,    0,    0,
  -70,   79,   56,   54,   82,    0,    0,    0,    0, -241,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  -17,   80,
    0,    0,   51,   52,    0,   55,    0,    0,    0,    0,
    0,    0,  -44,  -44,  -44,   81,   84,   87,    0,    0,
   49,    0,    0,   83,    0,    0,   85, -252,    0,    0,
    0,    0,    0,    0, -135,    0,    0,    0,    0,    0,
    0, -182,    0,    0,    0,    0,    0,   92,   93,   94,
   95,   96,   97,   98,   99,  100,  101,  102,  103,  104,
  105,  106,  107,  108,  109,  110,  111,  112,  113,  114,
  115,  116,  117,  118,  119,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0, -187,    0, -186,   86,    0,    0,    0,
    0,    0,    0, -268,    0,    0,    0,  -86,    0,    0,
    0,    0,    0,    0,    0,    0,    0, -215,    0,    0,
    0,    0,    0,  -18,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0, -185,    0,
  120,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  120,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  120,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  -64,    0,    0,    0,    0,    0,    0, -131,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  -37,  -34,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0, -100, -100, -100,    0,    0,
  -91,    0,    0,  -88,    0,    0,  -72,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,    0,  354,    0,    0,  -14,    0, -126,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0, -198,    0,    0,    0,    0,    0,
    0, -145,    0,    0,    0,    0,    0,    0,    0, -155,
    0,    0,    0,    0,    0,    0,    0, -157,    0,    0,
    0,    0,    0,    0, -159,    0,    0,    0,    0,    0,
  -29,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  263,    0,    0,  198,    0,    0, -121,
  -48,  -67,    0,  221,  205,    0,  153,    0,    0,    0,
    0,  253,    0,    0,
};
final static int YYTABLESIZE=390;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         22,
   54,   26,   97,  199,  174,   29,    5,  206,  209,  205,
  208,  211,   37,  218,    8,  217,  181,  151,   44,  204,
  207,   96,   18,  214,   45,  233,   12,  222,    8,  308,
   13,  309,  119,   94,  310,    8,   93,  311,  103,   93,
   94,  212,  234,  215,   15,  219,  221,  157,   21,  235,
   46,  116,    8,  143,  312,   47,  152,   95,  115,   48,
   95,  226,  227,  228,  229,   49,    9,   19,   50,   51,
   52,  136,  139,  166,  276,  277,  278,  266,  264,  166,
  267,   93,   44,   25,   97,  116,  135,  263,   19,  143,
  194,  195,  196,  197,  198,  187,  187,  189,    8,  318,
  138,  319,   95,   96,  320,  166,   31,  187,  139,  187,
  166,   39,  187,  187,  166,  187,  187,  187,   76,  321,
  166,  178,   81,  166,  166,  166,   92,  134,  116,  116,
  116,  116,  116,  134,  143,  143,  143,  143,  191,   93,
   94,  102,   93,   94,  185,  186,  313,  136,  314,  144,
  145,  315,  316,  139,  139,  139,  139,  100,  138,  134,
   95,  101,  135,   95,  134,   82,  317,   83,  134,   72,
   84,   85,   86,   87,  134,  230,  231,  134,  134,  134,
  187,   73,  187,   74,   88,   89,   90,   75,   94,   40,
   91,   40,   54,  187,   54,  106,   21,  187,  107,  187,
  108,  260,   40,  187,  187,   54,   40,   21,   68,   54,
   68,  109,   40,   40,  110,   54,   54,   21,   32,  144,
  145,   68,  112,  127,  146,   68,  128,  113,  131,   32,
  114,   68,   68,  127,  127,  132,  128,  128,  182,   32,
  127,  134,  186,  128,  181,  133,  164,  186,  183,  184,
  185,  186,  186,  186,  186,  186,  158,  159,  160,  147,
  165,  280,  283,  286,  161,  162,  148,  163,  158,  159,
  160,  183,  184,  185,  186,  149,  161,  162,  225,  193,
  122,  123,  124,  125,  126,  127,  128,  129,  130,   78,
  150,   79,  154,   80,  268,  269,  270,  271,  155,  156,
  166,  175,  167,  168,  169,  170,  177,  171,  172,  173,
  180,  200,  210,  190,  213,  216,  220,  232,  240,  259,
  223,  241,  242,  243,  244,  245,  246,  247,  248,  249,
  250,  251,  252,  253,  254,  255,  256,  257,  258,  262,
  265,  279,  273,  274,  282,  146,  275,  285,  288,  322,
  323,  324,  325,  326,  327,  328,  329,  330,  331,  332,
  333,  334,  335,   17,  140,  188,  336,  337,  338,  339,
  340,  341,  342,  343,  344,  345,  346,  347,  348,  349,
  187,  224,  295,  261,  302,  176,    0,    0,    0,    2,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         14,
   30,   16,   51,  163,  131,   20,  304,  165,  166,  165,
  166,  167,   27,  171,  283,  171,  138,  261,  259,  165,
  166,   51,  264,  169,  265,  281,  263,  173,  297,  282,
  259,  284,  258,  259,  287,  304,  258,  290,   68,  258,
  259,  168,  298,  170,  259,  172,  173,  115,  261,  305,
  291,   81,  283,  102,  307,  296,  300,  279,  277,  300,
  279,  183,  184,  185,  186,  306,  297,  283,  309,  310,
  311,  101,  102,  259,  273,  274,  275,  235,  234,  265,
  240,  258,  259,  261,  133,  115,  101,  233,  304,  138,
  158,  159,  160,  161,  162,  283,  283,  146,  283,  282,
  277,  284,  279,  133,  287,  291,  300,  294,  138,  297,
  296,  300,  300,  300,  300,  145,  304,  304,  294,  302,
  306,  136,  277,  309,  310,  311,  263,  259,  158,  159,
  160,  161,  162,  265,  183,  184,  185,  186,  153,  258,
  259,  266,  258,  259,  273,  274,  282,  177,  284,  261,
  262,  287,  288,  183,  184,  185,  186,  263,  277,  291,
  279,  263,  177,  279,  296,  282,  302,  284,  300,  289,
  287,  288,  289,  290,  306,  261,  262,  309,  310,  311,
  281,  301,  283,  303,  301,  302,  303,  307,  259,  281,
  307,  283,  281,  294,  283,  258,  283,  298,  258,  300,
  258,  231,  294,  304,  305,  294,  298,  294,  281,  298,
  283,  258,  304,  305,  259,  304,  305,  304,  283,  261,
  262,  294,  258,  261,  266,  298,  261,  258,  283,  294,
  258,  304,  305,  271,  272,  259,  271,  272,  261,  304,
  278,  261,  261,  278,  263,  271,  292,  266,  271,  272,
  273,  274,  271,  272,  273,  274,  267,  268,  269,  261,
  292,  276,  277,  278,  275,  276,  261,  278,  267,  268,
  269,  271,  272,  273,  274,  261,  275,  276,  278,  278,
   83,   84,   85,   86,   87,   88,   89,   90,   91,  280,
  261,  282,  261,  284,  312,  313,  314,  315,  261,  261,
  292,  279,  292,  292,  292,  292,  262,  292,  292,  292,
  261,  259,  259,  295,  259,  259,  259,  258,  286,  285,
  262,  261,  261,  261,  261,  261,  261,  261,  261,  261,
  261,  261,  261,  261,  261,  261,  261,  261,  261,  261,
  259,  261,  292,  292,  261,  266,  292,  261,  300,  258,
  258,  258,  258,  258,  258,  258,  258,  258,  258,  258,
  258,  258,  258,   10,  102,  145,  261,  261,  261,  261,
  261,  261,  261,  261,  261,  261,  261,  261,  261,  261,
  261,  177,  300,  231,  300,  133,   -1,   -1,   -1,  304,
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
"usercode : start",
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
"park_definition : Park park_name park_elements land_definitions",
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
"land_definition : Land land_name land_attributes land_elements",
"land_attributes : SEMICOLON",
"land_attributes : Set Location NUMBER SEMICOLON",
"land_elements : land_elements land_element",
"land_elements : empty",
"land_element : attraction_definition",
"land_element : restaurant_definition",
"land_element : store_definition",
"attraction_definition : Attraction attraction_name In land_name attraction_attributes",
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
"restaurant_definition : Restaurant restaurant_name In land_name restaurant_attributes",
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
"store_definition : Store store_name In land_name store_attributes",
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
"function : return_type function_name COLON actual_parameters block function",
"function : empty",
"return_type : Number",
"return_type : String",
"return_type : empty",
"actual_parameters : data_type variable_name actual_parameter",
"actual_parameters : empty",
"actual_parameter : COMMA actual_parameters",
"actual_parameter : empty",
"block : start_block statements end_block",
"start_block : OPEN_PARAN",
"end_block : CLOSE_PARAN",
"statements : statements statement",
"statements : empty",
"statement : add_attraction_attribute",
"statement : add_crowd_attribute",
"statement : add_restaurant_attribute",
"statement : add_store_attribute",
"statement : assignment",
"statement : condition",
"statement : declaration",
"statement : function_call",
"statement : initialization",
"statement : thrill_functions",
"add_attraction_attribute : Set Cost value In attraction_name SEMICOLON",
"add_attraction_attribute : Set Capacity value In attraction_name SEMICOLON",
"add_attraction_attribute : Set Employees value In attraction_name SEMICOLON",
"add_attraction_attribute : Set ThrillLevel value In attraction_name SEMICOLON",
"add_attraction_attribute : Set EnergyLost value In attraction_name SEMICOLON",
"add_crowd_attribute : Set Size value In crowd_name SEMICOLON",
"add_crowd_attribute : Set EnergyLevel value In crowd_name SEMICOLON",
"add_crowd_attribute : Set ThrillLevel value In crowd_name SEMICOLON",
"add_crowd_attribute : Set SpendingCapacity value In crowd_name SEMICOLON",
"add_restaurant_attribute : Set Cost value In restaurant_name SEMICOLON",
"add_restaurant_attribute : Set Capacity value In restaurant_name SEMICOLON",
"add_restaurant_attribute : Set Employees value In restaurant_name SEMICOLON",
"add_restaurant_attribute : Set SpendLevel value In restaurant_name SEMICOLON",
"add_restaurant_attribute : Set EnergyIncrease value In restaurant_name SEMICOLON",
"add_store_attribute : Set Cost value In store_name SEMICOLON",
"add_store_attribute : Set Capacity value In store_name SEMICOLON",
"add_store_attribute : Set Employees value In store_name SEMICOLON",
"add_store_attribute : Set SpendLevel value In store_name SEMICOLON",
"assignment : left_side EQUAL right_side",
"left_side : variable_name",
"right_side : arithmetic_expression SEMICOLON",
"right_side : function_call",
"arithmetic_expression : arithmetic_expression PLUS arithmetic_expression",
"arithmetic_expression : arithmetic_expression MINUS arithmetic_expression",
"arithmetic_expression : arithmetic_expression MUL arithmetic_expression",
"arithmetic_expression : arithmetic_expression DIV arithmetic_expression",
"arithmetic_expression : OPEN arithmetic_expression CLOSE",
"arithmetic_expression : variable_name",
"arithmetic_expression : constant",
"condition : If OPEN relational_expression CLOSE block",
"condition : If OPEN relational_expression CLOSE block Else block",
"relational_expression : relational_expression LESSEQUAL relational_expression",
"relational_expression : relational_expression GREATEQUAL relational_expression",
"relational_expression : relational_expression NOTEQUAL relational_expression",
"relational_expression : relational_expression LESS relational_expression",
"relational_expression : relational_expression GREAT relational_expression",
"relational_expression : OPEN relational_expression CLOSE",
"relational_expression : variable_name",
"relational_expression : constant",
"declaration : primitive_type declaration_list",
"declaration_list : variable_name COMMA declaration_list",
"declaration_list : variable_name SEMICOLON",
"function_call : function_name COLON formal_parameters SEMICOLON",
"formal_parameters : variable_name formal_parameter",
"formal_parameters : empty",
"formal_parameter : COMMA formal_parameters",
"formal_parameter : empty",
"initialization : primitive_type initialization_list",
"initialization_list : variable_name EQUAL constant SEMICOLON",
"initialization_list : variable_name EQUAL constant COMMA initialization_list",
"thrill_functions : calculate_revenue",
"thrill_functions : output",
"thrill_functions : simulate",
"calculate_revenue : CalculateRevenue COLON Crowd crowd_name COMMA Duration duration_type",
"duration_type : Days",
"duration_type : Weeks",
"duration_type : Months",
"duration_type : Years",
"output : Print constant_variable_chain",
"simulate : Simulate COLON SEMICOLON",
"constant_variable_chain : constant_or_variable PLUS constant_variable_chain",
"constant_variable_chain : constant_or_variable",
"constant_or_variable : constant",
"constant_or_variable : variable_name",
"data_type : complex_type",
"data_type : primitive_type",
"complex_type : Crowd",
"complex_type : Duration",
"primitive_type : Number",
"primitive_type : String",
"constant : NUMBER",
"constant : Quote ID Quote",
"value : NUMBER",
"value : variable_name",
"attraction_name : ID",
"crowd_name : ID",
"function_name : ID",
"land_name : ID",
"park_name : ID",
"restaurant_name : ID",
"store_name : ID",
"variable_name : ID",
"empty :",
};

//#line 336 "grammar_thrill.y"
  private Yylex lexer;
  
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
//#line 685 "Parser.java"
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
