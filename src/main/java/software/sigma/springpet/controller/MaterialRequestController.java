package software.sigma.springpet.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
@RestController
@RequestMapping(value = "/materialrequest")
public class MaterialRequestController {

    @Autowired
    private MaterialRequestService service;

    /**
     * Endpoint to get material request by id.
     *
     * @param id id
     * @return MaterialRequest
     */
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public MaterialRequest find(@RequestParam(value = "id") Long id) {
        return service.findById(id).orElse(null);
    }

    /**
     * Endpoint to add material request.
     *
     * @param requestNumber request number
     * @param customerName customer name
     * @param priority priority
     * @param invoice invoice
     * @return material request
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public MaterialRequest add(@RequestParam(value = "requestNumber") Integer requestNumber,
            @RequestParam(value = "customerName") String customerName,
            @RequestParam(value = "priority") Integer priority,
            @RequestParam(value = "invoice") String invoice) {
        return service.add(new MaterialRequest(requestNumber, customerName, priority, invoice));
    }

    /**
     * Endpoint to get all material requests.
     *
     * @return list of material requests
     */
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public List<MaterialRequest> findAll() {
        return service.findAll();
    }

    /**
     * Endpoint to get material requests by customer name.
     *
     * @param name customer name
     * @return list of material requests
     */
    @RequestMapping(value = "/getByCustomerName", method = RequestMethod.GET)
    public List<MaterialRequest> findByCustomerName(@RequestParam(value = "customerName") String name) {
        return service.findByCustomerName(name);
    }

    /**
     * Endpoint to get material requests by invoice.
     *
     * @param invoice invoice
     * @return list of material requests
     */
    @RequestMapping(value = "/getByInvoice", method = RequestMethod.GET)
    public List<MaterialRequest> findByInvoice(@RequestParam(value = "invoice") String invoice) {
        return service.findByInvoice(invoice);
    }
}
