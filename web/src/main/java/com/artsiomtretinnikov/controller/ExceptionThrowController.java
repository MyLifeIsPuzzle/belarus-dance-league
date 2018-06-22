package com.artsiomtretinnikov.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExceptionThrowController {

    @GetMapping("/exception")
    public String throwException() {
        int integer = 5/0;
        return "home";
    }
}
