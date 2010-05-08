# !/usr/bin

rundir=../gui
result=.result
testList=$1
passRate=0
testFiles=`cat $testList`
count=1

# remove the results
rm -rf results*

if [ $# -eq 2 ]
then
    # if build command is issued, then call the build script
    # which will build the parser, create jars etc - initial setup
    echo "Building the thrill parser, thrill backend, creating required jars."
    $rundir/gui_build.sh
else
    echo "Build skipped. Running all the tests."
fi

mkdir results

for testFile in $testFiles
do
    echo "Running test $testFile."
    $rundir/runTest.sh $testFile > testFile$result
done

# move the results to a different folder
mv *$result results/

# create a tar.gz file
tar -czf result.tar.gz results/

