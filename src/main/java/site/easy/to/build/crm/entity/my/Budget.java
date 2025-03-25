package site.easy.to.build.crm.entity.my;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import site.easy.to.build.crm.entity.Customer;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Entity
@Table(name = "budget")
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_budget")
    private Integer idBudget;

    @ManyToOne
    @JoinColumn(name = "id_customer" , referencedColumnName = "customer_id")
    private Customer customer;

    @Column(name = "montant")
    @Positive(message = "Montant negatif")
    private BigDecimal montant;

    @Column(name = "date")
    @NotNull(message = "Date is required")
    private LocalDateTime date;

    public Integer getIdBudget() {
        return idBudget;
    }

    public void setIdBudget(Integer idBudget) {
        this.idBudget = idBudget;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public void setMontant(String montant) throws Exception {

        try {
            this.setMontant(BigDecimal.valueOf(Double.parseDouble(montant)));
        } catch (Exception e) {
            throw new Exception("Amount Value not Conform");
        }

        if (BigDecimal.valueOf(Double.parseDouble(montant)).compareTo(BigDecimal.valueOf(0)) <= 0) {
            throw new Exception("Amount must be Positive");
        }
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setDate(String date) {
        this.setDate(LocalDateTime.parse(date));
    }

    public Budget() {

    }

    public Budget(Integer idBudget, Customer customer, BigDecimal montant, LocalDateTime date) {
        this.idBudget = idBudget;
        this.customer = customer;
        this.montant = montant;
        this.date = date;
    }
}
