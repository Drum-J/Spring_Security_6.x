package study.springsecurity6.users.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import study.springsecurity6.users.dto.SignupDto;
import study.springsecurity6.users.service.UserService;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public String signup(SignupDto signupDto) {
        userService.createUser(signupDto);

        return "redirect:/";
    }
}
