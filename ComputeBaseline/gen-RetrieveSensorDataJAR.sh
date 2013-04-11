#!/bin/bash

# go to bin directory
cd bin

# create jar for ComputeError
rm -f RetrieveSensorData.jar
echo Main-class: ch.epfl.lsir.wattalyst.webserver.RetrieveSensorData > manifest.txt
jar cvfm RetrieveSensorData.jar manifest.txt ch/epfl/lsir/wattalyst/webserver org/apache org/wattalyst

# go to main project directory
cd ..

# bring the new jars
mv bin/RetrieveSensorData.jar ..
