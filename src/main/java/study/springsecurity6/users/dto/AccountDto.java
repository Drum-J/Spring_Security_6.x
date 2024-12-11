package study.springsecurity6.users.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import study.springsecurity6.entity.Account;
import study.springsecurity6.entity.AccountRole;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {

    private Long id;
    private String username;
    private String password;
    private int age;
    private AccountRole role;

    public AccountDto(Account account) {
        this.id = account.getId();
        this.username = account.getUsername();
        this.password = account.getPassword();
        this.age = account.getAge();
        this.role = account.getRole();
    }
}
