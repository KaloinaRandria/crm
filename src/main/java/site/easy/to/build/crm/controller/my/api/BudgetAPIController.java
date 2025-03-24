package site.easy.to.build.crm.controller.my.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import site.easy.to.build.crm.entity.my.api.BudgetModel;
import site.easy.to.build.crm.service.my.BudgetService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/budget")
public class BudgetAPIController {

    private final BudgetService budgetService;

    public BudgetAPIController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    @GetMapping("/all")
    public List<BudgetModel> getAllBudgets(@RequestParam("dateTime") String dateTimeStr) {
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr);
        return budgetService.getAllBudgetModels(dateTime);
    }

}
