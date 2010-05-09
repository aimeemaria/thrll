#!/bin/sh

rm -rf *.jar

cd ../versions

echo
echo "*** Building thrill parser now.. ***"
echo

make build

mv thrill_parser.jar ../gui/

make clean

cd ../java_backend

echo
echo "*** Building thrill back end now.. ***"
echo

make build

mv thrill.jar ../gui/

make clean

cd ../gui/

echo
echo "*** Building thrill back end now.. ***"
echo

make build

clear
