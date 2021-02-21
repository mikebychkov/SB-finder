#!/bin/sh
echo "********************************************************"
echo "STARTING FINDER SERVICE"
echo "********************************************************"

dockerize -wait $REDIS_SERVER

java -Dspring.profiles.active=$PROFILE \
	-jar /usr/local/app/@project.build.finalName@.jar

     #-Djava.security.egd=file:/dev/./urandom -Dserver.port=$SERVER_PORT \
     #-Deureka.client.serviceUrl.defaultZone=$EUREKASERVER_URI   \
     #-Dspring.cloud.config.uri=$CONFIGSERVER_URI                \
