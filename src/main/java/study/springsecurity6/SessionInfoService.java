package study.springsecurity6;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SessionInfoService {

    private final SessionRegistry sessionRegistry;

    public void sessionInfo() {
        List<Object> allPrincipals = sessionRegistry.getAllPrincipals();
        for (Object principal : allPrincipals) {
            List<SessionInformation> allSessions = sessionRegistry.getAllSessions(principal, false);

            for (SessionInformation sessionInformation : allSessions) {
                log.info("사용자 : {} / sessionId : {} / 최종 요청 시간 : {}"
                        , principal, sessionInformation.getSessionId(), sessionInformation.getLastRequest());
            }
        }
    }
}
