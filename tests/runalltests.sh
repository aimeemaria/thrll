# !/usr/bin

rundir=../gui
testList=$1
passRate=0
testFiles=`cat $testList`
count=1
# build the parser, create jars etc - initial setup
# $rundir/gui_build.sh
mkdir results

for testFile in $testFiles
do
    echo "Running test $testFile"
    $rundir/runTest.sh $testFile > resultFile_$count
    mv resultFile_$count results/
    count=`expr $count + 1`
done


