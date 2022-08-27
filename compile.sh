#!/bin/sh

mkdir -p bin
find . -name "*.java" > sources.txt
javac @sources.txt -d bin
rm -f sources.txt
java -classpath bin Main scenario.txt