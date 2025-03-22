package site.easy.to.build.crm.service.my;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.easy.to.build.crm.repository.my.BudgetRepository;

@Service
public class BudgetService {
    @Autowired
    BudgetRepository budgetRepository;

    
}
