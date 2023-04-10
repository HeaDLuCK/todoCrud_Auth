package com.todoLb.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

@Configuration
public class Config extends AbstractMongoClientConfiguration {
    @Value("spring.data.mongodb.database")
    private String databaseName;

    @Override
    protected String getDatabaseName() {
        System.out.println(databaseName);
        return "databaseName";
    }

    @Override
    protected boolean autoIndexCreation() {
        return true;
    }

    @Bean
    public MongoTransactionManager MongoTransaction(MongoDatabaseFactory factory) {
        return new MongoTransactionManager(factory);
    }
}
