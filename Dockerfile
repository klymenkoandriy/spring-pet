FROM java:9
EXPOSE 8080
ADD target/spring-boot-ex.jar app.jar
ENTRYPOINT ["java","--add-opens=java.base/java.nio=ALL-UNNAMED","--add-opens=java.base/sun.nio.ch=ALL-UNNAMED","--add-opens=java.base/java.lang=ALL-UNNAMED","--add-opens=java.base/java.lang.invoke=ALL-UNNAMED","-DLog4jContextSelector=org.apache.logging.log4j.core.async.AsyncLoggerContextSelector","-Dspring.data.mongodb.uri=mongodb://mongo/test","-jar","/app.jar"]
