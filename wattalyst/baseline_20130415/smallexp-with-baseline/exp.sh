

for baseline in "PJMEco" "CAISO" "NYISO" "Mid4Of6" "ISONE"
do 
	java -jar ../../ComputeBaseline.jar -o baseline-$baseline.txt $baseline data/344.txt 2013-04-10
done

for baseline in "PJMEco" "CAISO" "NYISO" "Mid4Of6" "ISONE"
do 
	java -jar ../../ComputeError.jar -o err-$baseline.csv $baseline.txt data/344.txt
done

