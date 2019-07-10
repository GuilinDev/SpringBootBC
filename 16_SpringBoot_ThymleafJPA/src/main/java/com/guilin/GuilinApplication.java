package com.guilin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * 添加 Servlet 的支持
 * 添加 SpringBootServletInitializer 是为了支持将项目打包成独立的 war 在 Tomcat 中运行的情况
 */
@SpringBootApplication
public class GuilinApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder applicationBuilder) {
        return applicationBuilder.sources(GuilinApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(GuilinApplication.class, args);
    }

}
