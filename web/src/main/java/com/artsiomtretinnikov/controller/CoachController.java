package com.artsiomtretinnikov.controller;

import com.artsiomtretinnikov.service.CoachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/coach")
public class CoachController {

    private final CoachService coachService;

    @Autowired
    public CoachController(CoachService coachService) {
        this.coachService = coachService;
    }

    @GetMapping("/{coachId}")
    public String getById(Model model, @PathVariable("coachId") Long coachId) {
        model.addAttribute("coachModel", coachService.getById(coachId));
        return "coach";
    }

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("coachesList", coachService.getAllByActive(true));
        model.addAttribute("inactiveCoachesList", coachService.getAllByActive(false));
        return "coaches";
    }
}
