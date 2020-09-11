package com.course.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @auther shanhen
 * @create 2020-09-11 12:55
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.course.edu", "com.course.common"})
public class EduApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class,args);
    }
}
