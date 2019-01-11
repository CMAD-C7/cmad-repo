FROM java:8-jre-alpine
WORKDIR /
COPY --from=build /code/target/nms-dashboard-*.jar app
EXPOSE 8080 
CMD java -jar app 
