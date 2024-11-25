package study.springsecurity6;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public class CustomUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AccountDto accountDto =
                new AccountDto("seungho","{noop}test123", List.of(new SimpleGrantedAuthority("ROLE_USER")));

        return new CustomUserDetails(accountDto);
    }
}
