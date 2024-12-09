package study.springsecurity6.entity;

import lombok.Getter;

@Getter
public enum AccountRole {
    USER("USER","ROLE_USER"),
    MANAGER("MANAGER","ROLE_MANAGER"),
    ADMIN("ADMIN","ROLE_ADMIN");

    private final String role;
    private final String authority;

    AccountRole(String role, String authority) {
        this.role = role;
        this.authority = authority;
    }
}
