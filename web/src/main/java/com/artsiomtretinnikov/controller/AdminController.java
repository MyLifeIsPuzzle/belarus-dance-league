package com.artsiomtretinnikov.controller;

import com.artsiomtretinnikov.dto.coach.CoachCreateDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/admin")
    public String adminPage(Model model) {
        model.addAttribute("coachAndAccInfo", new CoachCreateDto());
        return "admin";
    }
}
