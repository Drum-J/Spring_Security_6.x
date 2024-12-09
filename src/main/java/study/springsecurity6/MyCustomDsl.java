package study.springsecurity6;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter;

public class MyCustomDsl extends AbstractHttpConfigurer<MyCustomDsl, HttpSecurity> {

    private boolean flag;

    @Override
    public void init(HttpSecurity http) throws Exception {
        super.init(http);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        MyCustomFilter myCustomDslFilter = new MyCustomFilter();
        myCustomDslFilter.setFlag(flag);

        http.addFilterAfter(myCustomDslFilter, SecurityContextHolderAwareRequestFilter.class);
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public static MyCustomDsl myCustomDsl() {
        return new MyCustomDsl();
    }
}
