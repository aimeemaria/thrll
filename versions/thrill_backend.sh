# !/usr/bin

cd ../java_backend

javac *.java

mkdir backend_classes

chmod -R 777 backend_classes

mv *.class backend_classes/

cd backend_classes

jar -cf thrill.jar *.class

cp thrill.jar ../../run

cd ../../versions

rm -rf ../java_backend/backend_classes
