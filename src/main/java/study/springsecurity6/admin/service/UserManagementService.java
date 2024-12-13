package study.springsecurity6.admin.service;

import study.springsecurity6.entity.Account;
import study.springsecurity6.users.dto.AccountDto;

import java.util.List;

public interface UserManagementService {
    void modifyUser(AccountDto accountDto);

    List<Account> getUsers();
    AccountDto getUser(Long id);

    void deleteUser(Long idx);
}
