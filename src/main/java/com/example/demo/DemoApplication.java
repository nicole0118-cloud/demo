package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 启用缓存注解
 *
 * @author Nicole
 */
@EnableCaching
@RestController
@SpringBootApplication
public class DemoApplication {
    @RequestMapping("/devtools")
    String index() {
        return "hello cary";
    }

    public static void main(String[] args) {

        SpringApplication.run(DemoApplication.class, args);
    }

}
