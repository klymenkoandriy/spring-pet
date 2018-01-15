package software.sigma.springpet.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import software.sigma.springpet.domain.MaterialRequestRepository;
import software.sigma.springpet.model.MaterialRequest;
import software.sigma.springpet.service.MaterialRequestFluxService;

/**
 * @author Andriy Klymenko
 */
@Service
public class MaterialRequestFluxServiceImpl implements MaterialRequestFluxService {

    @Autowired
    private MaterialRequestRepository repository;

    @Override
    public Mono<MaterialRequest> getMaterialRequest(long id) {
        return Mono.justOrEmpty(repository.findById(id));
    }

    @Override
    public Flux<MaterialRequest> findByCustomerName(String customerName) {
        return Flux.fromIterable(repository.findByCustomerName(customerName));
    }

    @Override
    public Flux<MaterialRequest> findByInvoice(String invoice) {
        return Flux.fromIterable(repository.findByInvoice(invoice));
    }

    @Override
    public Flux<MaterialRequest> allMaterialRequest() {
        return Flux.fromIterable(repository.findAll());
    }

    @Override
    public Mono<MaterialRequest> saveMaterialRequest(Mono<MaterialRequest> materialRequest) {
        return materialRequest.doOnNext(entity -> {
            repository.save(entity);
        });
    }

}
