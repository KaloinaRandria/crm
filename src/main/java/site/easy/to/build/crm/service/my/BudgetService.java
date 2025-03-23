package site.easy.to.build.crm.service.my;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.easy.to.build.crm.entity.Customer;
import site.easy.to.build.crm.entity.my.Budget;
import site.easy.to.build.crm.repository.my.BudgetRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BudgetService {
    @Autowired
    BudgetRepository budgetRepository;

    @Autowired
    DepenseService depenseService;
    @Autowired
    private TauxService tauxService;

    public void saveBudget(Budget budget) {
        budgetRepository.save(budget);
    }

    public List<Budget> getAllBudgets() {
        return budgetRepository.findAll();
    }

    public Budget getBudgetById(Integer id) {
        return budgetRepository.findById(id).orElse(null);
    }

    public List<Budget> getBudgetByCustomerAndDate(int customerId , LocalDateTime dateTime) {
        return budgetRepository.findBudgetByCustomerAndDate(customerId , dateTime);
    }

    public double sommeBudget(int customerId , LocalDateTime dateTime) {
        double valiny  = 0.0;
        List<Budget> budgets = getBudgetByCustomerAndDate(customerId , dateTime);
        for (Budget budget : budgets) {
            valiny += budget.getMontant().doubleValue();
        }
        return valiny;
    }


    public double pourcentageBudget(int customerId , LocalDateTime dateTime , double montantDepense) {
        double sommeBudget = this.sommeBudget(customerId , dateTime);
        double sommeDepense = this.depenseService.sommeDepenses(customerId , dateTime) + montantDepense;
        double pourcentage = tauxService.getTaux();

        System.out.println("Budget somme: " + sommeBudget);
        System.out.println("Depense somme: " + sommeDepense);

        double valiny = (sommeDepense * 100) / sommeBudget;

        if (valiny >= pourcentage) {
            System.out.println("Pourcentage: " + valiny);
            return valiny;
        }
        System.out.println("Pourcentage: " + valiny);
        return 0.0;
    }




}
