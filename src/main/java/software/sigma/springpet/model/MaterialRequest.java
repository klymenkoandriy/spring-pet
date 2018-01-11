package software.sigma.springpet.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Material Request entity.
 *
 * @author Andriy Klymenko
 */
@Entity
@NoArgsConstructor
public class MaterialRequest {

    /**
     * The unique technical key for the material request.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    /**
     * The request number represents the business key of a material request together with the order position.
     */
    @Column(nullable = false)
    @Getter
    @Setter
    private Integer requestNumber;

    /**
     * The name of the customer to which the material request refers.
     */
    @Column(nullable = false)
    @Getter
    @Setter
    private String customerName;

    /**
     * Priority.
     */
    @Column(nullable = false)
    @Getter
    @Setter
    private Integer priority = 0;

    /**
     * Invoice number (character-digital code).
     */
    @Column(nullable = false, unique = true)
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
