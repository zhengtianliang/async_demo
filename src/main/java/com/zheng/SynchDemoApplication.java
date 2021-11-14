package com.zheng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SynchDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SynchDemoApplication.class, args);
    }

}
