package software.sigma.springpet.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import software.sigma.springpet.domain.MaterialRequestRepository;
import software.sigma.springpet.model.MaterialRequest;
import software.sigma.springpet.service.MaterialRequestService;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Test class for the {@link MaterialRequestServiceImpl} class.
 *
 * @author Andriy Klymenko
 */
@RunWith(SpringRunner.class)
@ImportAutoConfiguration(ServiceTestConfiguration.class)
public class MaterialRequestServiceImplTest {

    private static final String ID = "id";
    private static final String CUSTOMER_NAME = "customerName";
    private static final String INVOICE = "invoice";

    private MaterialRequest materialRequest;

    @Autowired
    private MaterialRequestService service;

    @MockBean
    private MaterialRequestRepository repository;

    @Before
    public void init() {
        materialRequest = new MaterialRequest(1, CUSTOMER_NAME, 2, INVOICE);
        materialRequest.setId(ID);
    }

    @Test
    public void shouldReturnAllEntities() {
        Iterable <MaterialRequest> expected = List.of(materialRequest);
        when(repository.findAll()).thenReturn(expected);
        assertEquals(expected, service.findAll());
    }

    @Test
    public void shouldReturnEntityById() {
        Optional<MaterialRequest> expected = Optional.of(materialRequest);
        when(repository.findById(ID)).thenReturn(expected);
        assertEquals(expected, service.findById(ID));
    }

    @Test
    public void shouldReturnEntitiesByInvoice() {
        List <MaterialRequest> expected = List.of(materialRequest);
        when(repository.findByInvoice(INVOICE)).thenReturn(expected);
        assertEquals(expected, service.findByInvoice(INVOICE));
    }

    @Test
    public void shouldReturnEntitiesByCustomerName() {
        List <MaterialRequest> expected = List.of(materialRequest);
        when(repository.findByCustomerName(CUSTOMER_NAME)).thenReturn(expected);
        assertEquals(expected, service.findByCustomerName(CUSTOMER_NAME));
    }

    @Test
    public void shouldSaveEntity() {
        when(repository.save(materialRequest)).thenReturn(materialRequest);
        assertEquals(materialRequest, service.save(materialRequest));
    }

    @Test
    public void shouldReturnTrueIfExistsById() {
        when(repository.existsById(ID)).thenReturn(true);
        assertTrue(service.exists(ID));
    }

    @Test
    public void shouldDeleteById() {
        doNothing().when(repository).deleteById(ID);
        service.delete(ID);
        verify(repository).deleteById(ID);
    }
}
