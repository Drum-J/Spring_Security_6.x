package study.springsecurity6.users.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import study.springsecurity6.entity.Account;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {

    private Long id;
    private String username;
    private String password;
    private int age;
    private List<String> roles;

    public AccountDto(Account account) {
        this.id = account.getId();
        this.username = account.getUsername();
        this.password = account.getPassword();
        this.age = account.getAge();
    }

    public Account toEntity() {
        return Account.builder()
                .id(this.id)
                .username(this.username)
                .password(this.password)
                .age(this.age)
                .build();
    }
}
