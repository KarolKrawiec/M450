@echo off
echo Building and testing project...

cd /d D:\TBZ_Module\M450\recipe-planner-fronend-and-backend\recipe-planner-fronend-and-backend\recipe-planner-backend

mvn clean test jacoco:report

echo Build and test completed. You can find the report at target/site/jacoco/index.html.
pause
