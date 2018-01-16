package software.sigma.springpet.domain;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import software.sigma.springpet.model.MaterialRequest;

/**
 * @author Andriy Klymenko
 */
public interface MaterialRequestFluxRepository extends ReactiveCrudRepository<MaterialRequest, String> {
    /**
     * Returns a Flux object that represents a list of entities with the specified customerName.
     *
     * @param customerName customer name
     * @return Flux object for entity list
     */
    Flux<MaterialRequest> findByCustomerName(String customerName);

    /**
     * Returns a Flux object that represents a list of entities with the specified invoice.
     *
     * @param invoice invoice
     * @return Flux object for entity list
     */
    Flux<MaterialRequest> findByInvoice(String invoice);

}
