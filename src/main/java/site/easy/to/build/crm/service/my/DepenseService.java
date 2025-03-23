package site.easy.to.build.crm.service.my;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.easy.to.build.crm.entity.Customer;
import site.easy.to.build.crm.entity.my.Depense;
import site.easy.to.build.crm.repository.my.DepenseRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DepenseService {
    @Autowired
    DepenseRepository depenseRepository;

    public void saveDepense(Depense depense) {
        depenseRepository.save(depense);
    }
    public Depense getDepense(Integer id) {
        return depenseRepository.findById(id).orElse(null);
    }

    public List<Depense> getAllDepenses() {
        return depenseRepository.findAll();
    }

    public List<Depense> getDepensesTicketByCustomerAndDate(int customerId , LocalDateTime dateTime) {
        return depenseRepository.findDepenseTicketByCustomerAndDate(customerId , dateTime);
    }

    public List<Depense> getDepensesLeadByCustomerAndDate(int customerId , LocalDateTime dateTime) {
        return depenseRepository.findDepenseLeadByCustomerAndDate(customerId , dateTime);
    }

    public double sommeDepensesTicket(int customerId , LocalDateTime dateTime) {
        double valiny =  0.0;
        List<Depense> depenses = this.getDepensesTicketByCustomerAndDate(customerId , dateTime);
        for (Depense depense : depenses) {
            valiny += depense.getMontant().doubleValue();
        }
        return valiny;
    }

    public double sommeDepensesLead(int customerId , LocalDateTime dateTime) {
        double valiny =  0.0;
        List<Depense> depenses = this.getDepensesLeadByCustomerAndDate(customerId , dateTime);
        for (Depense depense : depenses) {
            valiny += depense.getMontant().doubleValue();
        }
        return valiny;
    }

    public double sommeDepenses(int customerId , LocalDateTime dateTime) {
        return this.sommeDepensesTicket(customerId , dateTime) + sommeDepensesLead(customerId , dateTime);
    }

    

    


}
