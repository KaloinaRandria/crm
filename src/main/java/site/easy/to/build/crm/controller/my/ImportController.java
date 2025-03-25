package site.easy.to.build.crm.controller.my;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import site.easy.to.build.crm.entity.User;
import site.easy.to.build.crm.service.my.DepenseTempService;
import site.easy.to.build.crm.service.my.ImportService;
import site.easy.to.build.crm.service.user.UserService;
import site.easy.to.build.crm.util.AuthenticationUtils;

import java.io.File;
import java.sql.SQLException;

@Controller
@RequestMapping("/manager/import")
public class ImportController {
    private final ImportService importService;
    private final DepenseTempService depenseTempService;
    private final AuthenticationUtils authenticationUtils;
    private final UserService userService;
    public ImportController(ImportService importService , DepenseTempService depenseTempService , AuthenticationUtils authenticationUtils , UserService userService) {
        this.importService = importService;
        this.depenseTempService = depenseTempService;
        this.authenticationUtils = authenticationUtils;
        this.userService = userService;
    }



    @GetMapping("/show-import-csv")
    public String showImportCSVForm() {

        return "my/import-csv";
    }

    @PostMapping("/insert-csv")
    public String importCSVCustomer(@RequestParam("file1") MultipartFile customerFile , @RequestParam("file2") MultipartFile ticketLeadFile, @RequestParam("file3") MultipartFile budgetFile,
                                    Authentication authentication , Model model) throws Exception {
        int userId =  authenticationUtils.getLoggedInUserId(authentication);
        User loggedInUser = userService.findById(userId);

        if (customerFile.isEmpty() || ticketLeadFile.isEmpty() || budgetFile.isEmpty()) {
            model.addAttribute("error", "File required");
        }


        String absolutePath = "E:\\dev (ITUniversity)\\L3\\S6\\EVALUATION\\data";
        try {

            importService.importAllCSV(absolutePath + File.separator + customerFile.getOriginalFilename() ,
                    absolutePath + File.separator + budgetFile.getOriginalFilename(),
                    absolutePath + File.separator + ticketLeadFile.getOriginalFilename() ,
                    loggedInUser);

            model.addAttribute("success", "Donnees inserer avec succes");
        } catch (Exception e) {
                model.addAttribute("error", e.getMessage());
            return "my/import-csv";
        }

        return "my/import-csv";
    }
}
