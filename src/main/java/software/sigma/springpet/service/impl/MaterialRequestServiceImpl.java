package software.sigma.springpet.service.impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.sigma.springpet.domain.MaterialRequestRepository;
import software.sigma.springpet.model.MaterialRequest;
import software.sigma.springpet.service.MaterialRequestService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service implementation for MaterialRequest entity.
 *
 * @author Andriy Klymenko
 */
@Log4j2
@Service
public class MaterialRequestServiceImpl implements MaterialRequestService {

    @Autowired
    private MaterialRequestRepository repository;

    @Override
    public Optional<MaterialRequest> findById(String id) {
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
    public MaterialRequest save(MaterialRequest entity) {
        return repository.save(entity);
    }

    @Override
    public boolean delete(String id) {
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean exists(String id) {
        return repository.existsById(id);
    }

}
