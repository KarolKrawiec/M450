#!/bin/bash

echo "Starting Frontend..."

# Navigate to frontend directory
# Note: Directory name has a typo 'fronend' which is preserved here
cd "$(dirname "$0")/recipe-planner-fronend"

# Install dependencies
npm install

# Start React app (invoke binary directly to avoid PATH issues)
./node_modules/.bin/react-scripts start
