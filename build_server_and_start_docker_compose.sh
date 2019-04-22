#!/bin/sh

mv clean install

cd ..
docker-compose build
docker-compose up -d

cd demo/
rm -R target/
cd ..


