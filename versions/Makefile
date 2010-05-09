# makefile to generate the THRLL parser

JFLEX  = jflex 
JAR = jar -cf
OPTIONS = -Jthrows=ThrillException -Jnorun -d -v
JAVAC  = javac

ifdef _IS_WINDOWS
	DELETE = del /q /s /r
	BYACCJ = yacc
else
	DELETE = rm -rf
	BYACCJ = yacc.linux
endif
     
# targets:

all: thrill_parser.jar

run: thrill_parser.jar
	java Parser

build: clean thrill_parser.jar 

clean: 
	$(DELETE) *.*~ *.class Parser.java Yylex.java ParserTokens.java ParserVal.java

thrill_parser.jar: Parser.class
	$(JAR) thrill_parser.jar *.class

Parser.class: Yylex.java ThrillUserFunction.class ThrillException.class Parser.java
	$(JAVAC) Parser.java
	
Yylex.java: thrill_lexer.flex
	$(JFLEX) thrill_lexer.flex

ThrillException.class:
	$(JAVAC) ThrillException.java

ThrillUserFunction.class:
	$(JAVAC) ThrillUserFunction.java

Parser.java: thrill_grammar.y
	$(BYACCJ) $(OPTIONS) thrill_grammar.y
