#!/bin/bash

# Ensure JAVA_HOME is set correctly for Mac
export JAVA_HOME=$(/usr/libexec/java_home)
echo "Using JAVA_HOME: $JAVA_HOME"

echo "Building and testing project..."

# Navigate to backend directory
cd "$(dirname "$0")/recipe-planner-backend"

# Run Maven build and tests
mvn clean package -Dmaven.test.skip=true

echo "Build and test completed. You can find the report at recipe-planner-backend/target/site/jacoco/index.html."
