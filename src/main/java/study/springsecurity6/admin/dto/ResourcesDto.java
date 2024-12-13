package study.springsecurity6.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import study.springsecurity6.entity.Resources;
import study.springsecurity6.entity.Role;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResourcesDto{
    private Long id;
    private String resourceName;
    private String httpMethod;
    private int orderNum;
    private String resourceType;
    private String roleName;
    private Set<Role> roleSet;

    public ResourcesDto(Resources resources) {
        this.id = resources.getId();
        this.resourceName = resources.getResourceName();
        this.httpMethod = resources.getHttpMethod();
        this.orderNum = resources.getOrderNum();
        this.resourceType = resources.getResourceType();
        this.roleSet = resources.getRoleSet();
    }

    public Resources toEntity() {
        return Resources.builder()
                .id(this.id)
                .resourceName(this.resourceName)
                .httpMethod(this.httpMethod)
                .orderNum(this.orderNum)
                .resourceType(this.resourceType)
                .roleSet(this.roleSet)
                .build();
    }
}
