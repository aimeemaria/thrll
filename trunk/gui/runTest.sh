# !/usr/bin
jardir=../gui
thrill_source=ThrillProgram.java
thrill_class=ThrillProgram.class
testFile=$1

# Compile the input to generate the intermediate code - ThrillProgram.java
java -cp $jardir/thrill_parser.jar Parser $testFile

if [ -f $thrill_source ]
then
    echo 
else
    echo $result

    #clean up the files now
    rm -rf ThrillProgram.*
    exit
fi

result=`exec javac -cp $jardir/thrill.jar ThrillProgram.java`

if [ -f $thrill_class ]
then
    jar uf $jardir/thrill.jar ThrillProgram.class
    echo " Welcome to THRLL "
    echo
    java -cp $jardir/thrill.jar ThrillProgram
    echo    
else
    echo "Sorry, $thrill_class file does not exist"
    
    #clean up the files now
    rm -rf ThrillProgram.*
    exit
fi

#clean up the files now
rm -rf ThrillProgram.*
