package software.sigma.springpet.domain;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Configuration;

/**
 *  Configuration class.
 *
 * @author Andriy Klymenko
 */
@Configuration
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class RepositoryTestConfiguration {

}
