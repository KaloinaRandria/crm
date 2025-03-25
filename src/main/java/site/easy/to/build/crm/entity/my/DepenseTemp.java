package site.easy.to.build.crm.entity.my;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "depense_temp")
public class DepenseTemp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_depense_temp")
    int id;

    @Column(name = "customer_email")
    String customerEmail;

    @Column(name = "subject_or_name")
    String subjectOrName;

    @Column(name = "type")
    String type;

    @Column(name = "status")
    String status;

    @Column(name = "expense")
    BigDecimal expense;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getSubjectOrName() {
        return subjectOrName;
    }

    public void setSubjectOrName(String subjectOrName) {
        this.subjectOrName = subjectOrName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getExpense() {
        return expense;
    }

    public void setExpense(BigDecimal expense) {
        this.expense = expense;
    }

    public void setExpense(String expense) throws Exception {

        try {
            this.setExpense(BigDecimal.valueOf(Double.parseDouble(expense)));
        } catch (Exception e) {
            throw new Exception("Expense Value not Conform");
        }

        if (BigDecimal.valueOf(Double.parseDouble(expense)).compareTo(BigDecimal.valueOf(0)) <= 0) {
            throw new Exception("Expense must be Positive");
        }
    }

    public DepenseTemp(int id, String customerEmail, String subjectOrName, String type, String status, BigDecimal expense) {
        this.id = id;
        this.customerEmail = customerEmail;
        this.subjectOrName = subjectOrName;
        this.type = type;
        this.status = status;
        this.expense = expense;
    }

    public DepenseTemp() {
    }
}
