package com.kristiantoyanuar.api.config;

import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by Kristianto Yanuar on 5/24/2016.
 */
@Configuration
@EnableJpaRepositories(
        basePackages = {
                "com.kristiantoyanuar.repo",
                "com.kristiantoyanuar.api.client.repo"
        }
)
@EntityScan(
        basePackages = {
                "com.kristiantoyanuar.model",
                "com.kristiantoyanuar.api.client.model"
        }
)
public class DataConfig {
}
