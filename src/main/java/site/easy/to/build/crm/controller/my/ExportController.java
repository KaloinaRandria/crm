package site.easy.to.build.crm.controller.my;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import site.easy.to.build.crm.entity.Customer;
import site.easy.to.build.crm.entity.Lead;
import site.easy.to.build.crm.entity.Ticket;
import site.easy.to.build.crm.entity.my.Budget;
import site.easy.to.build.crm.service.customer.CustomerServiceImpl;
import site.easy.to.build.crm.service.lead.LeadServiceImpl;
import site.easy.to.build.crm.service.my.BudgetService;
import site.easy.to.build.crm.service.ticket.TicketServiceImpl;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/employee/customer")
public class ExportController {

    @Autowired
    CustomerServiceImpl customerService;

    @Autowired
    LeadServiceImpl leadService;

    @Autowired
    TicketServiceImpl ticketService;

    @Autowired
    BudgetService budgetService;

    @PostMapping("/export-file")
    public String exportFile(Model model , @RequestParam("customerId") int customerId) {
        List<Customer> customers = customerService.findAll();

        List<Lead> leadList = leadService.getLeadByCustomerId(customerId);
        List<Ticket> ticketList = ticketService.getByCustomerId(customerId);
        List<Budget> budgetList = budgetService.getBudgetByCustomerAndDate(customerId , LocalDateTime.now());

        Lead.listToString(leadList);
        Ticket.listToString(ticketList);
        Budget.listToString(budgetList);


        model.addAttribute("customers", customers);
        System.out.println("Export file " + Lead.listToString(leadList));
        return "customer/all-customers";
    }
}
