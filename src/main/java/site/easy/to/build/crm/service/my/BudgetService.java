package site.easy.to.build.crm.service.my;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.easy.to.build.crm.entity.my.Budget;
import site.easy.to.build.crm.repository.my.BudgetRepository;

import java.util.List;

@Service
public class BudgetService {
    @Autowired
    BudgetRepository budgetRepository;

    public void saveBudget(Budget budget) {
        budgetRepository.save(budget);
    }

    public List<Budget> getAllBudgets() {
        return budgetRepository.findAll();
    }

    public Budget getBudgetById(Integer id) {
        return budgetRepository.findById(id).orElse(null);
    }
}
