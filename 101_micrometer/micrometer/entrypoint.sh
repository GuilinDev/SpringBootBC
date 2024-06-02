##!/bin/bash
#
## Start the Spring Boot application in the background
#java -jar /app.jar &
#
## Wait for the Spring Boot application to start
#echo "Waiting for Spring Boot application to start..."
#sleep 10
#
## Generate metrics data
#echo "Generating metrics data..."
#curl -s http://localhost:8089/increment
#curl -s http://localhost:8089/incrementMultiple
#curl -s http://localhost:8089/recordSummary?amount=5
#curl -s http://localhost:8089/recordSummary?amount=3
#curl -s http://localhost:8089/recordMultipleSummary
#curl -s http://localhost:8089/setGauge?value=10
#curl -s http://localhost:8089/setGaugeSample
#curl -s http://localhost:8089/time
#curl -s http://localhost:8089/timeMultiple
#
## Keep the container running
#wait
