package study.springsecurity6.config;

import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import study.springsecurity6.CustomAuthenticationProvider;
import study.springsecurity6.CustomAuthenticationProvider2;

//@Configuration
//@EnableWebSecurity
public class SecurityConfig {

    //@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder managerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        // 일반 객체로 생성하는 방법
        managerBuilder.authenticationProvider(new CustomAuthenticationProvider());
        managerBuilder.authenticationProvider(new CustomAuthenticationProvider2());

        return http
                .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
                .formLogin(Customizer.withDefaults())
                // 위의 managerBuilder.authenticationProvider() 와 같은 방식임
                //.authenticationProvider(new CustomAuthenticationProvider())
                //.authenticationProvider(new CustomAuthenticationProvider2())
                .build();
    }

    //@Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User
                .withUsername("seungho")
                .password("{noop}test123")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user);
    }
}
