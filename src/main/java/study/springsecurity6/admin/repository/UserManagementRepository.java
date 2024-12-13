package study.springsecurity6.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.springsecurity6.entity.Account;

public interface UserManagementRepository extends JpaRepository<Account, Long> {

}
