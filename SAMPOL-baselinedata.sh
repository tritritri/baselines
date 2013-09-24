#!/bin/bash

targetdate=`date -v +2d +"20%y-%m-%d"`
#targetdate=`date -v +"20%y-%m-%d"`

for sensor in wattalyst.mallorca.sampol.Building.sensor_117 wattalyst.mallorca.sampol.Building.sensor_111 wattalyst.mallorca.sampol.Building.sensor_99 wattalyst.mallorca.sampol.Building.sensor_105 wattalyst.mallorca.sampol.TechnologicalIntegration.sensor_19 wattalyst.mallorca.sampol.TechnologicalIntegration.sensor_17 wattalyst.mallorca.sampol.TechnologicalIntegration.sensor_1069 wattalyst.mallorca.sampol.AdministrativeAccounting.sensor_1078 wattalyst.mallorca.sampol.AdministrativeAccounting.sensor_26 wattalyst.mallorca.sampol.AdministrativeAccounting.sensor_1030 wattalyst.mallorca.sampol.ITDepartment.sensor_1097 wattalyst.mallorca.sampol.ITDepartment.sensor_1088 wattalyst.mallorca.sampol.ITDepartment.sensor_1095 wattalyst.mallorca.sampol.InstallationsDepartment.sensor_56 wattalyst.mallorca.sampol.InstallationsDepartment.sensor_1103 wattalyst.mallorca.sampol.InstallationsDepartment.sensor_50 wattalyst.mallorca.sampol.EnergyDepartment.sensor_68 wattalyst.mallorca.sampol.EnergyDepartment.sensor_62 wattalyst.mallorca.sampol.EnergyDepartment.sensor_1110 wattalyst.mallorca.sampol.Storehouse.sensor_74 wattalyst.mallorca.sampol.Storehouse.sensor_1116 wattalyst.mallorca.sampol.Storehouse.sensor_1119
do

    java -jar ComputeBaseline.jar -o ${sensor}.baseline.txt CAISO ${sensor}.txt ${targetdate}

done


