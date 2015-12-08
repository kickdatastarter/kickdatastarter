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
#cp=$cp:../../DataModel/
#
echo "Compiling"
#javac -cp $cp ../../DataModel/edu/neu/cs5200/finalproj/model/*.java
javac -cp $cp ./edu/neu/cs5200/finalproj/model/*.java
echo "Enhancing"
java -cp $cp org.apache.openjpa.enhance.PCEnhancer edu/neu/cs5200/finalproj/model/*.java
#java -cp $cp org.apache.openjpa.enhance.PCEnhancer ../../DataModel/edu/neu/cs5200/finalproj/model/*.java
echo "Running"
java -cp $cp -javaagent:"./lib/openjpa-all-2.4.0.jar" TrustedGuard $1 $2
