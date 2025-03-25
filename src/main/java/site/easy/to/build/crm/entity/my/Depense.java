package site.easy.to.build.crm.entity.my;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import site.easy.to.build.crm.entity.Lead;
import site.easy.to.build.crm.entity.Ticket;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "depense")
public class Depense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_depense")
    private Integer idDepense;

    @Column(name = "montant")
    @Range(min = 0)
    private BigDecimal montant;

    @Column(name = "date")
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "id_ticket" , referencedColumnName = "ticket_id")
    private Ticket ticket;

    @ManyToOne
    @JoinColumn(name = "id_lead" , referencedColumnName = "lead_id")
    private Lead lead;

    public Integer getIdDepense() {
        return idDepense;
    }

    public void setIdDepense(Integer idDepense) {
        this.idDepense = idDepense;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public void setMontant (String montant) {
        this.setMontant(new BigDecimal(montant));
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public Lead getLead() {
        return lead;
    }

    public void setLead(Lead lead) {
        this.lead = lead;
    }

    public Depense() {}

    public Depense(Integer idDepense, BigDecimal montant, LocalDateTime date, Ticket ticket, Lead lead) {
        this.idDepense = idDepense;
        this.montant = montant;
        this.date = date;
        this.ticket = ticket;
        this.lead = lead;
    }
}
