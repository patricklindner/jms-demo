FROM openjdk:17-jdk-alpine

COPY pom.xml /src/
COPY jms-consumer /src/jms-consumer
COPY jms-producer /src/jms-producer

WORKDIR /src/

RUN apk update && apk add maven

RUN mvn clean install

# ENTRYPOINT ["java","-jar","/opt/jms-consumer/target/"]