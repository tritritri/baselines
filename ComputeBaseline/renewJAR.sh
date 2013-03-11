#!/bin/bash

# go to bin directory
cd bin

# create jar for ComputeBaseline
rm -f ComputeBaseline.jar
echo Main-class: ch.epfl.lsir.wattalyst.baseline.compute.ComputeBaseline > manifest.txt
jar cvfm ComputeBaseline.jar manifest.txt ch org weka

# create jar for ComputeError
rm -f ComputeError.jar
echo Main-class: ch.epfl.lsir.wattalyst.baseline.compute.ComputeError > manifest.txt
jar cvfm ComputeError.jar manifest.txt ch org

# go to main project directory
cd ..

# rename the old jars
mv -f ../ComputeBaseline.jar ../ComputeBaseline-old.jar
mv -f ../ComputeError.jar ../ComputeError-old.jar
# bring the new jars
mv bin/ComputeBaseline.jar ..
mv bin/ComputeError.jar ..
