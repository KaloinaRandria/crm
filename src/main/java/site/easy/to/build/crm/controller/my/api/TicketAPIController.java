package site.easy.to.build.crm.controller.my.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import site.easy.to.build.crm.entity.my.api.TicketModel;
import site.easy.to.build.crm.service.my.DepenseService;


import java.time.LocalDateTime;

import java.util.List;

@RestController
@RequestMapping("/api/ticket")
public class TicketAPIController {
    private final DepenseService depenseService; // Injection du service

    public TicketAPIController(DepenseService depenseService) {
        this.depenseService = depenseService;
    }

    @GetMapping("/all")
    public List<TicketModel> getAllTickets(@RequestParam("dateTime") String dateTimeStr) {
       LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr);
        return depenseService.getAllTicketModel(dateTime);
    }
}
