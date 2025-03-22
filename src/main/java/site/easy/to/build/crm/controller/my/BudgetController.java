package site.easy.to.build.crm.controller.my;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import site.easy.to.build.crm.entity.Customer;
import site.easy.to.build.crm.entity.User;
import site.easy.to.build.crm.entity.my.Budget;
import site.easy.to.build.crm.service.customer.CustomerService;
import site.easy.to.build.crm.service.user.UserService;
import site.easy.to.build.crm.util.AuthenticationUtils;
import site.easy.to.build.crm.util.AuthorizationUtil;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/manager/budget")
public class BudgetController {
    private final CustomerService customerService;
    private final AuthenticationUtils authenticationUtils;
    private final UserService userService;

    @Autowired
    public BudgetController(CustomerService customerService, AuthenticationUtils authenticationUtils, UserService userService) {
        this.customerService = customerService;
        this.authenticationUtils = authenticationUtils;
        this.userService = userService;
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
}
