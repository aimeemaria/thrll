# !/usr/bin

make build

jar -cf thrill_parser.jar *.class

mv thrill_parser.jar ../gui/

make clean
