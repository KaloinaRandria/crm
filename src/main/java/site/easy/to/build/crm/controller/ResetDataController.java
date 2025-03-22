package site.easy.to.build.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import site.easy.to.build.crm.service.DataManager.ResetData;

@Controller
public class ResetDataController {
    @Autowired
    ResetData resetData;

    @GetMapping("/manager/delete-data")
    public String supprimerDonnee() {
        resetData.initBase();
        return "redirect:/";
    }

}
