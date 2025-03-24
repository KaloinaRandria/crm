package site.easy.to.build.crm.entity.my.api;

import java.time.LocalDateTime;

public class BudgetModel {

    private int idBudgetModel;

    private String customerName;

    private double montant;

    private LocalDateTime dateTime;

    public int getIdBudgetModel() {
        return idBudgetModel;
    }

    public void setIdBudgetModel(int idBudgetModel) {
        this.idBudgetModel = idBudgetModel;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public BudgetModel() {
    }

    public BudgetModel(int idBudgetModel, String customerName, double montant, LocalDateTime dateTime) {
        this.idBudgetModel = idBudgetModel;
        this.customerName = customerName;
        this.montant = montant;
        this.dateTime = dateTime;
    }
}
