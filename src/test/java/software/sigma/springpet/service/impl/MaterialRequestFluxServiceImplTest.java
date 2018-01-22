package software.sigma.springpet.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import software.sigma.springpet.domain.MaterialRequestFluxRepository;
import software.sigma.springpet.model.MaterialRequest;
import software.sigma.springpet.service.MaterialRequestFluxService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Test class for the {@link MaterialRequestFluxServiceImpl}.
 *
 * @author Andriy Klymenko
 */
@RunWith(SpringRunner.class)
@ImportAutoConfiguration(FluxServiceTestConfiguration.class)
public class MaterialRequestFluxServiceImplTest {

    private static final String ID = "id";
    private static final String CUSTOMER_NAME = "customerName";
    private static final String INVOICE = "invoice";

    private MaterialRequest materialRequest;

    private List<MaterialRequest> expectedList;

    private List<MaterialRequest> resultList;

    @Autowired
    private MaterialRequestFluxService service;

    @MockBean
    private MaterialRequestFluxRepository repository;

    @Before
    public void init() {
        materialRequest = new MaterialRequest(1, CUSTOMER_NAME, 2, INVOICE);
        materialRequest.setId(ID);
        expectedList = List.of(materialRequest);
        resultList = new ArrayList<>();
    }

    @Test
    public void shouldFindEntityAndReturnItInResponse() {
        when(repository.findById(ID)).thenReturn(Mono.just(materialRequest));
        ResponseEntity<MaterialRequest> response =  service.findById(ID).block();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(materialRequest, response.getBody());
    }

    @Test
    public void shouldReturnResponseIfEntityNotFound() {
        when(repository.findById(ID)).thenReturn(Mono.justOrEmpty(null));
        ResponseEntity<MaterialRequest> response =  service.findById(ID).block();
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(null, response.getBody());
    }

    @Test
    public void shouldReturnEntitiesByCustomerName() {
        when(repository.findByCustomerName(CUSTOMER_NAME)).thenReturn(Flux.fromIterable(expectedList));
        service.findByCustomerName(CUSTOMER_NAME).subscribe(resultList::add);
        verify(repository).findByCustomerName(CUSTOMER_NAME);
        assertEquals(expectedList, resultList);
    }

    @Test
    public void shouldReturnEntitiesByInvoice() {
        when(repository.findByInvoice(INVOICE)).thenReturn(Flux.fromIterable(expectedList));
        service.findByInvoice(INVOICE).subscribe(resultList::add);
        verify(repository).findByInvoice(INVOICE);
        assertEquals(expectedList, resultList);
    }

    @Test
    public void shouldReturnAllEntities() {
        when(repository.findAll()).thenReturn(Flux.fromIterable(expectedList));
        service.findAll().subscribe(resultList::add);
        verify(repository).findAll();
        assertEquals(expectedList, resultList);
    }

    @Test
    public void shouldSaveEntityAndReturnIt() {
        when(repository.save(materialRequest)).thenReturn(Mono.just(materialRequest));
        MaterialRequest result =  service.save(materialRequest).block();
        assertEquals(materialRequest, result);
    }

    @Test
    public void shouldDeleteById() {
        when(repository.findById(ID)).thenReturn(Mono.just(materialRequest));
        when(repository.delete(materialRequest)).thenReturn(Mono.empty());
        ResponseEntity<Void> response =  service.delete(ID).block();
        verify(repository).delete(materialRequest);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void shouldReturnResponseIfDeletingEntityNotFound() {
        when(repository.findById(ID)).thenReturn(Mono.justOrEmpty(null));
        when(repository.delete(materialRequest)).thenReturn(Mono.empty());
        ResponseEntity<Void> response =  service.delete(ID).block();
        verify(repository, never()).delete(materialRequest);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
