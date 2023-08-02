package com.team5devathon5.abledappbackend.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/able/hello")
@CrossOrigin("http://localhost:4200")
public class HelloController {

    @GetMapping()
    public String helloAble(){
        System.out.println("First TEST");
        return "Hello World from the web application Able";
    }
}
