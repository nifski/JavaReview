package com.nifemi.pharamedian.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/")
    public String greet(HttpServletRequest request) {
        return "Hello Mr.Odumosu, I am Pharmedian" +" " + request.getSession().getId( );
    }
}
