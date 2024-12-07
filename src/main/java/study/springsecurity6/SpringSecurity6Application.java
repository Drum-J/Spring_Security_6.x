package study.springsecurity6;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class SpringSecurity6Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurity6Application.class, args);
    }

}
