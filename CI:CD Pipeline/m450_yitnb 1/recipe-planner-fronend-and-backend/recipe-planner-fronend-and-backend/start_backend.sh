#!/bin/bash

# Ensure JAVA_HOME is set correctly for Mac
export JAVA_HOME=$(/usr/libexec/java_home)
echo "Using JAVA_HOME: $JAVA_HOME"

echo "Starting Backend..."

# Navigate to backend directory
cd "$(dirname "$0")/recipe-planner-backend"

# Run Spring Boot application
mvn clean package -Dmaven.test.skip=true
java -jar target/recipe-planner-backend-1.0-SNAPSHOT.jar
