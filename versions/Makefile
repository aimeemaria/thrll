# only works with the Java extension of yacc: 
# byacc/j from http://troi.lincom-asg.com/~rjamison/byacc/

JFLEX  = jflex 
BYACCJ = yacc -J -d -v
JAVAC  = javac

# targets:

all: Parser.class

run: Parser.class
	java Parser

build: clean Parser.class

clean: 
	del *.*~ Parser.class Yylex.class ParserTokens.class ParserVal.class Parser.java Yylex.java ParserTokens.java ParserVal.java

Parser.class: Yylex.java Park.java Land.java Store.java ThrillException.java Parser.java
	$(JAVAC) Parser.java
	
Yylex.java: thrill_lexer.flex
	$(JFLEX) thrill_lexer.flex

Park.class: Park.java
	$(JAVAC) Park.java

Land.class: Land.java
	$(JAVAC) Land.java

Store.class: Store.java
	$(JAVAC) Store.java

ThrillException.class: ThrillException.java
	$(JAVAC) ThrillException.java

Parser.java: thrill_grammar.y
	$(BYACCJ) thrill_grammar.y