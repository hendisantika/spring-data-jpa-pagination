FROM bellsoft/liberica-openjdk-debian:21
LABEL authors="hendisantika"
LABEL maintainer="hendisantika@yahoo.co.id"
VOLUME /tmp
EXPOSE 8080
ADD target/*.jar app.jar
ENTRYPOINT ["java","-Dspring.profiles.active=docker","-jar","app.jar"]
