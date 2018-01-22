package software.sigma.springpet.service.impl;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import software.sigma.springpet.service.MaterialRequestFluxService;

/**
 * Configuration class for testing the {@link MaterialRequestFluxServiceImpl} class.
 *
 * @author Andriy Klymenko
 */
@TestConfiguration
public class FluxServiceTestConfiguration {

    @Bean
    public MaterialRequestFluxService materialRequestFluxService() {
        return new MaterialRequestFluxServiceImpl();
    }
}
