#!/bin/bash

offset=-1d
startdate=`date -v $offset +"20%y-%m-%d--00:00"`
enddate=`date -v $offset +"20%y-%m-%d--23:59"`

for sensor in wattalyst.lulea.location_43.sensor_344 wattalyst.lulea.location_43.sensor_346 wattalyst.lulea.location_43.sensor_348 wattalyst.lulea.location_43.sensor_590 wattalyst.lulea.location_46.sensor_545 wattalyst.lulea.location_46.sensor_560 wattalyst.lulea.location_46.sensor_562 wattalyst.lulea.location_46.sensor_514 wattalyst.lulea.location_76.sensor_820 wattalyst.lulea.location_76.sensor_822 wattalyst.lulea.location_76.sensor_824 wattalyst.lulea.location_76.sensor_826 wattalyst.lulea.location_160.sensor_1992 wattalyst.lulea.location_160.sensor_1994 wattalyst.lulea.location_160.sensor_1996 wattalyst.lulea.location_160.sensor_1998 wattalyst.lulea.location_156.sensor_1942 wattalyst.lulea.location_156.sensor_1944 wattalyst.lulea.location_156.sensor_1946 wattalyst.lulea.location_156.sensor_1948 wattalyst.lulea.location_75.sensor_806 wattalyst.lulea.location_75.sensor_808 wattalyst.lulea.location_75.sensor_810 wattalyst.lulea.location_75.sensor_812 wattalyst.lulea.location_120.sensor_1460 wattalyst.lulea.location_120.sensor_1462 wattalyst.lulea.location_120.sensor_1464 wattalyst.lulea.location_120.sensor_1466 wattalyst.lulea.location_159.sensor_1978 wattalyst.lulea.location_159.sensor_1980 wattalyst.lulea.location_159.sensor_1982 wattalyst.lulea.location_159.sensor_1984 wattalyst.lulea.location_115.sensor_1390 wattalyst.lulea.location_115.sensor_1392 wattalyst.lulea.location_115.sensor_1394 wattalyst.lulea.location_115.sensor_1396 wattalyst.lulea.location_123.sensor_1502 wattalyst.lulea.location_123.sensor_1504 wattalyst.lulea.location_123.sensor_1506 wattalyst.lulea.location_123.sensor_1508
do

    java -jar RetrieveEnergyData.jar -o ${sensor}.kpi.txt ${sensor} ${startdate} ${enddate}
    
done


