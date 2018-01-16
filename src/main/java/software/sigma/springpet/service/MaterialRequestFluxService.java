package software.sigma.springpet.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import software.sigma.springpet.model.MaterialRequest;

/**
 * @author Andriy Klymenko
 */
public interface MaterialRequestFluxService {

    /**
     * Returns the entity with the specified id.
     *
     * @param id id
     * @return material request
     */
    Mono<MaterialRequest> findById(String id);

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
    Mono<MaterialRequest> save(Mono<MaterialRequest> materialRequest);


    /**
     * Deletes the entity with the specified id.
     *
     * @param id id
     * @return <code>true</code> if successful, otherwise <code>false</code>
     */
    Mono<Boolean> delete(String id);

    /**
     * Checks whether the entity exists or not.
     *
     * @param id id
     * @return <code>true</code> if the entity exists, otherwise <code>false</code>
     */
    Mono<Boolean> exists(String id);
}
