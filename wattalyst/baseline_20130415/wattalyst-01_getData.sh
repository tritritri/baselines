#!/bin/bash  

# get data
program=../../RetrieveEnergyData.jar
startDate=2012-12-01--00:00
endDate=2013-04-11--23:59

for sensor in "344" "346" "348" "590" "1083" 
do 
	#java -jar $program -e $excludeFile -o $resultDir/$baseline-$sensor.txt -z $horizonLen $baseline $dataDir/$sensor.txt $startDate
	java -jar $program -o $sensor.txt wattalyst.lulea.location_43.sensor_$sensor $startDate $endDate
	#java -jar RetrieveEnergyData.jar -o e344.txt wattalyst.lulea.location_43.sensor_344 2012-12-01--00:00 2013-04-11--23:59
done


