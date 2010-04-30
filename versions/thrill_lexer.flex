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
Admission           { yyparser.yycolumn += yytext().length(); return Parser.Admission;      }
Attraction          { yyparser.yycolumn += yytext().length(); return Parser.Attraction;     }
Capacity            { yyparser.yycolumn += yytext().length(); return Parser.Capacity;       }
CalculateRevenue	{ yyparser.yycolumn += yytext().length(); return Parser.CalculateRevenue; }
Cost                { yyparser.yycolumn += yytext().length(); return Parser.Cost;           }
Crowd               { yyparser.yycolumn += yytext().length(); return Parser.Crowd;          }
Days                { yyparser.yycolumn += yytext().length(); return Parser.Days;           }
Duration            { yyparser.yycolumn += yytext().length(); return Parser.Duration;       }
Else                { yyparser.yycolumn += yytext().length(); return Parser.Else;           }
Employees           { yyparser.yycolumn += yytext().length(); return Parser.Employees;      }
EnergyIncrease      { yyparser.yycolumn += yytext().length(); return Parser.EnergyIncrease; }
EnergyLevel         { yyparser.yycolumn += yytext().length(); return Parser.EnergyLevel;    }
EnergyLost          { yyparser.yycolumn += yytext().length(); return Parser.EnergyLost;     }
If                  { yyparser.yycolumn += yytext().length(); return Parser.If;             }
In                  { yyparser.yycolumn += yytext().length(); return Parser.In;             }
Iterate             { yyparser.yycolumn += yytext().length(); return Parser.Iterate;        }
Land                { yyparser.yycolumn += yytext().length(); yyparser.noOfLands++; return Parser.Land; }
Location            { yyparser.yycolumn += yytext().length(); return Parser.Location;       }
Months              { yyparser.yycolumn += yytext().length(); return Parser.Months;         }
Number              { yyparser.yycolumn += yytext().length(); return Parser.Number;         }
Park                { yyparser.noOfParks++; return Parser.Park; }
Print               { yyparser.yycolumn += yytext().length(); return Parser.Print;          }
Restaurant          { yyparser.yycolumn += yytext().length(); return Parser.Restaurant;     }
Return              { yyparser.yycolumn += yytext().length(); return Parser.Return;         }
Set                 { yyparser.yycolumn += yytext().length(); return Parser.Set;            }
Simulate            { yyparser.yycolumn += yytext().length(); return Parser.Simulate;       }
Size                { yyparser.yycolumn += yytext().length(); return Parser.Size;           }
SpendingCapacity    { yyparser.yycolumn += yytext().length(); return Parser.SpendingCapacity; }
SpendLevel          { yyparser.yycolumn += yytext().length(); return Parser.SpendLevel;     }
Start               { yyparser.yycolumn += yytext().length(); return Parser.Start;          }
Store               { yyparser.yycolumn += yytext().length(); return Parser.Store; }
String              { yyparser.yycolumn += yytext().length(); return Parser.String;         }
ThrillLevel         { yyparser.yycolumn += yytext().length(); return Parser.ThrillLevel;    }
Until               { yyparser.yycolumn += yytext().length(); return Parser.Until;          }
Weeks               { yyparser.yycolumn += yytext().length(); return Parser.Weeks;          }
Years               { yyparser.yycolumn += yytext().length(); return Parser.Years;          }

/* newline */
{NL}                { 
                        yyparser.yycolumn = 0; 
                        yyparser.yyline++; 
                    }

/* Identifier */
{ID}                { 
                        yyparser.yycolumn += yytext().length();                
                        yyparser.yylval = new ParserVal(yytext()); 
                        return Parser.ID; 
                    }

/* float */
{NUMBER}            {
                        yyparser.yycolumn += yytext().length();
			            yyparser.yylval = new ParserVal(Double.parseDouble(yytext()));
                        return Parser.NUMBER;                                        
			        }

\b                  { System.err.println("Sorry, backspace doesn't work"); }

[ \t]+              { yyparser.yycolumn += yytext().length();            }

[;]+                { 
                        yyparser.yycolumn += yytext().length(); 
                        return Parser.SEMICOLON;      
                    }

,                   { yyparser.yycolumn++; return Parser.COMMA;          }

:                   { yyparser.yycolumn++; return Parser.COLON;          }

"\""                { yyparser.yycolumn++; return Parser.Quote;          }

"{"                 { yyparser.yycolumn++; return Parser.OPEN_PARAN;     }

"}"                 { yyparser.yycolumn++; return Parser.CLOSE_PARAN;    }

"+"                 { yyparser.yycolumn++; return Parser.PLUS;           }

"-"                 { yyparser.yycolumn++; return Parser.MINUS;          }

"*"                 { yyparser.yycolumn++; return Parser.MUL;            }

"/"                 { yyparser.yycolumn++; return Parser.DIV;            }

"=="                { yyparser.yycolumn++; return Parser.ISEQUAL;        }

"="                 { yyparser.yycolumn++; return Parser.EQUAL;          }

"!="                { yyparser.yycolumn++; return Parser.NOTEQUAL;       }

"<="                { yyparser.yycolumn++; return Parser.LESSEQUAL;      }

">="                { yyparser.yycolumn++; return Parser.GREATEQUAL;     }

">"                 { yyparser.yycolumn++; return Parser.GREAT;          }

"<"                 { yyparser.yycolumn++;return Parser.LESS;            }

"("                 { yyparser.yycolumn++;return Parser.OPEN;            }

")"                 { yyparser.yycolumn++;return Parser.CLOSE;           }

/* error fallback */
[^]                 { 
                        System.err.println("Error: unexpected character '" + yytext() + "'"); 
                        return -1; 
                    }
