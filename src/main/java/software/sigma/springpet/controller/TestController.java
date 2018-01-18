package software.sigma.springpet.controller;

import lombok.extern.log4j.Log4j2;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import software.sigma.springpet.service.MaterialRequestFluxService;
import software.sigma.springpet.service.MaterialRequestService;

/**
 * Controller for performance testing.
 *
 * @author Andriy Klymenko
 */
@Log4j2
@RestController
@RequestMapping(value = "/materialrequest/test")
public class TestController {

    @Autowired
    private MaterialRequestService service;

    @Autowired
    private MaterialRequestFluxService fluxService;

    /**
     * Endpoint to get all material requests with performance testing.
     *
     * @param number number of requests
     * @return test results as JSON
     */
    @GetMapping(path = "/get/{number}", produces = "application/json;charset=UTF-8")
    public String findAll(@PathVariable int number) {
        int numberOfRequests = (number < 1) ? 1 : number;
        int numberOfEntities = service.findAll().size();

        long start = System.nanoTime();
        for (int i = 0; i < numberOfRequests; i++) {
            service.findAll();
        }
        long simpleTime = System.nanoTime() - start;

        start = System.nanoTime();
        for (int i = 0; i < numberOfRequests; i++) {
            fluxService.findAll();
        }
        long reactTime = System.nanoTime() - start;
        return createJsonString("findAll", numberOfRequests, simpleTime, reactTime, numberOfEntities);
    }

    /**
     * Endpoint to get material request by id with performance testing.
     *
     * @param id id
     * @param number number of requests
     * @return test results as JSON
     */
    @GetMapping(path = "/get/{id}/{number}", produces = "application/json;charset=UTF-8")
    public String find(@PathVariable String id, @PathVariable int number) {
        int numberOfRequests = (number < 1) ? 1 : number;
        int numberOfEntities =  (service.findById(id) == null) ? 0 : 1;

        long start = System.nanoTime();
        for (int i = 0; i < numberOfRequests; i++) {
            service.findById(id);
        }
        long simpleTime = System.nanoTime() - start;

        start = System.nanoTime();
        for (int i = 0; i < numberOfRequests; i++) {
            fluxService.findById(id);
        }
        long reactTime = System.nanoTime() - start;

        return createJsonString("findById", numberOfRequests, simpleTime, reactTime, numberOfEntities);
    }

    private String createJsonString(String methodName, int numberOfRequests, long simpleTime, long reactTime, long numberOfEntities) {
        JSONObject json = new JSONObject();
        json.put("method name", methodName);
        json.put("number of requests", numberOfRequests);
        json.put("number of entities", numberOfEntities);
        json.put("simple response time (ns)", simpleTime);
        json.put("reactive response time (ns)", reactTime);
        json.put("acceleration factor", simpleTime / (reactTime + 1));
        return json.toString();
    }

}
