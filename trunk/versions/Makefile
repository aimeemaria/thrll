# makefile to generate the THRLL parser

JFLEX  = jflex 
BYACCJ = yacc.linux -Jthrows=ThrillException -Jnorun -d -v
JAVAC  = javac

# targets:

all: thrill_parser.jar

run: thrill_parser.jar
	java Parser

build: clean Parser.class

clean: 
	rm -rf *.*~ *.class Parser.java Yylex.java ParserTokens.java ParserVal.java

thrill_parser.jar: Parser.class
	jar -cf thrill_parser.jar *.class

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
