package com.guilin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.guilin.mapper")
public class GuilinApplication {

    public static void main(String[] args) {
        SpringApplication.run(GuilinApplication.class, args);
    }

}
