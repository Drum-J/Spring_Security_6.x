package study.springsecurity6.users.dto;

import org.springframework.security.crypto.password.PasswordEncoder;
import study.springsecurity6.entity.Account;
import study.springsecurity6.entity.AccountRole;

public record SignupDto(String username, String password, int age, AccountRole role) {

    public Account toEntity(PasswordEncoder encoder) {
        return Account.builder()
                .username(this.username)
                .password(encoder.encode(this.password))
                .age(this.age)
                .role(this.role)
                .build();
    }
}
