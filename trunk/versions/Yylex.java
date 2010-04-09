/* The following code was generated by JFlex 1.4.3 on 4/9/10 2:26 PM */

/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.4.3
 * on 4/9/10 2:26 PM from the specification file
 * <tt>thrill_lexer.flex</tt>
 */
class Yylex {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0, 0
  };

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\10\0\1\54\1\55\1\4\2\0\1\5\22\0\1\55\1\71\1\61"+
    "\5\0\1\74\1\75\1\66\1\64\1\57\1\65\1\3\1\67\12\2"+
    "\1\60\1\56\1\72\1\70\1\73\2\0\1\6\1\1\1\21\1\25"+
    "\1\27\3\1\1\33\2\1\1\34\1\37\1\41\1\1\1\43\1\1"+
    "\1\45\1\46\1\50\1\51\1\1\1\52\1\1\1\53\1\1\4\0"+
    "\1\1\1\0\1\17\1\42\1\20\1\7\1\31\1\36\1\32\1\40"+
    "\1\11\1\1\1\44\1\30\1\10\1\14\1\13\1\22\1\1\1\16"+
    "\1\12\1\15\1\26\1\35\1\24\1\1\1\23\1\47\1\62\1\0"+
    "\1\63\uff82\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\0\1\1\1\2\1\3\2\4\17\2\1\5\1\4"+
    "\1\6\1\7\1\10\1\11\1\12\1\13\1\14\1\15"+
    "\1\16\1\17\1\20\1\1\1\21\1\22\1\23\1\24"+
    "\1\0\12\2\1\25\1\2\1\26\17\2\1\27\1\30"+
    "\1\31\1\32\1\3\31\2\1\33\6\2\1\34\2\2"+
    "\1\35\3\2\1\36\2\2\1\37\3\2\1\40\3\2"+
    "\1\41\12\2\1\42\10\2\1\43\3\2\1\44\1\2"+
    "\1\45\2\2\1\46\1\47\1\50\10\2\1\51\1\52"+
    "\1\2\1\53\1\2\1\54\12\2\1\55\10\2\1\56"+
    "\1\57\4\2\1\60\1\2\1\61\3\2\1\62\1\2"+
    "\1\63\7\2\1\64\1\2\1\65\1\2\1\66\1\2"+
    "\1\67\2\2\1\70\1\2\1\71\4\2\1\72\2\2"+
    "\1\73";

  private static int [] zzUnpackAction() {
    int [] result = new int[230];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\76\0\174\0\272\0\76\0\370\0\u0136\0\u0174"+
    "\0\u01b2\0\u01f0\0\u022e\0\u026c\0\u02aa\0\u02e8\0\u0326\0\u0364"+
    "\0\u03a2\0\u03e0\0\u041e\0\u045c\0\u049a\0\76\0\u04d8\0\76"+
    "\0\76\0\76\0\76\0\76\0\76\0\76\0\76\0\76"+
    "\0\76\0\u0516\0\u0554\0\u0592\0\u05d0\0\76\0\76\0\u060e"+
    "\0\u064c\0\u068a\0\u06c8\0\u0706\0\u0744\0\u0782\0\u07c0\0\u07fe"+
    "\0\u083c\0\u087a\0\174\0\u08b8\0\174\0\u08f6\0\u0934\0\u0972"+
    "\0\u09b0\0\u09ee\0\u0a2c\0\u0a6a\0\u0aa8\0\u0ae6\0\u0b24\0\u0b62"+
    "\0\u0ba0\0\u0bde\0\u0c1c\0\u0c5a\0\76\0\76\0\76\0\76"+
    "\0\u060e\0\u0c98\0\u0cd6\0\u0d14\0\u0d52\0\u0d90\0\u0dce\0\u0e0c"+
    "\0\u0e4a\0\u0e88\0\u0ec6\0\u0f04\0\u0f42\0\u0f80\0\u0fbe\0\u0ffc"+
    "\0\u103a\0\u1078\0\u10b6\0\u10f4\0\u1132\0\u1170\0\u11ae\0\u11ec"+
    "\0\u122a\0\u1268\0\174\0\u12a6\0\u12e4\0\u1322\0\u1360\0\u139e"+
    "\0\u13dc\0\174\0\u141a\0\u1458\0\174\0\u1496\0\u14d4\0\u1512"+
    "\0\174\0\u1550\0\u158e\0\174\0\u15cc\0\u160a\0\u1648\0\174"+
    "\0\u1686\0\u16c4\0\u1702\0\174\0\u1740\0\u177e\0\u17bc\0\u17fa"+
    "\0\u1838\0\u1876\0\u18b4\0\u18f2\0\u1930\0\u196e\0\174\0\u19ac"+
    "\0\u19ea\0\u1a28\0\u1a66\0\u1aa4\0\u1ae2\0\u1b20\0\u1b5e\0\174"+
    "\0\u1b9c\0\u1bda\0\u1c18\0\174\0\u1c56\0\174\0\u1c94\0\u1cd2"+
    "\0\174\0\174\0\174\0\u1d10\0\u1d4e\0\u1d8c\0\u1dca\0\u1e08"+
    "\0\u1e46\0\u1e84\0\u1ec2\0\174\0\174\0\u1f00\0\174\0\u1f3e"+
    "\0\174\0\u1f7c\0\u1fba\0\u1ff8\0\u2036\0\u2074\0\u20b2\0\u20f0"+
    "\0\u212e\0\u216c\0\u21aa\0\174\0\u21e8\0\u2226\0\u2264\0\u22a2"+
    "\0\u22e0\0\u231e\0\u235c\0\u239a\0\174\0\174\0\u23d8\0\u2416"+
    "\0\u2454\0\u2492\0\174\0\u24d0\0\174\0\u250e\0\u254c\0\u258a"+
    "\0\174\0\u25c8\0\174\0\u2606\0\u2644\0\u2682\0\u26c0\0\u26fe"+
    "\0\u273c\0\u277a\0\174\0\u27b8\0\174\0\u27f6\0\174\0\u2834"+
    "\0\174\0\u2872\0\u28b0\0\174\0\u28ee\0\174\0\u292c\0\u296a"+
    "\0\u29a8\0\u29e6\0\174\0\u2a24\0\u2a62\0\174";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[230];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\2\1\3\1\4\1\2\1\5\1\6\1\7\12\3"+
    "\1\10\3\3\1\11\1\3\1\12\3\3\1\13\1\14"+
    "\2\3\1\15\1\3\1\16\1\3\1\17\1\3\1\20"+
    "\1\21\1\3\1\22\1\23\1\24\1\25\1\26\1\27"+
    "\1\30\1\31\1\32\1\33\1\34\1\35\1\36\1\37"+
    "\1\40\1\41\1\42\1\43\1\44\1\45\1\46\1\47"+
    "\77\0\2\3\3\0\46\3\24\0\1\4\1\50\76\0"+
    "\1\5\72\0\2\3\3\0\1\3\1\51\5\3\1\52"+
    "\36\3\23\0\2\3\3\0\5\3\1\53\2\3\1\54"+
    "\1\55\34\3\23\0\2\3\3\0\11\3\1\56\6\3"+
    "\1\57\25\3\23\0\2\3\3\0\2\3\1\60\3\3"+
    "\1\61\13\3\1\62\23\3\23\0\2\3\3\0\6\3"+
    "\1\63\1\64\20\3\1\65\15\3\23\0\2\3\3\0"+
    "\5\3\1\66\3\3\1\67\34\3\23\0\2\3\3\0"+
    "\5\3\1\70\40\3\23\0\2\3\3\0\20\3\1\71"+
    "\25\3\23\0\2\3\3\0\10\3\1\72\1\73\34\3"+
    "\23\0\2\3\3\0\23\3\1\74\22\3\23\0\2\3"+
    "\3\0\3\3\1\75\3\3\1\76\4\3\1\77\6\3"+
    "\1\100\22\3\23\0\2\3\3\0\32\3\1\101\13\3"+
    "\23\0\2\3\3\0\6\3\1\102\37\3\23\0\2\3"+
    "\3\0\23\3\1\103\22\3\23\0\2\3\3\0\23\3"+
    "\1\104\22\3\77\0\1\27\110\0\1\105\75\0\1\106"+
    "\75\0\1\107\75\0\1\110\7\0\1\111\74\0\2\3"+
    "\3\0\2\3\1\112\43\3\23\0\2\3\3\0\7\3"+
    "\1\113\36\3\23\0\2\3\3\0\4\3\1\114\41\3"+
    "\23\0\2\3\3\0\5\3\1\115\40\3\23\0\2\3"+
    "\3\0\14\3\1\116\31\3\23\0\2\3\3\0\15\3"+
    "\1\117\30\3\23\0\2\3\3\0\10\3\1\120\35\3"+
    "\23\0\2\3\3\0\14\3\1\121\31\3\23\0\2\3"+
    "\3\0\23\3\1\122\22\3\23\0\2\3\3\0\4\3"+
    "\1\123\41\3\23\0\2\3\3\0\23\3\1\124\22\3"+
    "\23\0\2\3\3\0\12\3\1\125\33\3\23\0\2\3"+
    "\3\0\6\3\1\126\37\3\23\0\2\3\3\0\6\3"+
    "\1\127\37\3\23\0\2\3\3\0\2\3\1\130\43\3"+
    "\23\0\2\3\3\0\3\3\1\131\42\3\23\0\2\3"+
    "\3\0\10\3\1\132\35\3\23\0\2\3\3\0\4\3"+
    "\1\133\2\3\1\134\36\3\23\0\2\3\3\0\2\3"+
    "\1\135\36\3\1\136\4\3\23\0\2\3\3\0\5\3"+
    "\1\137\2\3\1\140\1\141\34\3\23\0\2\3\3\0"+
    "\23\3\1\142\22\3\23\0\2\3\3\0\7\3\1\143"+
    "\36\3\23\0\2\3\3\0\10\3\1\144\35\3\23\0"+
    "\2\3\3\0\7\3\1\145\36\3\23\0\2\3\3\0"+
    "\23\3\1\146\22\3\23\0\2\3\3\0\11\3\1\147"+
    "\34\3\23\0\2\3\3\0\3\3\1\150\42\3\23\0"+
    "\2\3\3\0\10\3\1\151\35\3\23\0\2\3\3\0"+
    "\7\3\1\152\36\3\23\0\2\3\3\0\16\3\1\153"+
    "\27\3\23\0\2\3\3\0\11\3\1\154\34\3\23\0"+
    "\2\3\3\0\4\3\1\155\41\3\23\0\2\3\3\0"+
    "\11\3\1\156\34\3\23\0\2\3\3\0\22\3\1\157"+
    "\23\3\23\0\2\3\3\0\10\3\1\160\35\3\23\0"+
    "\2\3\3\0\23\3\1\161\22\3\23\0\2\3\3\0"+
    "\10\3\1\162\35\3\23\0\2\3\3\0\11\3\1\163"+
    "\34\3\23\0\2\3\3\0\1\3\1\164\44\3\23\0"+
    "\2\3\3\0\7\3\1\165\36\3\23\0\2\3\3\0"+
    "\34\3\1\166\11\3\23\0\2\3\3\0\6\3\1\167"+
    "\37\3\23\0\2\3\3\0\36\3\1\170\7\3\23\0"+
    "\2\3\3\0\7\3\1\171\36\3\23\0\2\3\3\0"+
    "\20\3\1\172\25\3\23\0\2\3\3\0\20\3\1\173"+
    "\25\3\23\0\2\3\3\0\23\3\1\174\22\3\23\0"+
    "\2\3\3\0\10\3\1\175\35\3\23\0\2\3\3\0"+
    "\3\3\1\176\42\3\23\0\2\3\3\0\10\3\1\177"+
    "\35\3\23\0\2\3\3\0\6\3\1\200\37\3\23\0"+
    "\2\3\3\0\3\3\1\201\42\3\23\0\2\3\3\0"+
    "\3\3\1\202\42\3\23\0\2\3\3\0\36\3\1\203"+
    "\7\3\23\0\2\3\3\0\10\3\1\204\35\3\23\0"+
    "\2\3\3\0\4\3\1\205\41\3\23\0\2\3\3\0"+
    "\11\3\1\206\34\3\23\0\2\3\3\0\1\3\1\207"+
    "\44\3\23\0\2\3\3\0\12\3\1\210\33\3\23\0"+
    "\2\3\3\0\7\3\1\211\36\3\23\0\2\3\3\0"+
    "\5\3\1\212\40\3\23\0\2\3\3\0\24\3\1\213"+
    "\21\3\23\0\2\3\3\0\11\3\1\214\34\3\23\0"+
    "\2\3\3\0\7\3\1\215\36\3\23\0\2\3\3\0"+
    "\32\3\1\216\13\3\23\0\2\3\3\0\23\3\1\217"+
    "\22\3\23\0\2\3\3\0\7\3\1\220\36\3\23\0"+
    "\2\3\3\0\11\3\1\221\34\3\23\0\2\3\3\0"+
    "\10\3\1\222\35\3\23\0\2\3\3\0\22\3\1\223"+
    "\23\3\23\0\2\3\3\0\23\3\1\224\22\3\23\0"+
    "\2\3\3\0\6\3\1\225\37\3\23\0\2\3\3\0"+
    "\7\3\1\226\36\3\23\0\2\3\3\0\1\3\1\227"+
    "\44\3\23\0\2\3\3\0\22\3\1\230\23\3\23\0"+
    "\2\3\3\0\22\3\1\231\23\3\23\0\2\3\3\0"+
    "\4\3\1\232\41\3\23\0\2\3\3\0\4\3\1\233"+
    "\41\3\23\0\2\3\3\0\4\3\1\234\41\3\23\0"+
    "\2\3\3\0\12\3\1\235\33\3\23\0\2\3\3\0"+
    "\3\3\1\236\42\3\23\0\2\3\3\0\3\3\1\237"+
    "\42\3\23\0\2\3\3\0\15\3\1\240\30\3\23\0"+
    "\2\3\3\0\15\3\1\241\30\3\23\0\2\3\3\0"+
    "\7\3\1\242\36\3\23\0\2\3\3\0\3\3\1\243"+
    "\42\3\23\0\2\3\3\0\4\3\1\244\41\3\23\0"+
    "\2\3\3\0\10\3\1\245\35\3\23\0\2\3\3\0"+
    "\20\3\1\246\25\3\23\0\2\3\3\0\6\3\1\247"+
    "\37\3\23\0\2\3\3\0\11\3\1\250\34\3\23\0"+
    "\2\3\3\0\24\3\1\251\21\3\23\0\2\3\3\0"+
    "\3\3\1\252\22\3\1\253\17\3\23\0\2\3\3\0"+
    "\22\3\1\254\23\3\23\0\2\3\3\0\3\3\1\255"+
    "\42\3\23\0\2\3\3\0\7\3\1\256\36\3\23\0"+
    "\2\3\3\0\7\3\1\257\36\3\23\0\2\3\3\0"+
    "\5\3\1\260\40\3\23\0\2\3\3\0\23\3\1\261"+
    "\22\3\23\0\2\3\3\0\25\3\1\262\1\263\17\3"+
    "\23\0\2\3\3\0\23\3\1\264\22\3\23\0\2\3"+
    "\3\0\5\3\1\265\40\3\23\0\2\3\3\0\10\3"+
    "\1\266\35\3\23\0\2\3\3\0\7\3\1\267\36\3"+
    "\23\0\2\3\3\0\6\3\1\270\37\3\23\0\2\3"+
    "\3\0\23\3\1\271\22\3\23\0\2\3\3\0\26\3"+
    "\1\272\17\3\23\0\2\3\3\0\5\3\1\273\40\3"+
    "\23\0\2\3\3\0\3\3\1\274\42\3\23\0\2\3"+
    "\3\0\15\3\1\275\30\3\23\0\2\3\3\0\6\3"+
    "\1\276\37\3\23\0\2\3\3\0\23\3\1\277\22\3"+
    "\23\0\2\3\3\0\6\3\1\300\37\3\23\0\2\3"+
    "\3\0\5\3\1\301\15\3\1\302\22\3\23\0\2\3"+
    "\3\0\6\3\1\303\37\3\23\0\2\3\3\0\11\3"+
    "\1\304\34\3\23\0\2\3\3\0\23\3\1\305\22\3"+
    "\23\0\2\3\3\0\24\3\1\306\21\3\23\0\2\3"+
    "\3\0\27\3\1\307\16\3\23\0\2\3\3\0\23\3"+
    "\1\310\22\3\23\0\2\3\3\0\6\3\1\311\37\3"+
    "\23\0\2\3\3\0\5\3\1\312\40\3\23\0\2\3"+
    "\3\0\4\3\1\313\41\3\23\0\2\3\3\0\12\3"+
    "\1\314\33\3\23\0\2\3\3\0\4\3\1\315\41\3"+
    "\23\0\2\3\3\0\27\3\1\316\16\3\23\0\2\3"+
    "\3\0\6\3\1\317\37\3\23\0\2\3\3\0\13\3"+
    "\1\320\32\3\23\0\2\3\3\0\23\3\1\321\22\3"+
    "\23\0\2\3\3\0\27\3\1\322\16\3\23\0\2\3"+
    "\3\0\6\3\1\323\37\3\23\0\2\3\3\0\10\3"+
    "\1\324\35\3\23\0\2\3\3\0\7\3\1\325\36\3"+
    "\23\0\2\3\3\0\23\3\1\326\22\3\23\0\2\3"+
    "\3\0\7\3\1\327\36\3\23\0\2\3\3\0\11\3"+
    "\1\330\34\3\23\0\2\3\3\0\22\3\1\331\23\3"+
    "\23\0\2\3\3\0\23\3\1\332\22\3\23\0\2\3"+
    "\3\0\23\3\1\333\22\3\23\0\2\3\3\0\22\3"+
    "\1\334\23\3\23\0\2\3\3\0\14\3\1\335\31\3"+
    "\23\0\2\3\3\0\22\3\1\336\23\3\23\0\2\3"+
    "\3\0\11\3\1\337\34\3\23\0\2\3\3\0\11\3"+
    "\1\340\34\3\23\0\2\3\3\0\4\3\1\341\41\3"+
    "\23\0\2\3\3\0\12\3\1\342\33\3\23\0\2\3"+
    "\3\0\23\3\1\343\22\3\23\0\2\3\3\0\3\3"+
    "\1\344\42\3\23\0\2\3\3\0\7\3\1\345\36\3"+
    "\23\0\2\3\3\0\15\3\1\346\30\3\22\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[10912];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unkown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\1\0\1\11\2\1\1\11\20\1\1\11\1\1\12\11"+
    "\4\1\2\11\1\0\34\1\4\11\236\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[230];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn;

  /** 
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;

  /* user code: */
  private Parser yyparser;

  public Yylex(java.io.Reader r, Parser yyparser) {
    this(r);
    this.yyparser = yyparser;
  }


  /**
   * Creates a new scanner
   * There is also a java.io.InputStream version of this constructor.
   *
   * @param   in  the java.io.Reader to read input from.
   */
  Yylex(java.io.Reader in) {
    this.zzReader = in;
  }

  /**
   * Creates a new scanner.
   * There is also java.io.Reader version of this constructor.
   *
   * @param   in  the java.io.Inputstream to read input from.
   */
  Yylex(java.io.InputStream in) {
    this(new java.io.InputStreamReader(in));
  }

  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x10000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 164) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }


  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   * 
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

      /* translate stored positions */
      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzCurrentPos*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
    }

    /* finally: fill the buffer with new input */
    int numRead = zzReader.read(zzBuffer, zzEndRead,
                                            zzBuffer.length-zzEndRead);

    if (numRead > 0) {
      zzEndRead+= numRead;
      return false;
    }
    // unlikely but not impossible: read 0 characters, but not at end of stream    
    if (numRead == 0) {
      int c = zzReader.read();
      if (c == -1) {
        return true;
      } else {
        zzBuffer[zzEndRead++] = (char) c;
        return false;
      }     
    }

	// numRead < 0
    return true;
  }

    
  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Resets the scanner to read from a new input stream.
   * Does not close the old reader.
   *
   * All internal variables are reset, the old input stream 
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>ZZ_INITIAL</tt>.
   *
   * @param reader   the new input stream 
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEOFDone = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Contains user EOF-code, which will be executed exactly once,
   * when the end of file is reached
   */
  private void zzDoEOF() throws java.io.IOException {
    if (!zzEOFDone) {
      zzEOFDone = true;
      yyclose();
    }
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public int yylex() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = ZZ_LEXSTATE[zzLexicalState];


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL)
            zzInput = zzBufferL[zzCurrentPosL++];
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = zzBufferL[zzCurrentPosL++];
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          int zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
        case 15: 
          { return Parser.DIV;
          }
        case 60: break;
        case 33: 
          { return Parser.Size;
          }
        case 61: break;
        case 39: 
          { return Parser.Weeks;
          }
        case 62: break;
        case 10: 
          { return Parser.OPEN_PARAN;
          }
        case 63: break;
        case 37: 
          { return Parser.Start;
          }
        case 64: break;
        case 13: 
          { return Parser.MINUS;
          }
        case 65: break;
        case 12: 
          { return Parser.PLUS;
          }
        case 66: break;
        case 46: 
          { return Parser.Capacity;
          }
        case 67: break;
        case 27: 
          { return Parser.Set;
          }
        case 68: break;
        case 2: 
          { yyparser.yylval = new ParserVal(yytext()); return Parser.ID;
          }
        case 69: break;
        case 5: 
          { System.err.println("Sorry, backspace doesn't work");
          }
        case 70: break;
        case 24: 
          { return Parser.NOTEQUAL;
          }
        case 71: break;
        case 23: 
          { return Parser.ISEQUAL;
          }
        case 72: break;
        case 9: 
          { return Parser.Quote;
          }
        case 73: break;
        case 3: 
          { yyparser.yylval = new ParserVal(Double.parseDouble(yytext()));
                      return Parser.NUMBER;
          }
        case 74: break;
        case 38: 
          { return Parser.Until;
          }
        case 75: break;
        case 16: 
          { return Parser.EQUAL;
          }
        case 76: break;
        case 53: 
          { return Parser.EnergyLost;
          }
        case 77: break;
        case 11: 
          { return Parser.CLOSE_PARAN;
          }
        case 78: break;
        case 57: 
          { return Parser.ThrillLevel;
          }
        case 79: break;
        case 20: 
          { return Parser.CLOSE;
          }
        case 80: break;
        case 21: 
          { return Parser.In;
          }
        case 81: break;
        case 29: 
          { return Parser.Days;
          }
        case 82: break;
        case 42: 
          { return Parser.Number;
          }
        case 83: break;
        case 25: 
          { return Parser.LESSEQUAL;
          }
        case 84: break;
        case 19: 
          { return Parser.OPEN;
          }
        case 85: break;
        case 55: 
          { return Parser.SpendLevel;
          }
        case 86: break;
        case 48: 
          { return Parser.Location;
          }
        case 87: break;
        case 17: 
          { return Parser.LESS;
          }
        case 88: break;
        case 1: 
          { System.err.println("Error: unexpected character '" + yytext() + "'"); return -1;
          }
        case 89: break;
        case 4: 
          { /* Do nothing */
          }
        case 90: break;
        case 14: 
          { return Parser.MUL;
          }
        case 91: break;
        case 32: 
          { yyparser.noOfParks++; yyparser.setKeywordType(Parser.Park); return Parser.Park;
          }
        case 92: break;
        case 47: 
          { return Parser.Duration;
          }
        case 93: break;
        case 40: 
          { return Parser.Years;
          }
        case 94: break;
        case 56: 
          { return Parser.EnergyLevel;
          }
        case 95: break;
        case 49: 
          { return Parser.Simulate;
          }
        case 96: break;
        case 45: 
          { return Parser.Iterate;
          }
        case 97: break;
        case 50: 
          { return Parser.Admission;
          }
        case 98: break;
        case 7: 
          { return Parser.COMMA;
          }
        case 99: break;
        case 31: 
          { yyparser.noOfLands++; yyparser.setKeywordType(Parser.Land); return Parser.Land;
          }
        case 100: break;
        case 59: 
          { return Parser.SpendingCapacity;
          }
        case 101: break;
        case 51: 
          { return Parser.Employees;
          }
        case 102: break;
        case 41: 
          { return Parser.Months;
          }
        case 103: break;
        case 26: 
          { return Parser.GREATEQUAL;
          }
        case 104: break;
        case 28: 
          { return Parser.Cost;
          }
        case 105: break;
        case 8: 
          { return Parser.COLON;
          }
        case 106: break;
        case 18: 
          { return Parser.GREAT;
          }
        case 107: break;
        case 36: 
          { yyparser.noOfLands++; yyparser.setKeywordType(Parser.Store); return Parser.Store;
          }
        case 108: break;
        case 35: 
          { return Parser.Print;
          }
        case 109: break;
        case 43: 
          { return Parser.Return;
          }
        case 110: break;
        case 30: 
          { return Parser.Else;
          }
        case 111: break;
        case 54: 
          { yyparser.setKeywordType(Parser.Restaurant); return Parser.Restaurant;
          }
        case 112: break;
        case 22: 
          { return Parser.If;
          }
        case 113: break;
        case 6: 
          { return Parser.SEMICOLON;
          }
        case 114: break;
        case 44: 
          { return Parser.String;
          }
        case 115: break;
        case 58: 
          { return Parser.EnergyIncrease;
          }
        case 116: break;
        case 34: 
          { yyparser.setKeywordType(Parser.Crowd); return Parser.Crowd;
          }
        case 117: break;
        case 52: 
          { yyparser.setKeywordType(Parser.Attraction); return Parser.Attraction;
          }
        case 118: break;
        default: 
          if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
            zzAtEOF = true;
            zzDoEOF();
              { return 0; }
          } 
          else {
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}
