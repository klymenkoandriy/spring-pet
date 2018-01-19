FROM openjdk:9
EXPOSE 8080
ADD target/spring-boot-ex.jar app.jar
ENTRYPOINT ["java","-Dspring.data.mongodb.host=mongo","-Dspring.data.mongodb.port=27017","--add-opens=java.base/java.nio=ALL-UNNAMED","--add-opens=java.base/sun.nio.ch=ALL-UNNAMED","--add-opens=java.base/java.lang=ALL-UNNAMED","--add-opens=java.base/java.lang.invoke=ALL-UNNAMED","-jar","/app.jar"]
