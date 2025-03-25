package site.easy.to.build.crm.entity.my.api;

import java.time.LocalDateTime;

public class TicketModel {
    private int idTicket;
    private String subject;
    private String customerName;
    private LocalDateTime dateTime;
    private double montantDepense;

    public int getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(int idTicket) {
        this.idTicket = idTicket;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
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

    public double getMontantDepense() {
        return montantDepense;
    }

    public void setMontantDepense(double montantDepense) {
        this.montantDepense = montantDepense;
    }

    public TicketModel() {
    }

    public TicketModel(int idTicket, String subject, String customerName, LocalDateTime dateTime, double montantDepense) {
        this.idTicket = idTicket;
        this.subject = subject;
        this.customerName = customerName;
        this.dateTime = dateTime;
        this.montantDepense = montantDepense;
    }
}
