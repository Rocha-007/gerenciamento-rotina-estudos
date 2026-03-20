#!/bin/bash
# Study Routine Manager - Unix/Linux/Mac Launcher
# This script runs the Study Routine Manager application

echo "========================================"
echo "  Study Routine Manager v1.0.0"
echo "========================================"
echo

# Check if Java is installed
if ! command -v java &> /dev/null; then
    echo "ERROR: Java is not installed or not in PATH"
    echo "Please install Java 11 or higher from https://www.oracle.com/java/technologies/downloads/"
    exit 1
fi

# Check if JAR file exists, if not build it
if [ ! -f "target/study-routine-manager.jar" ]; then
    echo "JAR file not found. Building the application..."
    mvn clean package
    if [ $? -ne 0 ]; then
        echo "Failed to build application. Make sure Maven is installed."
        exit 1
    fi
fi

# Run the application
echo "Starting application..."
java -jar target/study-routine-manager.jar

if [ $? -ne 0 ]; then
    echo
    echo "Application encountered an error"
    exit 1
fi
