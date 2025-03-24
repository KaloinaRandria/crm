package site.easy.to.build.crm.controller.my.api;

import org.springframework.web.bind.annotation.*;
import site.easy.to.build.crm.entity.Lead;
import site.easy.to.build.crm.entity.Ticket;
import site.easy.to.build.crm.entity.my.Depense;
import site.easy.to.build.crm.entity.my.api.LeadModel;
import site.easy.to.build.crm.entity.my.api.TicketModel;
import site.easy.to.build.crm.service.lead.LeadService;
import site.easy.to.build.crm.service.my.DepenseService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/lead")
public class LeadAPIController {
    private final DepenseService depenseService; // Injection du service
    private final LeadService leadService;

    public LeadAPIController(DepenseService depenseService, LeadService leadService) {
        this.depenseService = depenseService;
        this.leadService = leadService;
    }

    @GetMapping("/all")
    public List<LeadModel> getAllLeads(@RequestParam("dateTime") String dateTimeStr) {
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr);
        return depenseService.getAllLeadModel(dateTime);
    }

    @PostMapping("/delete")
    public String deleteLead(@RequestParam("idLead") int idLead) {
        // Récupérer le ticket en utilisant l'ID
        Lead lead = leadService.findByLeadId(idLead);
        if (lead != null) {

            depenseService.deleteByLead(lead);

            leadService.delete(lead);

            return "Lead et ses dépenses associées ont été supprimés avec succès.";
        } else {
            return "Lead introuvable.";
        }
    }

    @PostMapping("/update")
    public String updateLead(@RequestParam("idLead") int idLead ,@RequestParam("newMontant") double newMontant) {
        Depense depense = depenseService.getDepenseByIdLead(idLead);
        depense.setMontant(new BigDecimal(newMontant));
        depense.setDate(LocalDateTime.now());

        depenseService.saveDepense(depense);

        return "Lead Updater";
    }


}
