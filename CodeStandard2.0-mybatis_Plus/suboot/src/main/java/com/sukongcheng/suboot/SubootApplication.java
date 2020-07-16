package com.sukongcheng.suboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.sukongcheng.suboot.mapper")
public class SubootApplication {

    public static void main(String[] args) {
        SpringApplication.run(SubootApplication.class, args);
    }

}
