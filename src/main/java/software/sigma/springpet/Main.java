package software.sigma.springpet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Main start class.
 *
 * @author Andriy Klymenko
 */
@RestController
@EnableAutoConfiguration
@ComponentScan
public class Main {

    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    /**
     * The home REST endpoint.
     *
     * @return response
     */
    @RequestMapping("/")
    String home() {
        return "Home";
    }

    /**
     * The main method to start application.
     *
     * @param args arguments
     * @throws Exception exception
     */
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Main.class, args);
        LOGGER.info("Application started");
    }
}
