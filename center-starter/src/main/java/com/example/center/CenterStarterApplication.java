package com.example.center;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubbo
@SpringBootApplication
@MapperScan("com.example.center.dao")
public class CenterStarterApplication {

    public static void main(String[] args) {
        SpringApplication.run(CenterStarterApplication.class, args);
    }

}
