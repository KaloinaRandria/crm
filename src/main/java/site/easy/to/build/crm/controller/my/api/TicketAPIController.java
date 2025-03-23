package site.easy.to.build.crm.controller.my.api;

import org.springframework.web.bind.annotation.*;
import site.easy.to.build.crm.entity.Ticket;
import site.easy.to.build.crm.entity.my.Depense;
import site.easy.to.build.crm.entity.my.api.TicketModel;
import site.easy.to.build.crm.service.my.DepenseService;
import site.easy.to.build.crm.service.ticket.TicketService;


import java.math.BigDecimal;
import java.time.LocalDateTime;

import java.util.List;

@RestController
@RequestMapping("/api/ticket")
public class TicketAPIController {
    private final DepenseService depenseService; // Injection du service
    private final TicketService ticketService;

    public TicketAPIController(DepenseService depenseService , TicketService ticketService) {
        this.depenseService = depenseService;
        this.ticketService = ticketService;
    }

    @GetMapping("/all")
    public List<TicketModel> getAllTickets(@RequestParam("dateTime") String dateTimeStr) {
       LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr);
        return depenseService.getAllTicketModel(dateTime);
    }

    // Endpoint pour supprimer un ticket et ses dépenses associées
    @PostMapping("/delete")
    public String deleteTicket(@RequestParam("idTicket") int idTicket) {
        // Récupérer le ticket en utilisant l'ID
        Ticket ticket = ticketService.findByTicketId(idTicket);

        if (ticket != null) {
            // Supprimer les dépenses associées au ticket
            depenseService.deleteByTicket(ticket);

            // Supprimer le ticket lui-même
            ticketService.delete(ticket);

            return "Ticket et ses dépenses associées ont été supprimés avec succès.";
        } else {
            return "Ticket introuvable.";
        }
    }

    @PostMapping("/update")
    public String updateTicket(@RequestParam("idTicket") int idTicket ,@RequestParam("newMontant") double newMontant) {
        Depense depense = depenseService.getDepenseByIdTicket(idTicket);
        depense.setMontant(new BigDecimal(newMontant));
        depense.setDate(LocalDateTime.now());

        depenseService.saveDepense(depense);

        return "Ticket Updater";
    }

}
