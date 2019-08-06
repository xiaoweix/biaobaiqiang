package com.biaobai;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.biaobai.mapper")
public class BiaobaiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BiaobaiApplication.class, args);
    }

}
