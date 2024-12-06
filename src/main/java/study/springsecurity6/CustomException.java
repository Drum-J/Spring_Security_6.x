package study.springsecurity6;


import org.springframework.security.core.AuthenticationException;

public class CustomException extends AuthenticationException {
    public CustomException(String msg) {
        super(msg);
    }
}
