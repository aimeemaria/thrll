%%

%byaccj

%{
  private Parser yyparser;

  public Yylex(java.io.Reader r, Parser yyparser) {
    this(r);
    this.yyparser = yyparser;
  }
%}

ID = ([a-zA-Z])+
NUMBER = [0-9]+ ("." [0-9]+)?
NL  = \n | \r | \r\n

%%

/* keywords */
Admission           { System.out.println("Found Admission"); return Parser.Admission;       }
Attraction          { System.out.println("Found Attraction"); return Parser.Attraction;     }
Capacity            { return Parser.Capacity;       }
Cost                { System.out.println("Found Cost"); return Parser.Cost;                 }
Create              { System.out.println("Found Create"); return Parser.Create;             }
Crowd               { return Parser.Crowd;          }
Days                { return Parser.Days;           }
Duration            { return Parser.Duration;       }
Else                { return Parser.Else;           }
Employees           { return Parser.Employees;      }
EnergyIncrease      { return Parser.EnergyIncrease; }
EnergyLevel         { return Parser.EnergyLevel;    }
EnergyLost          { return Parser.EnergyLost;     }
If                  { System.out.println("Found If"); return Parser.If;                    }
In                  { System.out.println("Found In"); return Parser.In;                    }
Iterate             { System.out.println("Found Iterate"); return Parser.Iterate;          }
Land                { System.out.println("Found Land"); return Parser.Land;           }
Location            { return Parser.Location;       }
Months              { return Parser.Months;         }
Number              { System.out.println("Found Number"); return Parser.Number;            }
Park                { return Parser.Park;           }
Print               { return Parser.Print;          }
Restaurant          { System.out.println("Found Restaurant"); return Parser.Restaurant;    }
Set                 { System.out.println("Found Set"); return Parser.Set;                  }
Simulate            { System.out.println("Found Simulate"); return Parser.Simulate;        }
SpendingCapacity    { System.out.println("Found SpendingCapacity"); return Parser.SpendingCapacity; }
SpendLevel          { System.out.println("Found SpendLevel"); return Parser.SpendLevel;    }
Start               { System.out.println("Found Start"); return Parser.Start;              }
Store               { System.out.println("Found Store"); return Parser.Store;              }
String              { System.out.println("Found String"); return Parser.String;            }
ThrillLevel         { System.out.println("Found ThrillLevel"); return Parser.ThrillLevel;    }
Until               { System.out.println("Found Until"); return Parser.Until;          }
Weeks               { System.out.println("Found Weeks"); return Parser.Weeks;          }
Years               { System.out.println("Found Years"); return Parser.Years;          }

/* newline */
{NL}                { /* Do nothing */ }

/* Identifier */
{ID}                { System.out.println("Found ID"); return Parser.ID;             }

/* float */
{NUMBER}            { System.out.println("Found a number");
                      yyparser.yylval = new ParserVal(Double.parseDouble(yytext()));
                      return Parser.NUMBER;                                        }

\b                  { System.err.println("Sorry, backspace doesn't work"); }

[ \t]+              { /* Do nothing */              }

;                   { System.out.println("Found ;"); return Parser.SEMICOLON;      }

,                   { System.out.println("Found ,"); return Parser.COMMA;          }

:                   { System.out.println("Found :"); return Parser.COLON;          }

"\""                { System.out.println("Found Quote"); return Parser.Quote;      }

"{"                 { System.out.println("Found {"); return Parser.OPEN_PARAN;     }

"}"                 { System.out.println("Found }"); return Parser.CLOSE_PARAN;    }

"+"                 { System.out.println("Found +"); return Parser.PLUS;           }

"-"                 { System.out.println("Found -"); return Parser.MINUS;          }

"*"                 { System.out.println("Found *"); return Parser.MUL;            }

"/"                 { System.out.println("Found /"); return Parser.DIV;            }

"=="                { System.out.println("Found =="); return Parser.ISEQUAL;       }

"="                 { System.out.println("Found ="); return Parser.EQUAL;          }

"!="                { System.out.println("Found !="); return Parser.NOTEQUAL;      }

"<="                { System.out.println("Found <="); return Parser.LESSEQUAL;     }

">="                { System.out.println("Found >="); return Parser.GREATEQUAL;    }

">"                 { System.out.println("Found >"); return Parser.GREAT;          }

"<"                 { System.out.println("Found <"); return Parser.LESS;           }

"("                 { return Parser.OPEN;           }

")"                 { return Parser.CLOSE;          }

/* error fallback */
[^]                 { System.err.println("Error: unexpected character '" + yytext() + "'"); return -1; }

