package software.sigma.springpet.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

/**
 * Material Request entity.
 *
 * @author Andriy Klymenko
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class MaterialRequest {

    /**
     * The unique technical key for the material request.
     */
    @Id
    private String id;

    /**
     * The request number represents the business key of a material request together with the order position.
     */
    private Integer requestNumber;

    /**
     * The name of the customer to which the material request refers.
     */
    private String customerName;

    /**
     * Priority.
     */
    private Integer priority = 0;

    /**
     * Invoice number (character-digital code).
     */
    private String invoice;

    /**
     * Constructor for creation.
     *
     * @param requestNumber request number
     * @param customerName customer name
     * @param priority priority
     * @param invoice invoice
     */
    public MaterialRequest(Integer requestNumber, String customerName, Integer priority, String invoice) {
        this.requestNumber = requestNumber;
        this.customerName = customerName;
        this.priority = priority;
        this.invoice = invoice;
    }
}
