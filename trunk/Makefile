# only works with the Java extension of yacc: 
# byacc/j from http://troi.lincom-asg.com/~rjamison/byacc/

JFLEX  = jflex 
BYACCJ = yacc.linux -J
JAVAC  = javac

# targets:

all: Parser.class

run: Parser.class
	java Parser

build: clean Parser.class

clean:
	rm -rf *~ *.class *.java

Parser.class: Yylex.java Parser.java
	$(JAVAC) Parser.java


#Yylex.java: calc.flex
#	$(JFLEX) calc.flex

#Parser.java: calc.y
#	$(BYACCJ) calc.y
	
Yylex.java: thrill_lexer.flex
	$(JFLEX) thrill_lexer.flex

Parser.java: thrill_grammar.y
	$(BYACCJ) thrill_grammar.y
