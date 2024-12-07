package study.springsecurity6;

import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    AuthenticationTrustResolverImpl trustResolver = new AuthenticationTrustResolverImpl();

    @GetMapping("/")
    public String index() {
        Authentication authentication
                = SecurityContextHolder.getContextHolderStrategy().getContext().getAuthentication();
        return trustResolver.isAnonymous(authentication) ? "anonymous" : "authenticated";
    }

    @GetMapping("/home")
    public String home() {
        return "HOME";
    }

    @GetMapping("/user")
    public User user(@AuthenticationPrincipal User user) {
        return user;
    }

    @GetMapping("/currentUser")
    public User currentUser(@CurrentUser User user) {
        return user;
    }

    /**
     * User 객체 안에 있는 username 을 바로 가져온다.
     * 필드 이름을 틀리면 에러가 발생한다.
     */
    @GetMapping("/username")
    public String username(@AuthenticationPrincipal(expression = "username") String username) {
        return username;
    }

    @GetMapping("/currentUsername")
    public String currentUsername(@CurrentUsername String username) {
        return username;
    }

    @GetMapping("/db")
    public String db() {
        return "db";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }
}
