FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} note.jar
EXPOSE 9090
ENTRYPOINT ["java","-jar","/note.jar"]