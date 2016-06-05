#!/bin/sh -e
sbt compile
cd target/scala-2.11/classes
jar -cvf sparkExample.jar SparkExample*.class
mv sparkExample.jar ../../..
cd ../../..
sbt submit

