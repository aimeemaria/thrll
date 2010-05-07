JAVAC  = javac
JAR = jar -cf

# targets:

all: ui.class

run: ui.class
	java ui

build: clean ui.class

clean: 
	rm -rf *.*~ *.class

ui.class:
	$(JAVAC) ui.java DisplayPanel.java TextAreaDemo.java

