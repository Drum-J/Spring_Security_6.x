package study.springsecurity6;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("hasRole('ADMIN')")
public class RoleController {

    @GetMapping("/userRole")
    @PreAuthorize("hasRole('USER')")
    public String user() {
        return "userRole";
    }

    @GetMapping("/adminRole")
    public String admin() {
        return "adminRole";
    }
}
