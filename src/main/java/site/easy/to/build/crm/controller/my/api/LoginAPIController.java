package site.easy.to.build.crm.controller.my.api;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import site.easy.to.build.crm.service.my.LoginService;

@RestController
@RequestMapping("/api/login")
public class LoginAPIController {

    private final LoginService loginService;

    public LoginAPIController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/checkSession")
    public boolean loginAPI(@RequestParam("sessionId") String sessionId , HttpServletRequest request) {
        HttpSession session = loginService.getSessionById(sessionId , request);
        return loginService.hasRoleManager(session);
    }
}
