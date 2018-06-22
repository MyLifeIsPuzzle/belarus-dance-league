package com.artsiomtretinnikov.controller;

import com.artsiomtretinnikov.dto.coach.CoachCreateDto;
import com.artsiomtretinnikov.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    private final LogService logService;

    @Autowired
    public AdminController(LogService logService) {
        this.logService = logService;
    }

    @GetMapping("/admin")
    public String adminPage(Model model) {
        model.addAttribute("coachAndAccInfo", new CoachCreateDto());
        return "admin";
    }

    @GetMapping("/admin/logs")
    public String showLogs(Model model) {
        model.addAttribute("logs", logService.getLogs());
        return "logs";
    }
}
