package software.sigma.springpet.service.impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import software.sigma.springpet.domain.MaterialRequestFluxRepository;
import software.sigma.springpet.model.MaterialRequest;
import software.sigma.springpet.service.MaterialRequestFluxService;

/**
 * @author Andriy Klymenko
 */
@Log4j2
@Service
public class MaterialRequestFluxServiceImpl implements MaterialRequestFluxService {

    @Autowired
    private MaterialRequestFluxRepository repository;

    @Override
    public Mono<MaterialRequest> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Flux<MaterialRequest> findByCustomerName(String customerName) {
        return repository.findByCustomerName(customerName);
    }

    @Override
    public Flux<MaterialRequest> findByInvoice(String invoice) {
        return repository.findByInvoice(invoice);
    }

    @Override
    public Flux<MaterialRequest> findAll() {
        return repository.findAll();
    }

    @Override
    public Mono<MaterialRequest> save(Mono<MaterialRequest> materialRequest) {
        return materialRequest.doOnNext(entity -> {
            repository.save(entity);
        });
    }

    @Override
    public Mono<Boolean> delete(String id) {
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            Mono.just(false);
        }
        return Mono.just(true);
    }

    @Override
    public Mono<Boolean> exists(String id) {
        return repository.existsById(id);
    }

}
