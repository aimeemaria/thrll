# !/usr/bin

cd ../java_backend

javac *.java

sudo mkdir backend_classes

sudo chmod -R 777 backend_classes

sudo mv *.class backend_classes/

cd backend_classes

jar -cf thrill.jar *.class

cp thrill.jar ../../run

cd ../../versions

sudo rm -rf ../java_backend/backend_classes

