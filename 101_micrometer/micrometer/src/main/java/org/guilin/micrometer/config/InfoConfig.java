package org.guilin.micrometer.config;


import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class InfoConfig {

    @Bean
    public InfoContributor infoContributor() {
        return builder -> {
            Map<String, Object> appDetails = new HashMap<>();
            appDetails.put("name", "This Guilin's Spring Boot Application");
            appDetails.put("description", "This is a sample application using Spring Boot and Micrometer");
            appDetails.put("version", "1.0.0");

            builder.withDetail("app", appDetails);
        };
    }
}
