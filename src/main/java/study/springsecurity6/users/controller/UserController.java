package study.springsecurity6.users.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import study.springsecurity6.users.dto.AccountDto;
import study.springsecurity6.users.service.UserService;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public String signup(AccountDto accountDto) {
        userService.createUser(accountDto);

        return "redirect:/";
    }
}
