package com.cssl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * Created by 我的电脑 on 2018/12/15.
 */
@MapperScan("com.cssl.dao")
@ServletComponentScan("com.cssl.intercetor")
@SpringBootApplication()
public class application {
    public static void main(String[] args) {
        SpringApplication.run(application.class,args);
    }
}
