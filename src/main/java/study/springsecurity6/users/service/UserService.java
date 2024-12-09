package study.springsecurity6.users.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.springsecurity6.entity.Account;
import study.springsecurity6.users.dto.SignupDto;
import study.springsecurity6.users.repository.AccountRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final AccountRepository accountRepository;

    @Transactional
    public void createUser(SignupDto signupDto) {
        Account account = signupDto.toEntity(passwordEncoder);

        accountRepository.save(account);
    }
}
