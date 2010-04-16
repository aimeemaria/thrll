%%

%byaccj

%{
  private Parser yyparser;

  public Yylex(java.io.Reader r, Parser yyparser) {
    this(r);
    this.yyparser = yyparser;
  }
%}

ID 	 = [a-zA-Z"_"]([a-zA-Z"_"] | [0-9])*
NUMBER = [0-9]+ ("." [0-9]+)?
NL  	 = \n | \r | \r\n

%%

/* keywords */
Admission           { return Parser.Admission;      }
Attraction          { yyparser.setKeywordType(Parser.Attraction); return Parser.Attraction;     }
Capacity            { return Parser.Capacity;       }
CalculateRevenue	  { return Parser.CalculateRevenue; }
Cost                { return Parser.Cost;           }
Crowd               { yyparser.setKeywordType(Parser.Crowd); return Parser.Crowd;          }
Days                { return Parser.Days;           }
Duration            { return Parser.Duration;       }
Else                { return Parser.Else;           }
Employees           { return Parser.Employees;      }
EnergyIncrease      { return Parser.EnergyIncrease; }
EnergyLevel         { return Parser.EnergyLevel;    }
EnergyLost          { return Parser.EnergyLost;     }
If                  { return Parser.If;             }
In                  { return Parser.In;             }
Iterate             { return Parser.Iterate;        }
Land                { yyparser.noOfLands++; yyparser.setKeywordType(Parser.Land); return Parser.Land; }
Location            { return Parser.Location;       }
Months              { return Parser.Months;         }
Number              { return Parser.Number;         }
Park                { yyparser.noOfParks++; yyparser.setKeywordType(Parser.Park); return Parser.Park; }
Print               { return Parser.Print;          }
Restaurant          { yyparser.setKeywordType(Parser.Restaurant); return Parser.Restaurant;     }
Return              { return Parser.Return;         }
Set                 { return Parser.Set;            }
Simulate            { return Parser.Simulate;       }
Size                { return Parser.Size;           }
SpendingCapacity    { return Parser.SpendingCapacity; }
SpendLevel          { return Parser.SpendLevel;     }
Start               { return Parser.Start;          }
Store               { yyparser.noOfLands++; yyparser.setKeywordType(Parser.Store); return Parser.Store; }
String              { return Parser.String;         }
ThrillLevel         { return Parser.ThrillLevel;    }
Until               { return Parser.Until;          }
Weeks               { return Parser.Weeks;          }
Years               { return Parser.Years;          }

/* newline */
{NL}                { yyparser.yyline++; }

/* Identifier */
{ID}                { yyparser.yylval = new ParserVal(yytext()); return Parser.ID; }

/* float */
{NUMBER}            { yyparser.yylval = new ParserVal(Double.parseDouble(yytext()));
                      return Parser.NUMBER;                                        }

\b                  { System.err.println("Sorry, backspace doesn't work"); }

[ \t]+              { /* Do nothing */              }

;                   { return Parser.SEMICOLON;      }

,                   { return Parser.COMMA;          }

:                   { return Parser.COLON;          }

"\""                { return Parser.Quote;          }

"{"                 { return Parser.OPEN_PARAN;     }

"}"                 { return Parser.CLOSE_PARAN;    }

"+"                 { return Parser.PLUS;           }

"-"                 { return Parser.MINUS;          }

"*"                 { return Parser.MUL;            }

"/"                 { return Parser.DIV;            }

"=="                { return Parser.ISEQUAL;        }

"="                 { return Parser.EQUAL;          }

"!="                { return Parser.NOTEQUAL;       }

"<="                { return Parser.LESSEQUAL;      }

">="                { return Parser.GREATEQUAL;     }

">"                 { return Parser.GREAT;          }

"<"                 { return Parser.LESS;           }

"("                 { return Parser.OPEN;           }

")"                 { return Parser.CLOSE;          }

/* error fallback */
[^]                 { System.err.println("Error: unexpected character '" + yytext() + "'"); return -1; }

