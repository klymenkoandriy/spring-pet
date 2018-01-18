package software.sigma.springpet.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import software.sigma.springpet.model.MaterialRequest;
import software.sigma.springpet.service.MaterialRequestService;

import java.util.List;
import java.util.Optional;

/**
 * Controller for Material Requests.
 *
 * @author Andriy Klymenko
 */
@Log4j2
@RestController
@RequestMapping(value = "/materialrequest")
public class MaterialRequestController {

    @Autowired
    private MaterialRequestService service;

    /**
     * Endpoint to get the material request by id.
     *
     * @param id id
     * @return material request as JSON
     */
    @GetMapping(path = "/get/{id}")
    public ResponseEntity find(@PathVariable String id) {
        Optional<MaterialRequest> entity = service.findById(id);
        return (entity.isPresent()) ? new ResponseEntity<>(entity.get(), HttpStatus.OK) : new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    /**
     * Endpoint to create the material request.
     *
     * @param materialRequest material request
     * @return material request as JSON
     */
    @PostMapping(path = "/save")
    public ResponseEntity create(@RequestBody MaterialRequest materialRequest) {
        return new ResponseEntity<>(service.save(materialRequest), HttpStatus.OK);
    }

    /**
     * Endpoint to delete the material request with specified id.
     *
     * @param id id
     * @return result info
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable String id) {
        if (!service.exists(id)) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        service.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * Endpoint to get all material requests.
     *
     * @return list of material requests as JSON
     */
    @GetMapping(path = "/get")
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

}
