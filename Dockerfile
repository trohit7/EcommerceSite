FROM openjdk:8-jre-alpine
MAINTAINER Rohit
ADD ./target/btechproject-0.0.1-SNAPSHOT.jar btechproject-0.0.1-SNAPSHOT.jar
RUN mkdir -p /var/log/spring
CMD ["java", "-jar", "btechproject-0.0.1-SNAPSHOT.jar"]
