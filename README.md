#spring-pet application
Pet project to try Spring, SpringBoot, Docker, WebFlux, Reactor.

### Using command line
For making all loggers asynchronous set the SystemProperty Log4jContextSelector to org.apache.logging.log4j.core.async.AsyncLoggerContextSelector:

    -DLog4jContextSelector=org.apache.logging.log4j.core.async.AsyncLoggerContextSelector
    
For disabling warnings about illegal reflective access operations in Java 9:
    
    --add-opens=java.base/java.nio=ALL-UNNAMED --add-opens=java.base/sun.nio.ch=ALL-UNNAMED --add-opens=java.base/java.lang=ALL-UNNAMED --add-opens=java.base/java.lang.invoke=ALL-UNNAMED
