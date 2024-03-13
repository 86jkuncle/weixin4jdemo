package com.weixin4jdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* @Description java类的作用
* @Author mtxst
* @Date 2024/3/12 16:07 $
*/


@SpringBootApplication
@MapperScan("com.weixin4jdemo.mapper")
public class App {
    public static void main(String[] args){
        SpringApplication.run(App.class, args);
    }
}
