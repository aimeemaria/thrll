# !/usr/bin
flag="true"
thrill_source=ThrillProgram.java
thrill_class=ThrillProgram.class

#clean up position and input  files
#rm -rf position.txt
#rm -rf code.txt
echo "Executing generate script"

# Compile the input to generate the intermediate code - ThrillProgram.java
java -cp ./thrill_parser.jar Parser code.txt $flag

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
    #rm -rf ThrillProgram.*
    exit
fi

#clean up the files now
#rm -rf ThrillProgram.*


    

