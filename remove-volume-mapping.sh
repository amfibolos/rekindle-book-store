#!/bin/bash

# Specify the directory path
ROOTDIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
KAFKA_DIR="$ROOTDIR/docker-compose/rekindle-local/volumes/kafka"
POSTGRE_DIR="$ROOTDIR/docker-compose/rekindle-local/volumes/postgre"
ZOOKEEPER_DIR="$ROOTDIR/docker-compose/rekindle-local/volumes/zookeeper"

# Check if the directory exists
if [ -d "$KAFKA_DIR" ]; then
    # Remove all files and directories in the specified directory
    rm -rf "$KAFKA_DIR"/*

    echo "Files and directories in $KAFKA_DIR have been removed."
else
    echo "Directory $KAFKA_DIR does not exist."
fi

if [ -d "$POSTGRE_DIR" ]; then
    # Remove all files and directories in the specified directory
    rm -rf "$POSTGRE_DIR"/*

    echo "Files and directories in $POSTGRE_DIR have been removed."
else
    echo "Directory $POSTGRE_DIR does not exist."
fi

if [ -d "$ZOOKEEPER_DIR" ]; then
    # Remove all files and directories in the specified directory
    rm -rf "$ZOOKEEPER_DIR"/*

    echo "Files and directories in $ZOOKEEPER_DIR have been removed."
else
    echo "Directory $ZOOKEEPER_DIR does not exist."
fi

echo "ALl docker volume mapped Kafka, Zookeeper and PostgreSQL files have been deleted"
exit 0