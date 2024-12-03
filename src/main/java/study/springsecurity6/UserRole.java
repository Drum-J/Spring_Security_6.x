package study.springsecurity6;

import lombok.Getter;

@Getter
public enum UserRole {
    ADMIN("ADMIN","ROLE_ADMIN"),
    MANAGER("MANAGER","ROLE_MANAGER"),
    USER("USER","ROLE_USER"),
    ANONYMOUS("ANONYMOUS","ROLE_ANONYMOUS");

    private final String role;
    private final String authority;

    UserRole(String role, String authority) {
        this.role = role;
        this.authority = authority;
    }
}
