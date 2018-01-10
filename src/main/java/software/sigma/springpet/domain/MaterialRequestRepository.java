package software.sigma.springpet.domain;

import org.springframework.data.repository.CrudRepository;
import software.sigma.springpet.model.MaterialRequest;

import java.util.List;

/**
 * Material Request Repository.
 *
 * @author Andriy Klymenko
 */
public interface MaterialRequestRepository extends CrudRepository<MaterialRequest, Long> {

    /**
     * Returns the entity with the specified id.
     *
     * @param id id
     * @return material request
     */
    MaterialRequest findById(Integer id);

    /**
     * Returns the entity list with the specified customerName.
     *
     * @param customerName customer name
     * @return list of entities
     */
    List<MaterialRequest> findByCustomerName(String customerName);

    /**
     * Returns the entity list with the specified invoice.
     *
     * @param invoice invoice
     * @return list of entities
     */
    List<MaterialRequest> findByInvoice(String invoice);
}
