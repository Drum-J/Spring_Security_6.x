package study.springsecurity6.security.config;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import study.springsecurity6.security.handler.FormAccessDeniedHandler;

import static study.springsecurity6.entity.AccountRole.ADMIN;
import static study.springsecurity6.entity.AccountRole.MANAGER;
import static study.springsecurity6.entity.AccountRole.USER;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthenticationProvider authenticationProvider;
    private final AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> authenticationDetailsSource;
    private final AuthenticationSuccessHandler successHandler;
    private final AuthenticationFailureHandler failureHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        .requestMatchers("/","/signup","/login*").permitAll()
                        .requestMatchers("/user").hasRole(USER.name())
                        .requestMatchers("/manager").hasRole(MANAGER.name())
                        .requestMatchers("/admin").hasRole(ADMIN.name())
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login").permitAll()
                        .authenticationDetailsSource(authenticationDetailsSource)
                        .successHandler(successHandler)
                        .failureHandler(failureHandler)
                )
                .authenticationProvider(authenticationProvider)
                .exceptionHandling(exception -> exception
                        .accessDeniedHandler(new FormAccessDeniedHandler("/denied"))
                )
                .build();
    }

    @Bean
    public WebSecurityCustomizer configureH2ConsoleEnable() {
        return web -> web.ignoring().requestMatchers(PathRequest.toH2Console());
    }
}
