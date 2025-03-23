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

    @Column(name = "valeur")
    private double valeur;

    @Column(name = "date")
    private LocalDateTime date;

    public Integer getIdTaux() {
        return idTaux;
    }

    public void setIdTaux(Integer idTaux) {
        this.idTaux = idTaux;
    }

    public double getValeur() {
        return valeur;
    }

    public void setValeur(double valeur) {
        this.valeur = valeur;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Taux() {}

    public Taux(Integer idTaux, double valeur, LocalDateTime date) {
        this.idTaux = idTaux;
        this.valeur = valeur;
        this.date = date;
    }
}
