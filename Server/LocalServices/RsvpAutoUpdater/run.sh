#!/bin/sh
#
# Compile and Run the OpenJPA test program
#
cp=.
cp=$cp:lib/mysql-connector-java-5.1.37-bin.jar
cp=$cp:lib/openjpa-all-2.4.0.jar
cp=$cp:lib/slf4j-log4j12-1.7.13.jar # Logging binding to log4j
cp=$cp:lib/slf4j-api-1.7.13.jar # Logging front end
cp=$cp:lib/log4j-1.2.17.jar # Logger itself
#
echo "Compiling"
javac -cp $cp ./neu/edu/cs5200/finalproj/model/*.java
echo "Enhancing"
java -cp $cp org.apache.openjpa.enhance.PCEnhancer neu/edu/cs5200/finalproj/model/*.java
echo "Running"
java -cp $cp -javaagent:"./lib/openjpa-all-2.4.0.jar" SampleJPATest
