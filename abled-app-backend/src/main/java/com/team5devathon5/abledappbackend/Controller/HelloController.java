package com.team5devathon5.abledappbackend.Controller;

<<<<<<< HEAD
=======
import org.springframework.web.bind.annotation.CrossOrigin;
>>>>>>> 7059b9bd45c6263f6c90e70a64bbdd8cafd5d004
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/able/hello")
<<<<<<< HEAD
=======
@CrossOrigin("http://localhost:4200")
>>>>>>> 7059b9bd45c6263f6c90e70a64bbdd8cafd5d004
public class HelloController {

    @GetMapping()
    public String helloAble(){
        System.out.println("First TEST");
        return "Hello World from the web application Able";
    }
}
