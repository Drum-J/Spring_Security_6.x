package study.springsecurity6;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final HttpSessionSecurityContextRepository securityContextRepository = new HttpSessionSecurityContextRepository();

    @PostMapping("/login")
    public Authentication login(@RequestBody LoginRequestDto login, HttpServletRequest request, HttpServletResponse response) {
        UsernamePasswordAuthenticationToken token =
                UsernamePasswordAuthenticationToken.unauthenticated(login.getUsername(), login.getPassword()); //authorities 없이 만드는 스태틱 메서드
        Authentication authentication = authenticationManager.authenticate(token);

        SecurityContext securityContext = SecurityContextHolder.getContextHolderStrategy().createEmptyContext(); // 새로운 SecurityContext
        securityContext.setAuthentication(authentication); // 인증결과를 컨텍스트에 저장

        SecurityContextHolder.getContextHolderStrategy().setContext(securityContext); // 컨텍스트를 스레드 로컬에 저장

        securityContextRepository.saveContext(securityContext, request, response);

        return authentication;
    }
}
