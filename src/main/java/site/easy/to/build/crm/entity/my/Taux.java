package site.easy.to.build.crm.entity.my;

import jakarta.persistence.*;

import java.time.LocalDateTime;
@Entity
@Table(name = "taux")
public class Taux {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_taux")
    private  Integer idTaux;

    @Column(name = "montant")
    private double montant;

    @Column(name = "date")
    private LocalDateTime date;

    public Integer getIdTaux() {
        return idTaux;
    }

    public void setIdTaux(Integer idTaux) {
        this.idTaux = idTaux;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Taux() {}

    public Taux(Integer idTaux, double montant, LocalDateTime date) {
        this.idTaux = idTaux;
        this.montant = montant;
        this.date = date;
    }
}
