FROM java:8
WORKDIR /app
COPY ./target/demo-0.0.1-SNAPSHOT.jar /app/spring-boot-app.jar
ENTRYPOINT ["java","-jar","spring-boot-app.jar"]
