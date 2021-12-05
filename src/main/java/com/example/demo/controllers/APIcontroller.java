package com.example.demo.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class APIcontroller {

    @PostMapping("/person")
    public String person(){
        return "Success";
    }
}
