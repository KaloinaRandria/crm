package site.easy.to.build.crm.service.my;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.easy.to.build.crm.entity.my.Depense;
import site.easy.to.build.crm.repository.my.DepenseRepository;

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

}
