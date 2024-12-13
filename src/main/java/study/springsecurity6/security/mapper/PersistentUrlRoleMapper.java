package study.springsecurity6.security.mapper;

import lombok.RequiredArgsConstructor;
import study.springsecurity6.admin.repository.ResourcesRepository;
import study.springsecurity6.entity.Resources;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class PersistentUrlRoleMapper implements UrlRoleMapper{

    private final LinkedHashMap<String, String> urlRoleMappings = new LinkedHashMap<>();
    private final ResourcesRepository resourcesRepository;

    @Override
    public Map<String, String> getUrlRoleMappings() {
        urlRoleMappings.clear();

        List<Resources> resourcesList = resourcesRepository.findAllResources();
        resourcesList.forEach(resources -> {
            resources.getRoleSet().forEach(role -> {
                urlRoleMappings.put(resources.getResourceName(), role.getRoleName());
            });
        });

        return urlRoleMappings;
    }
}
