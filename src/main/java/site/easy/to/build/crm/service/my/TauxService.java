package site.easy.to.build.crm.service.my;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.easy.to.build.crm.entity.my.Taux;
import site.easy.to.build.crm.repository.my.TauxRepository;

import java.util.List;

@Service
public class TauxService {
    @Autowired
    TauxRepository tauxRepository;

    public void saveTaux(Taux taux) {
        tauxRepository.save(taux);
    }

    public List<Taux> getAllTaux() {
        return tauxRepository.findAll();
    }

    public Taux getTauxById(Integer id) {
        return tauxRepository.findById(id).orElse(null);
    }

    public double getTaux() {
        return  this.getAllTaux().get(0).getValeur();
    }
}
