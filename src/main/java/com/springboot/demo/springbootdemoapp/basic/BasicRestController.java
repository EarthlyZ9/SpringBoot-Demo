package com.springboot.demo.springbootdemoapp.basic;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasicRestController {
    @Value("${myname}")
    private String name;

    @GetMapping("/")
    public String sayHello() {
        return "Hello World to " + name;
    }
}
