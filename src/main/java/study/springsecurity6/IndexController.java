package study.springsecurity6;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping("/csrf")
    public String csrf() {
        return "CSRF 적용됨";
    }
}
