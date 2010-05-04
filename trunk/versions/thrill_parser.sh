# !/usr/bin

make build

jar -cf thrill_parser.jar *.class

mv thrill_parser.jar ../run/

make clean
