package Homework.Fighting.src.hello.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello(){
        return "Hello world";
    }

    @GetMapping("/hello-string")
    public String helloString(@RequestParam("name") String name){
        return "Hello world " + name;
    }
}
