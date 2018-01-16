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
     * @param amountVal amount of requests
     * @return test results as JSON
     */
    @GetMapping(path = "/get/{amountVal}", produces = "application/json;charset=UTF-8")
    public String findAll(@PathVariable int amountVal) {
        int amount = (amountVal < 1) ? 1 : amountVal;

        long start = System.currentTimeMillis();
        for (int i = 0; i < amount; i++) {
            service.findAll();
        }
        long simpleTime = System.currentTimeMillis() - start;

        start = System.currentTimeMillis();
        for (int i = 0; i < amount; i++) {
            fluxService.findAll();
        }
        long reactTime = System.currentTimeMillis() - start;
        return createJsonString("findAll", amount, simpleTime, reactTime);
    }

    /**
     * Endpoint to get material request by id with performance testing.
     *
     * @param id id
     * @param amountVal amount of requests
     * @return test results as JSON
     */
    @GetMapping(path = "/get/{id}/{amountVal}", produces = "application/json;charset=UTF-8")
    public String find(@PathVariable String id, @PathVariable int amountVal) {
        int amount = (amountVal < 1) ? 1 : amountVal;

        long start = System.currentTimeMillis();
        for (int i = 0; i < amount; i++) {
            service.findById(id);
        }
        long simpleTime = System.currentTimeMillis() - start;

        start = System.currentTimeMillis();
        for (int i = 0; i < amount; i++) {
            fluxService.findById(id);
        }
        long reactTime = System.currentTimeMillis() - start;

        return createJsonString("findById", amount, simpleTime, reactTime);
    }

    private String createJsonString(String methodName, int amount, long simpleTime, long reactTime) {
        JSONObject json = new JSONObject();
        json.put("method", methodName);
        json.put("requests amount", amount);
        json.put("simple response time", simpleTime);
        json.put("reactive response time", reactTime);
        json.put("acceleration factor", simpleTime / (reactTime + 1));
        return json.toString();
    }

}
