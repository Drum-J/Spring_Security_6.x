package study.springsecurity6.security.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import study.springsecurity6.entity.Account;
import study.springsecurity6.users.dto.AccountDto;
import study.springsecurity6.users.repository.AccountRepository;

import java.util.List;

@Slf4j
@Service("userDetailsService")
@RequiredArgsConstructor
public class FormUserDetailsService implements UserDetailsService {

    private final AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("로그인 시도 ID : {}", username);
        Account account = accountRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("해당 유저를 찾을 수 없습니다"));
        AccountDto accountDto = new AccountDto(account);

        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(account.getRole().getAuthority()));

        return new AccountContext(accountDto, authorities);
    }
}
