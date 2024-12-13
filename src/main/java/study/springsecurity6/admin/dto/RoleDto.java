package study.springsecurity6.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import study.springsecurity6.entity.Role;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto{
    private Long id;
    private String roleName;
    private String roleDesc;
    private String isExpression;

    public RoleDto(Role role) {
        this.id = role.getId();
        this.roleName = role.getRoleName();
        this.roleDesc = role.getRoleDesc();
        this.isExpression = role.getIsExpression();
    }

    public Role toEntity() {
        return Role.builder()
                .id(this.id)
                .roleName(this.roleName)
                .roleDesc(this.roleDesc)
                .isExpression(this.isExpression)
                .build();
    }
}
