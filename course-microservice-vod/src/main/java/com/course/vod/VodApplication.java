package com.course.vod;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @auther shanhen
 * @create 2020-09-12 12:54
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.course.vod"})
public class VodApplication {
    public static void main(String[] args) {
        SpringApplication.run(VodApplication.class,args);
    }
}
