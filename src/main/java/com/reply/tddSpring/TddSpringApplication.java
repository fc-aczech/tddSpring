package com.reply.tddSpring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.reply.tddSpring")
public class TddSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(TddSpringApplication.class, args);
    }

}
