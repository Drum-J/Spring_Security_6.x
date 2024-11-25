package study.springsecurity6;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    private final SecurityContextService securityContextService;

    public IndexController(SecurityContextService securityContextService) {
        this.securityContextService = securityContextService;
    }


    @GetMapping("/")
    public String index() {
        securityContextService.securityContext();

        return "index";
    }

    @GetMapping("/home")
    public String home() {
        return "HOME";
    }

    @GetMapping("/loginPage")
    public String loginPage() {
        return "login Page!";
    }

}
