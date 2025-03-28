package site.easy.to.build.crm.controller.my.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import site.easy.to.build.crm.entity.my.Depense;
import site.easy.to.build.crm.entity.my.Taux;
import site.easy.to.build.crm.service.my.TauxService;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/taux")
public class TauxAPIController {
    private final TauxService tauxService;

    public TauxAPIController(TauxService tauxService) {
        this.tauxService = tauxService;
    }

    @PostMapping("/update")
    public String updateTaux(@RequestParam("newTaux") String newTaux) {
        Taux taux = tauxService.getTauxObject();
        taux.setDate(LocalDateTime.now());
        taux.setValeur(Double.parseDouble(newTaux));

        tauxService.saveTaux(taux);

        return "Taux Updated";
    }
}
