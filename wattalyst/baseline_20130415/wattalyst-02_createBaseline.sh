#!/bin/bash  
program=../../ComputeBaseline.jar
startDate=2013-04-15
horizonLen=1
excludeFile=exclude.txt

for baseline in "PJMEco" "CAISO" "NYISO" "Mid4Of6" "ISONE"
do 
	for sensor in "344" "346" "348" "590" "1083" 
	do 
		java -jar $program -e $excludeFile -o $baseline-$sensor.txt -z $horizonLen $baseline $sensor.txt $startDate
	done
done


