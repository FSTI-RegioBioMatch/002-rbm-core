FROM openjdk:17-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} 004-rbm-core-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/004-rbm-core-0.0.1-SNAPSHOT.jar"]