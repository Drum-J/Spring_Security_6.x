package study.springsecurity6.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@NoArgsConstructor(access = PROTECTED)
public class Account {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String username;
    private String password;
    private int age;

    @Enumerated(value = EnumType.STRING)
    private AccountRole role;

    @Builder
    public Account(String username, String password,int age, AccountRole role) {
        this.username = username;
        this.password = password;
        this.age = age;
        this.role = role;
    }
}
