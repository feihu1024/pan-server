package com.feihu1024.panserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan("com.feihu1024.panserver.interrupt")
@MapperScan("com.feihu1024.panserver.mapper")
public class PanServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(PanServerApplication.class, args);
    }

}
