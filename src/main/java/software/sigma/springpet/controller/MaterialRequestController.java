package software.sigma.springpet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import software.sigma.springpet.model.MaterialRequest;
import software.sigma.springpet.service.MaterialRequestFluxService;
import software.sigma.springpet.service.MaterialRequestService;

import java.util.List;

/**
 * Controller for Material Requests.
 *
 * @author Andriy Klymenko
 */
@RestController
@RequestMapping(value = "/materialrequest")
public class MaterialRequestController {

    @Autowired
    private MaterialRequestService service;

    @Autowired
    private MaterialRequestFluxService fluxService;

    /**
     * Endpoint to get material request by id.
     *
     * @param id id
     * @return material request as JSON
     */
    @GetMapping(path = "/get/{id}", produces = "application/json;charset=UTF-8")
    public MaterialRequest find(@PathVariable Long id) {
        return  service.findById(id).orElse(null);
    }

    /**
     * Endpoint to add material request.
     *
     * @param materialRequest material request
     * @return material request as JSON
     */
    @RequestMapping(path = "/add", consumes = "application/json;charset=UTF-8", produces = "application/json;charset=UTF-8")
    public MaterialRequest add(@RequestBody MaterialRequest materialRequest) {
        return  service.add(materialRequest);
    }

    /**
     * Endpoint to get all material requests.
     *
     * @return list of material requests as JSON
     */
    @GetMapping(path = "/get", produces = "application/json;charset=UTF-8")
    public List<MaterialRequest> findAll() {
        return  service.findAll();
    }

    /**
     * Endpoint to get material requests by customer name.
     *
     * @param name customer name
     * @return list of material requests as JSON
     */
    @RequestMapping(value = "/getByCustomerName", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public List<MaterialRequest> findByCustomerName(@RequestParam(value = "customerName") String name) {
        return  service.findByCustomerName(name);
    }

    /**
     * Endpoint to get material requests by invoice.
     *
     * @param invoice invoice
     * @return list of material requests as JSON
     */
    @RequestMapping(value = "/getByInvoice", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public List<MaterialRequest> findByInvoice(@RequestParam(value = "invoice") String invoice) {
        return  service.findByInvoice(invoice);
    }

    /**
     * Endpoint to get material request by id using flux service.
     *
     * @param id id
     * @return material request as JSON
     */
    @GetMapping(path = "/flux/get/{id}", produces = "application/json;charset=UTF-8")
    public Mono<MaterialRequest> fluxFind(@PathVariable Long id) {
        return  fluxService.getMaterialRequest(id);
    }

    /**
     * Endpoint to add material request using flux service.
     *
     * @param materialRequest material request
     * @return material request as JSON
     */
    @RequestMapping(path = "/flux/add", consumes = "application/json;charset=UTF-8", produces = "application/json;charset=UTF-8")
    public Mono<MaterialRequest> fluxAdd(@RequestBody MaterialRequest materialRequest) {
        return  fluxService.saveMaterialRequest(Mono.justOrEmpty(materialRequest));
    }

    /**
     * Endpoint to get all material requests using flux service.
     *
     * @return list of material requests as JSON
     */
    @GetMapping(path = "/flux/get", produces = "application/json;charset=UTF-8")
    public Flux<MaterialRequest> fluxFindAll() {
        return  fluxService.allMaterialRequest();
    }

    /**
     * Endpoint to get material requests by customer name using flux service.
     *
     * @param name customer name
     * @return list of material requests as JSON
     */
    @RequestMapping(value = "/flux/getByCustomerName", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public Flux<MaterialRequest> fluxFindByCustomerName(@RequestParam(value = "customerName") String name) {
        return  fluxService.findByCustomerName(name);
    }

    /**
     * Endpoint to get material requests by invoice using flux service.
     *
     * @param invoice invoice
     * @return list of material requests as JSON
     */
    @RequestMapping(value = "/flux/getByInvoice", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public Flux<MaterialRequest> fluxFindByInvoice(@RequestParam(value = "invoice") String invoice) {
        return  fluxService.findByInvoice(invoice);
    }


}
