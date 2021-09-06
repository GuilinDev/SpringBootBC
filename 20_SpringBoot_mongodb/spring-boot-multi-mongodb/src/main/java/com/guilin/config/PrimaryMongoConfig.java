package com.guilin.config;

import com.guilin.config.props.MultipleMongoProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@Configuration
@EnableConfigurationProperties(MultipleMongoProperties.class)
@EnableMongoRepositories(basePackages = "com.guilin.repository.primary",
        mongoTemplateRef = "primaryMongoTemplate")
public class PrimaryMongoConfig {
}
