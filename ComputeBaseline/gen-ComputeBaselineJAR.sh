#!/bin/bash

# note
# 
# for org/apache, extract lib/commons-cli-1.2.jar
# for weka, extract lib.jar
# put them in bin directory

# go to bin directory
cd bin

# create jar for ComputeBaseline
rm -f ComputeBaseline.jar
echo Main-class: ch.epfl.lsir.wattalyst.baseline.compute.ComputeBaseline > manifest.txt
jar cvfm ComputeBaseline.jar manifest.txt ch/epfl/lsir/wattalyst/baseline org/apache weka/classifiers weka/clusterers weka/core weka/filters

# go to main project directory
cd ..

# bring the new jars
mv bin/ComputeBaseline.jar ..

