package software.sigma.springpet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
@RestController
@RequestMapping(value = "/materialrequest/flux")
public class MaterialRequestFluxController {

    @Autowired
    private MaterialRequestFluxService fluxService;

    /**
     * Endpoint to get material request by id using flux service.
     *
     * @param id id
     * @return material request as JSON
     */
    @GetMapping(path = "/get/{id}")
    public Mono<MaterialRequest> fluxFind(@PathVariable Long id) {
        return  fluxService.getMaterialRequest(id);
    }

    /**
     * Endpoint to add material request using flux service.
     *
     * @param materialRequest material request
     * @return material request as JSON
     */
    @PostMapping(path = "/save")
    public Mono<MaterialRequest> fluxSave(@RequestBody MaterialRequest materialRequest) {
        return  fluxService.save(Mono.justOrEmpty(materialRequest));
    }

    /**
     * Endpoint to delete material request with specified id.
     *
     * @param id id
     * @return result info
     */
    @DeleteMapping("/delete/{id}")
    public Mono<ResponseEntity> delete(@PathVariable Long id) {
        ResponseEntity responseEntity = null;

        if (!fluxService.exists(id)) {
            responseEntity = new ResponseEntity("No MaterialRequest found for ID: " + id, HttpStatus.NOT_FOUND);
        } else if (!fluxService.delete(id)) {
            responseEntity = new ResponseEntity("Delete error for MaterialRequest ID: " + id, HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            responseEntity = new ResponseEntity("Deleted MaterialRequest ID: " + id, HttpStatus.OK);
        }

        return Mono.justOrEmpty(responseEntity);
    }

    /**
     * Endpoint to get all material requests using flux service.
     *
     * @return list of material requests as JSON
     */
    @GetMapping(path = "/get")
    public Flux<MaterialRequest> fluxFindAll() {
        return  fluxService.getAll();
    }

    /**
     * Endpoint to get material requests by customer name using flux service.
     *
     * @param name customer name
     * @return list of material requests as JSON
     */
    @GetMapping(value = "/getByCustomerName")
    public Flux<MaterialRequest> fluxFindByCustomerName(@RequestParam(value = "customerName") String name) {
        return  fluxService.findByCustomerName(name);
    }

    /**
     * Endpoint to get material requests by invoice using flux service.
     *
     * @param invoice invoice
     * @return list of material requests as JSON
     */
    @GetMapping(value = "/getByInvoice")
    public Flux<MaterialRequest> fluxFindByInvoice(@RequestParam(value = "invoice") String invoice) {
        return  fluxService.findByInvoice(invoice);
    }
}
