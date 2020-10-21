#!/usr/bin/env bash

#mvn -f eureka-naming-server/ clean package
#mvn -f gateway-server/ clean package
mvn -f message-ingester/ clean package
#mvn -f websock-streamer/ clean package
#mvn -f db-persister/ clean package
docker-compose up --build
