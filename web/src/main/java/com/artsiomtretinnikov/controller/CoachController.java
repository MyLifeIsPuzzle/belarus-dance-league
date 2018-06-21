package com.artsiomtretinnikov.controller;

import com.artsiomtretinnikov.dto.coach.CoachCreateDto;
import com.artsiomtretinnikov.service.CoachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CoachController {

    private final CoachService coachService;

    @Autowired
    public CoachController(CoachService coachService) {
        this.coachService = coachService;
    }

    @GetMapping("/coachView/{coachId}")
    public String getById(Model model, @PathVariable("coachId") Long coachId) {
        model.addAttribute("coachModel", coachService.getById(coachId));
        return "coach";
    }

    @GetMapping("/coachView")
    public String getAll(Model model) {
        model.addAttribute("coachesList", coachService.getAllByActive(true));
        model.addAttribute("inactiveCoachesList", coachService.getAllByActive(false));
        return "coaches";
    }

    @PostMapping("/admin/coach/save")
    public String save(@ModelAttribute("coachAndAccInfo") CoachCreateDto coachCreateRequest, Model model, HttpServletRequest request) {
        model.addAttribute("coachAndAccInfo", new CoachCreateDto());

        coachService.save(coachCreateRequest);

        String referer = request.getHeader("Referer");

        return "redirect:"+ referer;
    }
}
