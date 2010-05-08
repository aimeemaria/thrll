# !/usr/bin

echo
echo "              THRLL Regression Tests         "

if [ $# -lt 1 ]
then
    echo
    echo "*** Missing tests file ***"
    echo "Usage: runalltests.sh tests_file"
    echo "Usage: runalltests.sh tests_file <build>"
    echo
    exit
fi

# initialize all the variables
rundir=../gui
result=.result
testList=$1
passRate=0
testFiles=`cat $testList`

if [ $# -eq 2 ]
then
    # if build command is issued, then call the build script
    # which will build the parser, create jars etc - initial setup
    echo "Building the thrill parser, thrill backend, creating required jars."
    $rundir/gui_build.sh
else
    echo "Build skipped."
fi

# remove the prev results
rm -rf results*

mkdir results

echo "Starting all tests."

for testFile in $testFiles
do
    echo "Running test $testFile."
    $rundir/runTest.sh $testFile > testFile$result
done

# move the results to a different folder
mv *$result results/

# create a tar.gz file
tar -czf result.tar.gz results/
