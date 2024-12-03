package study.springsecurity6;

import lombok.Getter;

@Getter
public enum UserRole {
    USER("USER","ROLE_USER"),
    MANAGER("MANAGER","ROLE_MANAGER"),
    ADMIN("ADMIN","ROLE_ADMIN");

    private final String role;
    private final String authority;

    UserRole(String role,String authority) {
        this.role = role;
        this.authority = authority;
    }

    public static UserRole getEnum(String role) {
        for (UserRole userRole : values()) {
            if (role.equals(userRole.role)) {
                return userRole;
            }
        }

        return null;
    }
}
