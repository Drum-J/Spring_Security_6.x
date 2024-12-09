package study.springsecurity6.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.springsecurity6.entity.Account;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByUsername(String username);
}
