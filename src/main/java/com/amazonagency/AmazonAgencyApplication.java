package com.amazonagency;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.amazonagency.repositories")
public class AmazonAgencyApplication {

    public static void main(String[] args) {
        SpringApplication.run(AmazonAgencyApplication.class, args);

    }

}
