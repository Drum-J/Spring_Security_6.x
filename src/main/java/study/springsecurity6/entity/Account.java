package study.springsecurity6.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor
public class Account {

    @Id @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String username;

    @Setter
    private String password;
    private int age;

    @ManyToMany(fetch = FetchType.LAZY, cascade={CascadeType.MERGE})
    @JoinTable(name = "account_roles", joinColumns = { @JoinColumn(name = "account_id") }, inverseJoinColumns = {
            @JoinColumn(name = "role_id") })
    @ToString.Exclude
    @Setter
    private Set<Role> userRoles = new HashSet<>();

    /*
    @Enumerated(value = EnumType.STRING)
    private AccountRole role;
    */

    @Builder
    public Account(Long id, String username, String password,int age, Set<Role> userRoles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.age = age;
        this.userRoles = userRoles;
    }
}
