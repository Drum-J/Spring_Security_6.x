package study.springsecurity6.security.manager;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authorization.AuthorityAuthorizationManager;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.expression.DefaultHttpSecurityExpressionHandler;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcherEntry;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;
import study.springsecurity6.admin.repository.ResourcesRepository;
import study.springsecurity6.security.mapper.PersistentUrlRoleMapper;
import study.springsecurity6.security.service.DynamicAuthorizationService;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CustomDynamicAuthorizationManager implements AuthorizationManager<RequestAuthorizationContext> {

    private final HandlerMappingIntrospector handlerMappingIntrospector;
    private final ResourcesRepository resourcesRepository;
    private final RoleHierarchyImpl roleHierarchy;
    private static final AuthorizationDecision ACCESS = new AuthorizationDecision(true);
    List<RequestMatcherEntry<AuthorizationManager<RequestAuthorizationContext>>> mappings;

    private DynamicAuthorizationService dynamicAuthorizationService;

    @PostConstruct
    public void mapping() {
        dynamicAuthorizationService
                = new DynamicAuthorizationService(new PersistentUrlRoleMapper(resourcesRepository));
        setMapping();
    }

    /**
     * synchronized : 동시성 문제 해결
     * 동기화 해서 처리하겠다는 말인듯
     */
    public synchronized void reload() {
        this.mappings.clear();
        setMapping();
    }

    private void setMapping() {
        this.mappings = dynamicAuthorizationService.getUrlRoleMappings()
                .entrySet().stream()
                .map(entry -> new RequestMatcherEntry<>(
                        new MvcRequestMatcher(handlerMappingIntrospector, entry.getKey()),
                        customAUthorizationManager(entry.getValue()))
                )
                .collect(Collectors.toList());
                // .toList(); 이걸로 List를 만드니까 clear() 호출 시 에러가 발생한다.
                // toList() 메서드로 생성된 리스트는 내부적으로 List.of()를 사용하여 만들어집니다.
                // 이는 수정 불가능한 리스트를 반환하므로, clear(), add(), remove() 같은 수정 작업이 허용되지 않습니다.
                // 라고 한다.
    }


    @Override
    public AuthorizationDecision check(Supplier<Authentication> authentication, RequestAuthorizationContext request) {

        for (RequestMatcherEntry<AuthorizationManager<RequestAuthorizationContext>> mapping : this.mappings) {
            RequestMatcher matcher = mapping.getRequestMatcher();
            RequestMatcher.MatchResult matchResult = matcher.matcher(request.getRequest());

            if (matchResult.isMatch()) {
                AuthorizationManager<RequestAuthorizationContext> manager = mapping.getEntry();

                return manager.check(authentication,
                        new RequestAuthorizationContext(request.getRequest(), matchResult.getVariables()));
            }
        }

        return ACCESS;
    }

    private AuthorizationManager<RequestAuthorizationContext> customAUthorizationManager(String role) {

        if (role != null) {
            if (role.startsWith("ROLE")) {
                AuthorityAuthorizationManager<RequestAuthorizationContext> authorityManager
                        = AuthorityAuthorizationManager.hasAuthority(role);
                authorityManager.setRoleHierarchy(roleHierarchy);

                return authorityManager;
            } else {
                DefaultHttpSecurityExpressionHandler handler = new DefaultHttpSecurityExpressionHandler();
                handler.setRoleHierarchy(roleHierarchy);

                WebExpressionAuthorizationManager authorizationManager = new WebExpressionAuthorizationManager(role);
                authorizationManager.setExpressionHandler(handler);

                return authorizationManager;
            }
        }

        return null;
    }

    @Override
    public void verify(Supplier<Authentication> authentication, RequestAuthorizationContext object) {
        AuthorizationManager.super.verify(authentication, object);
    }
}
