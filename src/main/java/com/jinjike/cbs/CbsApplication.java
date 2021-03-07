package com.jinjike.cbs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.jinjike.cbs.mapper")
public class CbsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CbsApplication.class, args);
    }

}
