package study.springsecurity6.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.springsecurity6.entity.RoleHierarchy;

public interface RoleHierarchyRepository extends JpaRepository<RoleHierarchy, Long> {
}
