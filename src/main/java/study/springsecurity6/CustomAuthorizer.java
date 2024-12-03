package study.springsecurity6;

import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.stereotype.Component;

@Component("myAuthorizer")
public class CustomAuthorizer {
    public boolean isUser(MethodSecurityExpressionOperations root) {
        return root.hasRole("USER");
    }
}
