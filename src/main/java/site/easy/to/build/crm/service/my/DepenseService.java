package site.easy.to.build.crm.service.my;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.easy.to.build.crm.entity.Customer;
import site.easy.to.build.crm.entity.Lead;
import site.easy.to.build.crm.entity.Ticket;
import site.easy.to.build.crm.entity.my.Depense;
import site.easy.to.build.crm.entity.my.api.LeadModel;
import site.easy.to.build.crm.entity.my.api.TicketModel;
import site.easy.to.build.crm.repository.my.DepenseRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class DepenseService {
    @Autowired
    DepenseRepository depenseRepository;

    public void saveDepense(Depense depense) {
        depenseRepository.save(depense);
    }
    public Depense getDepense(Integer id) {
        return depenseRepository.findById(id).orElse(null);
    }

    public List<Depense> getAllDepenses() {
        return depenseRepository.findAll();
    }

    public List<Depense> getDepensesTicketByCustomerAndDate(int customerId , LocalDateTime dateTime) {
        return depenseRepository.findDepenseTicketByCustomerAndDate(customerId , dateTime);
    }

    public List<Depense> getDepensesLeadByCustomerAndDate(int customerId , LocalDateTime dateTime) {
        return depenseRepository.findDepenseLeadByCustomerAndDate(customerId , dateTime);
    }

    public double sommeDepensesTicket(int customerId , LocalDateTime dateTime) {
        double valiny =  0.0;
        List<Depense> depenses = this.getDepensesTicketByCustomerAndDate(customerId , dateTime);
        for (Depense depense : depenses) {
            valiny += depense.getMontant().doubleValue();
        }
        return valiny;
    }

    public double sommeDepensesLead(int customerId , LocalDateTime dateTime) {
        double valiny =  0.0;
        List<Depense> depenses = this.getDepensesLeadByCustomerAndDate(customerId , dateTime);
        for (Depense depense : depenses) {
            valiny += depense.getMontant().doubleValue();
        }
        return valiny;
    }

    public double sommeDepenses(int customerId , LocalDateTime dateTime) {
        return this.sommeDepensesTicket(customerId , dateTime) + sommeDepensesLead(customerId , dateTime);
    }

    @Transactional
    public void deleteByTicket(Ticket ticket) {
        depenseRepository.deleteByTicket(ticket);
    }

    @Transactional
    public void deleteByLead(Lead lead) {
        depenseRepository.deleteByLead(lead);
    }

   public List<Ticket> getAllTicketsByDate(LocalDateTime dateTime) {
        List<Depense> tickets = depenseRepository.findDepenseTicketByDate(dateTime);
        List<Ticket> ticketList = new ArrayList<Ticket>();
        for (Depense depense : tickets) {
            Ticket ticket = depense.getTicket();
            ticket.setMontantDepense(depenseRepository.findLastValueTicket(ticket.getTicketId() , dateTime).get(0).getMontant().toString());
            ticketList.add(ticket);
        }
        return ticketList;
   }

   public List<Lead> getAllLeadsByDate(LocalDateTime dateTime) {
        List<Depense> leads = depenseRepository.findDepenseLeadByDate(dateTime);
        List<Lead> leadList = new ArrayList<>();
        for (Depense depense : leads) {
            Lead lead = depense.getLead();
            lead.setMontantDepense(depenseRepository.findLastValueLead(lead.getLeadId() , dateTime).get(0).getMontant().toString());
            leadList.add(lead);
        }
        return leadList;
   }

   public List<TicketModel> getAllTicketModel(LocalDateTime dateTime) {
        List<TicketModel> ticketModels = new ArrayList<>();
        List<Ticket> tickets = this.getAllTicketsByDate(dateTime);

        for (Ticket ticket : tickets) {
            TicketModel ticketModel = new TicketModel();
            ticketModel.setIdTicket(ticket.getTicketId());
            ticketModel.setCustomerName(ticket.getCustomer().getName());
            ticketModel.setSubject(ticket.getSubject());
            ticketModel.setDateTime(ticket.getCreatedAt());
            ticketModel.setMontantDepense(Double.parseDouble(ticket.getMontantDepense()));

            ticketModels.add(ticketModel);
        }

       System.out.println("Ticket List size :" + tickets.size());

        return ticketModels;
   }

   public List<LeadModel> getAllLeadModel(LocalDateTime dateTime) {
        List<LeadModel> leadModels = new ArrayList<>();
        List<Lead> leads = this.getAllLeadsByDate(dateTime);
        for (Lead lead : leads) {
            LeadModel leadModel = new LeadModel();
            leadModel.setIdLead(lead.getLeadId());
            leadModel.setCustomerName(lead.getCustomer().getName());
            leadModel.setLeadName(lead.getName());
            leadModel.setDateTime(lead.getCreatedAt());
            leadModel.setMontantDepense(Double.parseDouble(lead.getMontantDepense()));

            leadModels.add(leadModel);
        }
        return leadModels;
   }

   public Depense getDepenseByIdTicket(int idTicket) {
        return depenseRepository.findDepenseByIdTicket(idTicket);
   }

   public Depense getDepenseByIdLead(int idLead) {
        return depenseRepository.findDepenseByIdLead(idLead);
   }




    


}
