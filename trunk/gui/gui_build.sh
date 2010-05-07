#!/bin/sh

rm -rf *.jar

cd ../versions

make build

jar -cf thrill_parser.jar *.class

mv thrill_parser.jar ../gui/

make clean

cd ../java_backend

make build

mv thrill.jar ../gui/

make clean

cd ../gui/

make build
