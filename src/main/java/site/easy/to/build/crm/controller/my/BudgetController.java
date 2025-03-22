package site.easy.to.build.crm.controller.my;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import site.easy.to.build.crm.service.customer.CustomerService;

@Controller
@RequestMapping("/manager/budget")
public class BudgetController {
    private final CustomerService customerService;

    @Autowired
    public BudgetController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/show-budget-page")
    public String showAddBudgetForm() {
        
        return "";
    }
}
