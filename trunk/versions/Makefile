# makefile to generate the THRLL parser

JFLEX  = jflex 
BYACCJ = yacc.linux -Jthrows=ThrillException -Jnorun -d -v
JAVAC  = javac

# targets:

all: Parser.class

run: Parser.class
	java Parser

build: clean Parser.class

clean: 
	del *.*~ *.class Parser.java Yylex.java ParserTokens.java ParserVal.java

Parser.class: Yylex.java ThrillUserFunction.class ThrillException.class Parser.java
	$(JAVAC) Parser.java
	
Yylex.java: thrill_lexer.flex
	$(JFLEX) thrill_lexer.flex

ThrillException.class:
	$(JAVAC) ThrillException.java

ThrillUserFunction.class:
	$(JAVAC) ThrillUserFunction.java

Parser.java: thrill_grammar.y
	$(BYACCJ) thrill_grammar.y
