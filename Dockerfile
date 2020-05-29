FROM openjdk:8-jdk-alpine
VOLUME tmp/
ADD target/casapopular-0.0.1-SNAPSHOT.jar casapopular.jar
ENTRYPOINT ["java", "-jar", "casapopular.jar"]