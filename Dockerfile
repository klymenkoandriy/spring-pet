FROM java:9
EXPOSE 8080
ADD target/spring-boot-ex.jar app.jar
ENTRYPOINT ["java","--add-opens=java.base/java.lang=ALL-UNNAMED","--add-opens=java.base/java.lang.invoke=ALL-UNNAMED","-jar","/app.jar"]