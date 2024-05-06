package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestHeader;


@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class ThoughtTacker {

    public static void main(String[] args) {
        SpringApplication.run(ThoughtTacker.class,args);
    }
}
