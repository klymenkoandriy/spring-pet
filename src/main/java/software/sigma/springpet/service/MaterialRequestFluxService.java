package software.sigma.springpet.service;

import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import software.sigma.springpet.model.MaterialRequest;

/**
 * Material request reactive access service.
 *
 * @author Andriy Klymenko
 */
public interface MaterialRequestFluxService {

    /**
     * Returns the entity with the specified id.
     *
     * @param id id
     * @return response
     */
    Mono<ResponseEntity<MaterialRequest>> findById(String id);

    /**
     * Returns the Flux object that represents the entity list with the specified customerName.
     *
     * @param customerName customer name
     * @return Flux object for entity list
     */
    Flux<MaterialRequest> findByCustomerName(String customerName);

    /**
     * Returns the Flux object that represents the entity list with the specified invoice.
     *
     * @param invoice invoice
     * @return Flux object for entity list
     */
    Flux<MaterialRequest> findByInvoice(String invoice);

    /**
     * Returns the Flux object that represents the list of all entities.
     *
     * @return Flux object for entity list
     */
    Flux<MaterialRequest> findAll();

    /**
     * Saves the entity and returns the Mono object that represents the entity.
     *
     * @param materialRequest material request
     * @return Mono object for material request
     */
    Mono<MaterialRequest> save(MaterialRequest materialRequest);

    /**
     * Deletes the entity with the specified id.
     *
     * @param id id
     * @return response
     */
    Mono<ResponseEntity<Void>> delete(String id);

}
