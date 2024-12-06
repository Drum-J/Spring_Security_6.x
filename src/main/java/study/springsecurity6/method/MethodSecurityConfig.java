package study.springsecurity6.method;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.Advisor;
import org.springframework.aop.Pointcut;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.ComposablePointcut;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;
import org.springframework.security.authorization.AuthorityAuthorizationManager;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.authorization.method.AuthorizationManagerAfterMethodInterceptor;
import org.springframework.security.authorization.method.AuthorizationManagerBeforeMethodInterceptor;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@Configuration
@EnableMethodSecurity(prePostEnabled = false)
public class MethodSecurityConfig {

    /*
    @Bean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public Advisor preAuthorize() {
        return AuthorizationManagerBeforeMethodInterceptor.preAuthorize(new MyPreAuthorizationManager());
    }

    @Bean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public Advisor postAuthorize() {
        return AuthorizationManagerAfterMethodInterceptor.postAuthorize(new MyPostAuthorizationManager());
    }
    */

    //단일 포인트컷
    @Bean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public Advisor pointCutAdvisor() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* study.springsecurity6.DataService.getUser(..))");

        AuthorityAuthorizationManager<MethodInvocation> manager = AuthorityAuthorizationManager.hasRole("USER");

        return new AuthorizationManagerBeforeMethodInterceptor(pointcut, manager);
    }

    // 다중 포인트컷
    @Bean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public Advisor multiPointCutAdvisor() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* study.springsecurity6.DataService.getUser(..))");

        AspectJExpressionPointcut pointcut2 = new AspectJExpressionPointcut();
        pointcut2.setExpression("execution(* study.springsecurity6.DataService.getOwner(..))");

        ComposablePointcut composablePointcut = new ComposablePointcut((Pointcut) pointcut);
        composablePointcut.union((Pointcut) pointcut2);

        AuthorityAuthorizationManager<MethodInvocation> manager = AuthorityAuthorizationManager.hasRole("USER");

        return new AuthorizationManagerBeforeMethodInterceptor(composablePointcut, manager);
    }

}
