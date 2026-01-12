#!/bin/bash

echo "Starting Frontend..."

# Navigate to frontend directory
# Note: Directory name has a typo 'fronend' which is preserved here
cd "$(dirname "$0")/recipe-planner-fronend"

# Start React app
npm start
