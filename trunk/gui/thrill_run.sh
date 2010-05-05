# !/usr/bin

thrill_source=ThrillProgram.java
thrill_class=ThrillProgram.class

# Compile the input to generate the intermediate code - ThrillProgram.java
java -cp ./thrill_parser.jar Parser code.txt

if [ -f $thrill_source ]
then
    echo 
else
    echo $result

    #clean up the files now
    rm -rf ThrillProgram.*
    exit
fi

result=`exec javac -cp ./thrill.jar ThrillProgram.java`

if [ -f $thrill_class ]
then
    jar uf thrill.jar ThrillProgram.class
    echo " Welcome to THRLL "
    echo
    java -cp ./thrill.jar ThrillProgram
    echo    
else
    echo "Sorry, $thrill_class file does not exist"
    
    #clean up the files now
    rm -rf ThrillProgram.*
    exit
fi

#clean up the files now
rm -rf ThrillProgram.*


    

