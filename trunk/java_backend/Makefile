JAVAC  = javac
JAR = jar -cf

ifdef _IS_WINDOWS
	DELETE = del /q /s /f
else
	DELETE = rm -rf
endif

all: backend.jar

build: clean backend.jar

clean: 
	$(DELETE) *.*~ *.class thrill.jar

backend.jar: Attraction.class Days.class Months.class NormalDistribution.class ThrillException.class Weeks.class Years.class
	$(JAR) thrill.jar *.class
	
Attraction.class:
	$(JAVAC) Attraction.java

Crowd.class:
	$(JAVAC) Crowd.java

Days.class:
	$(JAVAC) Days.java

Duration.class:
	$(JAVAC) Duration.java
     
Land.class:
	$(JAVAC) Land.java

LandElement.class:
	$(JAVAC) LandElement.java

Months.class:
	$(JAVAC) Months.java

NormalDistribution.class:
	$(JAVAC) NormalDistribution.java
    
Park.class: 
	$(JAVAC) Park.java

Person.class: 
	$(JAVAC) Person.java

Restaurant.class: 
	$(JAVAC) Restaurant.java

Store.class: 
	$(JAVAC) Store.java

ThrillException.class: 
	$(JAVAC) ThrillException.java

Weeks.class: 
	$(JAVAC) Weeks.java

Years.class: 
	$(JAVAC) Years.java
