package software.sigma.springpet.model;

//import org.springframework.data.annotation.Id;

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
public class MaterialRequest {

    /**
     * The unique technical key for the material request.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * The request number represents the business key of a material request together with the order position.
     */
    @Column(nullable = false)
    private Integer requestNumber;

    /**
     * The name of the customer to which the material request refers.
     */
    @Column(nullable = false)
    private String customerName;

    /**
     * Priority.
     */
    @Column(nullable = false)
    private Integer priority = 0;

    /**
     * Invoice number (character-digital code).
     */
    @Column(nullable = false, unique = true)
    private String invoice;

    /**
     * Default constructor.
     */
    public MaterialRequest() {

    }

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRequestNumber() {
        return requestNumber;
    }

    public void setRequestNumber(Integer requestNumber) {
        this.requestNumber = requestNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }
}
