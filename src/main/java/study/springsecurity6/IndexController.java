package study.springsecurity6;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;

@Slf4j
@RestController
@RequiredArgsConstructor
public class IndexController {

    private final AsyncService asyncService;

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
        return "user";
    }

    @GetMapping("/db")
    public String db() {
        return "db";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("/callable")
    public Callable<Authentication> call() {
        //메인 스레드(부모 스레드)
        SecurityContext securityContext = SecurityContextHolder.getContextHolderStrategy().getContext();
        log.info("securityContext = {}", securityContext);
        log.info("Parent Thread = {}", Thread.currentThread().getName());

        //비동기 스레드(자식 스레드)
        return () -> {
            SecurityContext securityContext1 = SecurityContextHolder.getContextHolderStrategy().getContext();
            log.info("securityContext = {}", securityContext1);
            log.info("Child Thread = {}", Thread.currentThread().getName());

            return securityContext1.getAuthentication();
        };
    }

    @GetMapping("/async")
    public Authentication async() {
        //메인 스레드(부모 스레드)
        SecurityContext securityContext = SecurityContextHolder.getContextHolderStrategy().getContext();
        log.info("securityContext = {}", securityContext);
        log.info("Parent Thread = {}", Thread.currentThread().getName());

        asyncService.asyncMethod();

        return securityContext.getAuthentication();
    }
}
