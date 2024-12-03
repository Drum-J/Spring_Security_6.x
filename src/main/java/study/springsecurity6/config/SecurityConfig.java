package study.springsecurity6.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import study.springsecurity6.UserRole;

import static study.springsecurity6.UserRole.*;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/user").hasRole(USER.getRole())
                        .requestMatchers("/manager").hasRole(MANAGER.getRole())
                        .requestMatchers("/admin").hasAuthority(ADMIN.getAuthority())
                        .anyRequest().authenticated())
                .formLogin(Customizer.withDefaults())
                .build();
    }

    @Bean
    public RoleHierarchy roleHierarchy() {
        return RoleHierarchyImpl.fromHierarchy(
                ADMIN.getAuthority() +" > "+ MANAGER.getAuthority() +"\n" +
                MANAGER.getAuthority() +" > "+ USER.getAuthority() +"\n" +
                USER.getAuthority() +" > "+ ANONYMOUS.getAuthority()
        );
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withUsername("seungho").password("{noop}test123").roles(USER.getRole()).build();
        UserDetails manager = User.withUsername("manager").password("{noop}1111").roles(MANAGER.getRole()).build();
        UserDetails admin = User.withUsername("admin").password("{noop}1111").roles(ADMIN.getRole()).build();

        return new InMemoryUserDetailsManager(user, manager, admin);
    }
}
