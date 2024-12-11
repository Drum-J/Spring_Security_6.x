package study.springsecurity6.security.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import study.springsecurity6.users.dto.AccountDto;

import java.util.Collection;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class AccountContext implements UserDetails {

    private final AccountDto accountDto;
    private final List<GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return accountDto.getPassword();
    }

    @Override
    public String getUsername() {
        return accountDto.getUsername();
    }
}
