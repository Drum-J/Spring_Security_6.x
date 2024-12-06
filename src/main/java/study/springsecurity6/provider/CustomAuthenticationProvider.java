package study.springsecurity6.provider;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.event.AuthenticationFailureProviderNotFoundEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

//@Component
//@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    //private final ApplicationContext applicationContext;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (!authentication.getName().equals("seungho")) {

            /*
            applicationContext.publishEvent(
                    new AuthenticationFailureProviderNotFoundEvent(
                            authentication,
                            new BadCredentialsException("BadCredentialsException")
                    )
            );
            */

            throw new BadCredentialsException("BadCredentialsException");
        }
        UserDetails user = User.withUsername("seungho").password("{noop}test123").roles("USER").build();

        return new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
