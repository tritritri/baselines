cd bin
rm -f ComputeBaseline.jar
echo Main-class: ch.epfl.lsir.wattalyst.baseline.compute.ComputeBaseline > manifest.txt
jar cvfm ComputeBaseline.jar manifest.txt ch org weka
cd ..
cd ..
rm -f ComputeBaseline-old.jar
mv ComputeBaseline.jar ComputeBaseline-old.jar
mv ComputeBaseline/bin/ComputeBaseline.jar .
