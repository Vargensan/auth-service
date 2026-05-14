package org.fix.bill.auth.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("org.fix.bill.auth")
@EnableJpaRepositories("org.fix.bill.auth")
@EntityScan("org.fix.bill.auth")
public class AuthApp {

    public static void main(String[] args) {
        SpringApplication.run(AuthApp.class, args);
    }

}
