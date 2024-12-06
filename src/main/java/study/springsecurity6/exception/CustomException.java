package study.springsecurity6.exception;


import org.springframework.security.core.AuthenticationException;

public class CustomException extends AuthenticationException {
    public CustomException(String msg) {
        super(msg);
    }
}
