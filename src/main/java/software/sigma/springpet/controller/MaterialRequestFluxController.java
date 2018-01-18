package software.sigma.springpet.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import software.sigma.springpet.model.MaterialRequest;
import software.sigma.springpet.service.MaterialRequestFluxService;

/**
 * Controller for Material Requests. Uses WebFlux framework.
 *
 * @author Andriy Klymenko
 */
@Log4j2
@RestController
@RequestMapping(value = "/materialrequest/flux")
public class MaterialRequestFluxController {

    @Autowired
    MaterialRequestFluxService service;

    /**
     * Endpoint to get material request by id using flux service.
     *
     * @param id id
     * @return material request as JSON
     */
    @GetMapping(path = "/get/{id}")
    public Mono<ResponseEntity<MaterialRequest>> fluxFind(@PathVariable String id) {
        return  service.findById(id);
    }

    /**
     * Endpoint to add material request using flux service.
     *
     * @param materialRequest material request
     * @return material request as JSON
     */
    @PostMapping(path = "/save")
    public Mono<MaterialRequest> fluxSave(@RequestBody MaterialRequest materialRequest) {
        return  service.save(materialRequest);
    }

    /**
     * Endpoint to delete material request with specified id.
     *
     * @param id id
     * @return result info
     */
    @DeleteMapping("/delete/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable String id) {
        return service.delete(id);
    }

    /**
     * Endpoint to get all material requests using flux service.
     *
     * @return list of material requests as JSON
     */
    @GetMapping(path = "/get")
    public Flux<MaterialRequest> fluxFindAll() {
        return  service.findAll();
    }

    /**
     * Endpoint to get material requests by customer name using flux service.
     *
     * @param name customer name
     * @return list of material requests as JSON
     */
    @GetMapping(value = "/getByCustomerName")
    public Flux<MaterialRequest> fluxFindByCustomerName(@RequestParam(value = "customerName") String name) {
        return  service.findByCustomerName(name);
    }

    /**
     * Endpoint to get material requests by invoice using flux service.
     *
     * @param invoice invoice
     * @return list of material requests as JSON
     */
    @GetMapping(value = "/getByInvoice")
    public Flux<MaterialRequest> fluxFindByInvoice(@RequestParam(value = "invoice") String invoice) {
        return  service.findByInvoice(invoice);
    }
}
