package com.kristiantoyanuar.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.SpringApplicationConfiguration;

/**
 * Created by Kristianto Yanuar on 5/24/2016.
 */
@SpringBootApplication(scanBasePackages = "com.kristiantoyanuar")
@EnableAutoConfiguration
public class RestApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestApiApplication.class, args);
    }
}
