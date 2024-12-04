package study.springsecurity6.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> auth
                        // 여기서는 grantedAuthorityDefaults() 에서 설정한대로 MYPREFIX_ 가 붙음
                        .requestMatchers("/user").hasRole("USER")
                        .anyRequest().authenticated())
                .formLogin(Customizer.withDefaults())
                .build();
    }

    @Bean
    public GrantedAuthorityDefaults grantedAuthorityDefaults() {
        // ROLE_ 대신 MYPREFIX_ 사용
        return new GrantedAuthorityDefaults("MYPREFIX_");
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User
                .withUsername("seungho")
                .password("{noop}test123")
                .authorities("MYPREFIX_USER") // 여기서 직접 authorities 로 MYPREFIX_ 를 붙여줘야 한다.
                .build();

        return new InMemoryUserDetailsManager(user);
    }
}
