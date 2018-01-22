package software.sigma.springpet.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import software.sigma.springpet.model.MaterialRequest;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Test class for the {@link MaterialRequestRepository}.
 *
 * @author Andriy Klymenko
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes={ RepositoryTestConfiguration.class, MaterialRequestRepository.class})
public class MaterialRequestRepositoryTest {

    private static final String ID = "id";
    private static final String CUSTOMER_NAME = "customerName";
    private static final String INVOICE = "invoice";

    private MaterialRequest materialRequest;

    @Autowired
    private MaterialRequestRepository repository;

    @Before
    public void init() {
        materialRequest = new MaterialRequest(1, CUSTOMER_NAME, 2, INVOICE);
        materialRequest.setId(ID);
        repository.save(materialRequest);
    }

    @Test
    public void shouldReturnEntitiesByCustomerName() {
        List<MaterialRequest> result = repository.findByCustomerName(CUSTOMER_NAME);
        assertEquals(1, result.size());
        assertEquals(materialRequest, result.get(0));
    }

    @Test
    public void shouldReturnEntitiesByInvoice() {
        List<MaterialRequest> result = repository.findByInvoice(INVOICE);
        assertEquals(1, result.size());
        assertEquals(materialRequest, result.get(0));
    }

}
