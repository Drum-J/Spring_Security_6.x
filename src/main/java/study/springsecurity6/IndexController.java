package study.springsecurity6;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class IndexController {

    private final DataService dataService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/home")
    public String home() {
        return "HOME";
    }

    @GetMapping("/user")
    public String user() {
        return dataService.getUser();
    }

    @GetMapping("/owner")
    public Account admin(@RequestParam("name") String name) {
        return dataService.getOwner(name);
    }
}
