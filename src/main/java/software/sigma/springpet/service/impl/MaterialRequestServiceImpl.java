package software.sigma.springpet.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.sigma.springpet.domain.MaterialRequestRepository;
import software.sigma.springpet.model.MaterialRequest;
import software.sigma.springpet.service.MaterialRequestService;

import java.util.ArrayList;
import java.util.List;

/**
 * Service implementation for MaterialRequest entity.
 *
 * @author Andriy Klymenko
 */
@Service
public class MaterialRequestServiceImpl implements MaterialRequestService {

    @Autowired
    private MaterialRequestRepository repository;

    @Override
    public MaterialRequest findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<MaterialRequest> findByCustomerName(String customerName) {
        return repository.findByCustomerName(customerName);
    }

    @Override
    public List<MaterialRequest> findByInvoice(String invoice) {
        return repository.findByInvoice(invoice);
    }

    @Override
    public List<MaterialRequest> findAll() {
        List<MaterialRequest> result = new ArrayList<>();
        repository.findAll().forEach(result::add);
        return result;
    }

    @Override
    public MaterialRequest add(MaterialRequest entity) {
        return repository.save(entity);
    }
}
