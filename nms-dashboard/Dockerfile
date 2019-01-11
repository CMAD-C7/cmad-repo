FROM maven:3.6-jdk-8-alpine
WORKDIR /opt/SyslogServer
COPY . .
RUN mvn compile
RUN mvn -DskipTests package
RUN cd target
EXPOSE 8080
CMD java -jar nms-dashboard.jar
