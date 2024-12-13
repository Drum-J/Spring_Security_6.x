package study.springsecurity6.security.listener;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import study.springsecurity6.admin.repository.RoleRepository;
import study.springsecurity6.entity.Account;
import study.springsecurity6.entity.Role;
import study.springsecurity6.users.repository.AccountRepository;

import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private boolean alreadySetup = false;
    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        if (alreadySetup) {
            return;
        }

        setupData();
        alreadySetup = true;
    }

    private void setupData() {
        HashSet<Role> roles = new HashSet<>();
        Role adminRole = createRoleIfNotFound("ROLE_ADMIN", "관리자");
        roles.add(adminRole);
        createUserIfNotFound("admin", "admin@admin.com", "pass", roles);
    }

    public Role createRoleIfNotFound(String roleName, String roleDesc) {
        Role role = roleRepository.findByRoleName(roleName);

        if (role == null) {
            role = Role.builder()
                    .roleName(roleName)
                    .roleDesc(roleDesc)
                    .isExpression("N")
                    .build();
        }
        return roleRepository.save(role);
    }

    public void createUserIfNotFound(final String userName, final String email, final String password, Set<Role> roleSet) {
        Account account = accountRepository.findByUsername(userName).orElseGet(
                ()-> Account.builder()
                        .username(userName)
                        .password(passwordEncoder.encode(password))
                        .userRoles(roleSet)
                        .build()
        );


        accountRepository.save(account);
    }
}
