package software.sigma.springpet.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

/**
 * Material Request entity.
 *
 * @author Andriy Klymenko
 */
@NoArgsConstructor
public class MaterialRequest {

    /**
     * The unique technical key for the material request.
     */
    @Id
    @Getter
    @Setter
    private String id;

    /**
     * The request number represents the business key of a material request together with the order position.
     */
    @Getter
    @Setter
    private Integer requestNumber;

    /**
     * The name of the customer to which the material request refers.
     */
    @Getter
    @Setter
    private String customerName;

    /**
     * Priority.
     */
    @Getter
    @Setter
    private Integer priority = 0;

    /**
     * Invoice number (character-digital code).
     */
    @Getter
    @Setter
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
