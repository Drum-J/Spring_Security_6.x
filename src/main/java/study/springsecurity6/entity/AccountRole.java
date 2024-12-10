package study.springsecurity6.entity;

import lombok.Getter;

@Getter
public enum AccountRole {
    USER("ROLE_USER"),
    MANAGER("ROLE_MANAGER"),
    ADMIN("ROLE_ADMIN");

    private final String authority;

    AccountRole(String authority) {
        this.authority = authority;
    }
}
