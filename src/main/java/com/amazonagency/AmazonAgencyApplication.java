package com.amazonagency;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@EnableMongoRepositories(basePackages = "com.amazonagency.repositories")
@EnableCaching
@EnableScheduling
@EnableWebSecurity
public class AmazonAgencyApplication {

    public static void main(String[] args) {
        SpringApplication.run(AmazonAgencyApplication.class, args);

    }

}
