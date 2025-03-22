package site.easy.to.build.crm.controller.my;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import site.easy.to.build.crm.entity.Customer;
import site.easy.to.build.crm.entity.User;
import site.easy.to.build.crm.entity.my.Budget;
import site.easy.to.build.crm.service.customer.CustomerService;
import site.easy.to.build.crm.service.my.BudgetService;
import site.easy.to.build.crm.service.user.UserService;
import site.easy.to.build.crm.util.AuthenticationUtils;
import site.easy.to.build.crm.util.AuthorizationUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/manager/budget")
public class BudgetController {
    private final CustomerService customerService;
    private final AuthenticationUtils authenticationUtils;
    private final UserService userService;
    private final BudgetService budgetService;

    @Autowired
    public BudgetController(CustomerService customerService, AuthenticationUtils authenticationUtils, UserService userService, BudgetService budgetService) {
        this.customerService = customerService;
        this.authenticationUtils = authenticationUtils;
        this.userService = userService;
        this.budgetService = budgetService;
    }

    @GetMapping("/show-budget-page")
    public String showAddBudgetForm(Model model , Authentication authentication) {
        int userId =  authenticationUtils.getLoggedInUserId(authentication);
        User loggedInUser = userService.findById(userId);

        if(loggedInUser.isInactiveUser()) {
            return "error/account-inactive";
        }
        List<Customer> customers = customerService.findAll();
        model.addAttribute("customers",customers);
        model.addAttribute("budget",new Budget());

        return "my/add-budget";
    }

    @PostMapping("/insert-budget")
    public String addBudgetCustomer( Model model, @ModelAttribute("budget") @Validated Budget budget, BindingResult bindingResult , Authentication authentication ,
                                     @RequestParam("customerId") int customerId ,@RequestParam("date")  String date ,@RequestParam(name = "montant") String montant) {
        int userId =  authenticationUtils.getLoggedInUserId(authentication);
        User loggedInUser = userService.findById(userId);

        if (bindingResult.hasErrors()) {
            List<User> employees = new ArrayList<>();
            List<Customer> customers;

            if (AuthorizationUtil.hasRole(authentication , "ROLE_MANAGER")) {
                employees = userService.findAll();
                customers = customerService.findAll();
            } else {
                employees.add(loggedInUser);
                customers = customerService.findByUserId(loggedInUser.getId());
            }

            model.addAttribute("customers",customers);
            model.addAttribute("budget" , new Budget());
            return "my/add-budget";
        }

        budget.setCustomer(customerService.findByCustomerId(customerId));
        budget.setDate(date);
        budget.setMontant(montant);

        budgetService.saveBudget(budget);

        List<Customer> customers = customerService.findAll();
        model.addAttribute("customers",customers);
        return "customer/all-customers";
    }
}
