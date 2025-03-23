package site.easy.to.build.crm.entity.my.api;

import java.time.LocalDateTime;

public class LeadModel {
    private int idLead;
    private String customerName;
    private LocalDateTime dateTime;
    private String leadName;
    private double montantDepense;

    public double getMontantDepense() {
        return montantDepense;
    }

    public void setMontantDepense(double montantDepense) {
        this.montantDepense = montantDepense;
    }

    public int getIdLead() {
        return idLead;
    }

    public void setIdLead(int idLead) {
        this.idLead = idLead;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getLeadName() {
        return leadName;
    }

    public void setLeadName(String leadName) {
        this.leadName = leadName;
    }

    public LeadModel() {
    }

    public LeadModel(int idLead, String customerName, LocalDateTime dateTime, String leadName , double montantDepense) {
        this.idLead = idLead;
        this.customerName = customerName;
        this.dateTime = dateTime;
        this.leadName = leadName;
        this.montantDepense = montantDepense;
    }
}
