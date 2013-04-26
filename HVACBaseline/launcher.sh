#!/bin/bash

java -jar DataBuilder.jar -r R1A -m 40:41:42 hvac.csv | java -jar TestBaseline.jar 

