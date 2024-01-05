package com.example.productsservice.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping("/say/{name}/{surname}")
    public String sayHello(@PathVariable String name, @PathVariable String surname){
        return "Hello! "+name+" "+surname;
    }
}
