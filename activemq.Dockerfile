# Using jdk as base image
FROM openjdk:8-jdk-alpine

# Copy the whole directory of activemq into the image
COPY activemq/apache-activemq-5.16.5-bin.tar.gz /opt/apache-activemq-5.16.5-bin.tar.gz
RUN cd /opt && tar -xf apache-activemq-5.16.5-bin.tar.gz

# Set the working directory to the bin folder 
WORKDIR /opt/apache-activemq-5.16.5/bin

RUN  sed -i "s|127.0.0.1|0.0.0.0|g" /opt/apache-activemq-5.16.5/conf/jetty.xml

# Start up the activemq server
ENTRYPOINT ["./activemq","console"]