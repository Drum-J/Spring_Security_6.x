package study.springsecurity6.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import study.springsecurity6.entity.Role;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRoleName(String name);

    @Override
    void delete(Role role);

    @Query("select r from Role r where r.isExpression = 'N'")
    List<Role> findAllRolesWithoutExpression();
}
