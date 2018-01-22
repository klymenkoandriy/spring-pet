package software.sigma.springpet.service.impl;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import software.sigma.springpet.service.MaterialRequestService;

/**
 * Configuration class for testing the {@link MaterialRequestServiceImpl} class.
 *
 * @author Andriy Klymenko
 */
@TestConfiguration
public class ServiceTestConfiguration {

    @Bean
    public MaterialRequestService materialRequestService() {
        return new MaterialRequestServiceImpl();
    }
}
