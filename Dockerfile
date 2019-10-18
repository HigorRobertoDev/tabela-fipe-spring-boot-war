FROM java:8
EXPOSE 8081
ADD /target/api-0.0.1-SNAPSHOT.jar api-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["jar","-jar","api-0.0.1-SNAPSHOT.jar"]