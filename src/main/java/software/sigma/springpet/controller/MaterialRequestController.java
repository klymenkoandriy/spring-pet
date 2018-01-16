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
     * Endpoint to get material request by id.
     *
     * @param id id
     * @return material request as JSON
     */
    @GetMapping(path = "/get/{id}")
    public ResponseEntity find(@PathVariable String id) {
        MaterialRequest entity = service.findById(id).orElse(null);

        if (entity == null) {
            return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
        }

        return   new ResponseEntity(entity, HttpStatus.OK);
    }

    /**
     * Endpoint to add material request.
     *
     * @param materialRequest material request
     * @return material request as JSON
     */
    @PostMapping(path = "/save")
    public ResponseEntity save(@RequestBody MaterialRequest materialRequest) {
        return new ResponseEntity(service.save(materialRequest), HttpStatus.OK);
    }

    /**
     * Endpoint to add material request.
     *
     * @param materialRequest material request
     * @return material request as JSON
     */
    @PostMapping(path = "/update")
    public ResponseEntity update(@RequestBody MaterialRequest materialRequest) {
        return  new ResponseEntity(service.save(materialRequest), HttpStatus.OK);
    }

    /**
     * Endpoint to delete material request with specified id.
     *
     * @param id id
     * @return result info
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable String id) {

        if (!service.exists(id)) {
            return new ResponseEntity("No MaterialRequest found for ID: " + id, HttpStatus.NOT_FOUND);
        }

        if (!service.delete(id)) {
            return new ResponseEntity("Delete error for MaterialRequest ID: " + id, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity("Deleted MaterialRequest ID: " + id, HttpStatus.OK);
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
