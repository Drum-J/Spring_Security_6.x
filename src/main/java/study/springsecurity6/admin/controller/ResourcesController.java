package study.springsecurity6.admin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import study.springsecurity6.admin.dto.ResourcesDto;
import study.springsecurity6.admin.repository.RoleRepository;
import study.springsecurity6.admin.service.ResourcesService;
import study.springsecurity6.admin.service.RoleService;
import study.springsecurity6.entity.Resources;
import study.springsecurity6.entity.Role;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequiredArgsConstructor
public class ResourcesController {

    private final ResourcesService resourcesService;
    private final RoleRepository roleRepository;
    private final RoleService roleService;

    @GetMapping(value="/admin/resources")
    public String getResources(Model model) {
        List<Resources> resources = resourcesService.getResources();
        model.addAttribute("resources", resources);

        return "admin/resources";
    }

    @PostMapping(value="/admin/resources")
    public String createResources(ResourcesDto resourcesDto) {

        Role role = roleRepository.findByRoleName(resourcesDto.getRoleName());
        Set<Role> roles = new HashSet<>();
        roles.add(role);

        Resources resources = resourcesDto.toEntity();
        resources.setRoleSet(roles);

        resourcesService.createResources(resources);

        return "redirect:/admin/resources";
    }

    @GetMapping(value="/admin/resources/register")
    public String resourcesRegister(Model model) {

        List<Role> roleList = roleService.getRoles();
        model.addAttribute("roleList", roleList);
        List<String> myRoles = new ArrayList<>();
        model.addAttribute("myRoles", myRoles);
        ResourcesDto resources = new ResourcesDto();
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(new Role());
        resources.setRoleSet(roleSet);
        model.addAttribute("resources", resources);

        return "admin/resourcesdetails";
    }

    @GetMapping(value="/admin/resources/{id}")
    public String resourceDetails(@PathVariable String id, Model model) {

        List<Role> roleList = roleService.getRoles();
        model.addAttribute("roleList", roleList);
        Resources resources = resourcesService.getResources(Long.parseLong(id));
        List<String> myRoles = resources.getRoleSet().stream().map(Role::getRoleName).toList();
        model.addAttribute("myRoles", myRoles);

        ResourcesDto resourcesDto = new ResourcesDto(resources);
        model.addAttribute("resources", resourcesDto);

        return "admin/resourcesdetails";
    }

    @GetMapping(value="/admin/resources/delete/{id}")
    public String removeResources(@PathVariable String id) throws Exception {

        resourcesService.deleteResources(Long.parseLong(id));

        return "redirect:/admin/resources";
    }
}
