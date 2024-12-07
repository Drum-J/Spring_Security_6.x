package study.springsecurity6;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AsyncService {

    @Async
    public void asyncMethod() {
        //비동기 스레드(자식 스레드)
        SecurityContext securityContext = SecurityContextHolder.getContextHolderStrategy().getContext();
        log.info("securityContext = {}", securityContext);
        log.info("Child Thread = {}", Thread.currentThread().getName());
    }
}
