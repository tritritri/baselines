#!/bin/bash

# go to bin directory
cd bin

# create jar for ComputeError
rm -f ComputeError.jar
echo Main-class: ch.epfl.lsir.wattalyst.baseline.compute.ComputeError > manifest.txt
jar cvfm ComputeError.jar manifest.txt ch/epfl/lsir/wattalyst/baseline org/apache

# go to main project directory
cd ..

# bring the new jars
mv bin/ComputeError.jar ..
