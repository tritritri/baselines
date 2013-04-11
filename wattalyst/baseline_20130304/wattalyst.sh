#!/bin/bash  
program=../ComputeBaseline.jar
dataDir=data
startDate=2013-03-04
horizonLen=7
resultDir=result\_$startDate\_z$horizonLen
excludeFile=exclude.txt

rm -rf $resultDir 
mkdir $resultDir

for baseline in "PJMEco" "CAISO" "NYISO" "Mid4Of6" 
do 
	for sensor in "344" "346" "348" "590" "1083" 
	do 
		java -jar $program -e $excludeFile -o $resultDir/$baseline-$sensor.txt -z $horizonLen $baseline $dataDir/$sensor.txt $startDate
	done
done


# now for regression
baseline=Regression
fileConfig=config-reg.txt
for sensor in "344" "346" "348" "590" "1083" 
do 
	# files
	echo energy-file = data/$sensor.txt > $fileConfig
	echo temperature-file = data/temp_lulea_hourly_fixed.txt >> $fileConfig
	echo algorithm-file = data/algLinearRegression.txt >> $fileConfig
	# features parameters
	echo history-weekday = 20 >> $fileConfig
	echo history-weekend = 10 >> $fileConfig
	echo lag-weekday = 5 >> $fileConfig
	echo lag-weekend = 2 >> $fileConfig
	echo min-value-allowed = 0 >> $fileConfig

	# run
	java -jar $program -e $excludeFile -o $resultDir/$baseline-$sensor.txt -z $horizonLen $baseline $fileConfig $startDate
done

