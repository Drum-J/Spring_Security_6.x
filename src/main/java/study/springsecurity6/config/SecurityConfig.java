package study.springsecurity6.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.csrf.XorCsrfTokenRequestAttributeHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // 세션에 토큰 저장. 명시하지 않아도 기본적으로 세션에 토큰을 저장한다.
        HttpSessionCsrfTokenRepository csrfTokenRepository = new HttpSessionCsrfTokenRepository();
        // 쿠키에 토큰 저장.
        //CookieCsrfTokenRepository csrfTokenRepository = new CookieCsrfTokenRepository();

        XorCsrfTokenRequestAttributeHandler csrfHandler = new XorCsrfTokenRequestAttributeHandler();
        // 지연 로딩을 사용하지 않겠다.
        csrfHandler.setCsrfRequestAttributeName(null);

        return http
                .csrf(csrf -> csrf
                        // 둘 중 하나 만 사용
                        .csrfTokenRepository(csrfTokenRepository)
                        // 자바스크립트에서 CSRF 토큰을 읽을 수 있게 허용
                        //.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                        .csrfTokenRequestHandler(csrfHandler)
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/csrf","/csrfToken").permitAll()
                        .anyRequest().authenticated())
                .formLogin(Customizer.withDefaults())
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User
                .withUsername("seungho")
                .password("{noop}test123")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user);
    }
}
