#!/bin/bash  

program=../../ComputeBaseline.jar
startDate=2013-04-15
horizonLen=1
excludeFile=exclude.txt
baseline=Supervised
fileConfig=config-reg.txt
fileTemperature=data/temp_lulea_2012-12-01--2013-04-15.txt
fileAlgorithm=algLinearRegression.txt

for sensor in "344" "346" "348" "590" "1083" 
do 
	# files
	echo energy-file = data/$sensor.txt > $fileConfig
	echo temperature-file = $fileTemperature >> $fileConfig
	echo algorithm-file = $fileAlgorithm >> $fileConfig

	# features parameters
	echo history-weekday = 50 >> $fileConfig
	echo history-weekend = 25 >> $fileConfig
	echo lag-weekday = 7 >> $fileConfig
	echo lag-weekend = 5 >> $fileConfig
	echo min-value-allowed = 0 >> $fileConfig

	# run
	java -jar $program -e $excludeFile -o Regression-$sensor.txt -z $horizonLen $baseline $fileConfig $startDate
done


