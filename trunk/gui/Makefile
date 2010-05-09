JAVAC  = javac
JAR = jar -cf

ifdef _IS_WINDOWS
	DELETE = del /q /s /f
else
	DELETE=rm -rf
endif

# targets:

all: ui.class

run: ui.class
	java ui

build: clean ui.class

clean: 
	$(DELETE) *.*~ *.class

ui.class:
	$(JAVAC) ui.java DisplayPanel.java TextAreaDemo.java

