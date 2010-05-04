# !/usr/bin

java -cp ./thrill_parser.jar Parser code.txt

javac -cp ./thrill.jar ThrillProgram.java

jar uf thrill.jar ThrillProgram.class

echo " Welcome to THRLL "
echo

java -cp ./thrill.jar ThrillProgram

echo
