package study.springsecurity6;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/home")
    public String home() {
        return "HOME";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/denied")
    public String denied() {
        return "denied";
    }
}
