package com.todoLb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class todoApplication {
    public static void main(String[] args) {
        SpringApplication.run(todoApplication.class, args);
    }
}
