package com.example.springbootvueproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SpringBootVueProjectApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootVueProjectApplication.class, args);
    }

}
