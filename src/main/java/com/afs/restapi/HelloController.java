package com.afs.restapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hello")
public class HelloController {
    // http://localhost:8080/hello -> 200 ok - hello world
    @GetMapping
    public String getHello() {
        return "Hello world";
    }
}
