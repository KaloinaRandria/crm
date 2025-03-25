package site.easy.to.build.crm.controller.my;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import site.easy.to.build.crm.service.my.ImportService;
import site.easy.to.build.crm.service.user.UserService;
import site.easy.to.build.crm.util.AuthenticationUtils;

import java.io.File;

@Controller
@RequestMapping("/manager/import")
public class ImportController {
    private final ImportService importService;
    private final AuthenticationUtils authenticationUtils;
    private final UserService userService;

    public ImportController(ImportService importService , AuthenticationUtils authenticationUtils , UserService userService) {
        this.importService = importService;
        this.authenticationUtils = authenticationUtils;
        this.userService = userService;

    }



    @GetMapping("/show-import-csv")
    public String showImportCSVForm() {
        return "my/import-csv";
    }

    @PostMapping("/insert-csv")
    public String importCSVCustomer(@RequestParam("file1") MultipartFile customerFile ,@RequestParam("file2") MultipartFile ticketLeadFile) {
        String absolutePath = "E:\\dev (ITUniversity)\\L3\\S6\\EVALUATION\\data";

        String finalFile = absolutePath + File.separator + customerFile.getOriginalFilename();
        System.out.println(finalFile);
        importService.importCSVCustomer(finalFile);
        return "redirect:/manager/import/show-import-csv";
    }

}
