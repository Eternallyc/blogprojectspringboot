package com.eternallyc.blogproject;

import org.apache.log4j.BasicConfigurator;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(value = "com.eternallyc.blogproject.mapper")
@SpringBootApplication
public class BlogprojectApplication {

    public static void main(String[] args) {
        BasicConfigurator.configure();
        SpringApplication.run(BlogprojectApplication.class, args);
    }

}

