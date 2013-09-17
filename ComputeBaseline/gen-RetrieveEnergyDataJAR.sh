#!/bin/bash

# go to bin directory
cd bin

# create jar for RetrieveEnergyData
rm -f RetrieveEnergyData.jar
echo Main-class: ch.epfl.lsir.wattalyst.webserver.RetrieveEnergyData > manifest.txt
jar cvfm RetrieveEnergyData.jar manifest.txt ch/epfl/lsir/wattalyst org/apache org/wattalyst

# go to main project directory
cd ..

# bring the new jars
mv bin/RetrieveEnergyData.jar ..
