package software.sigma.springpet.service.impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import software.sigma.springpet.domain.MaterialRequestFluxRepository;
import software.sigma.springpet.model.MaterialRequest;
import software.sigma.springpet.service.MaterialRequestFluxService;

/**
 * Reactive service implementation for MaterialRequest entity.
 *
 * @author Andriy Klymenko
 */
@Log4j2
@Service
public class MaterialRequestFluxServiceImpl implements MaterialRequestFluxService {

    @Autowired
    private MaterialRequestFluxRepository repository;

    @Override
    public Mono<ResponseEntity<MaterialRequest>> findById(String id) {
        return repository.findById(id)
                .flatMap(entity -> Mono.just(new ResponseEntity<>(entity, HttpStatus.OK)))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
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
    public Mono<MaterialRequest> save(MaterialRequest materialRequest) {
        return repository.save(materialRequest);
    }

    @Override
    public Mono<ResponseEntity<Void>> delete(String id) {
        return repository.findById(id)
                .flatMap(entity -> repository.delete(entity)
                        .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK))))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
