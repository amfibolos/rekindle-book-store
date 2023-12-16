#!/bin/bash

# get the current directory
rootDir=$(pwd)

# Define the directory from rootDir
kafkaDir="$rootDir/volumes/kafka/"

# Delete all files in directory
rm -f $dir/*