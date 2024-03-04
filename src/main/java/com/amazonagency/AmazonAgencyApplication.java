package com.amazonagency;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.amazonagency.repositories")
@EnableCaching
@EnableScheduling
public class AmazonAgencyApplication {

    public static void main(String[] args) {
        SpringApplication.run(AmazonAgencyApplication.class, args);

    }

}
