package com.artsiomtretinnikov.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CoachController {

    @GetMapping("/coach")
    public String showAdminPage() {
        return "coach";
    }
}
