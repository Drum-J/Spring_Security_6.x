package study.springsecurity6.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.springsecurity6.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
