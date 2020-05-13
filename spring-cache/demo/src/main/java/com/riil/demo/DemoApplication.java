package com.riil.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

//启用缓存
@EnableCaching
//声明扫描包的位置
@ComponentScan(basePackages = {"com.riil.demo.*"})
@SpringBootApplication
public class DemoApplication
        extends SpringBootServletInitializer {

    public static void main(String[] args) {
        System.out.println(System.getProperty("java.io.tmpdir"));
        SpringApplication.run(DemoApplication.class, args);
    }
}
