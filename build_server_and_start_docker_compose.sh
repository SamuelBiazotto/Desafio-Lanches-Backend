#!/bin/sh

mvn clean install

docker-compose build
docker-compose up -d

#cd demo/

rm -R target/
#cd ..


