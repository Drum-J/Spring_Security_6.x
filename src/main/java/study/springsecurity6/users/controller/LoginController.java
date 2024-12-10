package study.springsecurity6.users.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import study.springsecurity6.entity.Account;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "exception", required = false) String exception,
                        Model model) {
        model.addAttribute("error", error);
        model.addAttribute("exception", exception);

        return "login/login";
    }

    @GetMapping("/api/login")
    public String restLogin() {
        return "rest/login";
    }

    @GetMapping("/signup")
    public String signup() {
        // Enum 값 전달
        // model.addAttribute("roles", AccountRole.values());
        return "login/signup";
    }

    /**
     * 간단하게는 GET 방식도 가능하나 POST 방식을 더 권장한다.
     */
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContextHolderStrategy().getContext().getAuthentication();

        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return "redirect:/login";
    }

    @GetMapping("/denied")
    public String accessDenied(@RequestParam(value = "exception",required = false) String exception,
                               @AuthenticationPrincipal Account account,
                               Model model) {

        model.addAttribute("username", account.getUsername());
        model.addAttribute("exception", exception);

        return "login/denied";
    }
}
