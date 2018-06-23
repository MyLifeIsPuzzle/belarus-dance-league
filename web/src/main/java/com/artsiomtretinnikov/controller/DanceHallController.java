package com.artsiomtretinnikov.controller;

import com.artsiomtretinnikov.service.DancingHallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/danceHall")
public class DanceHallController {


    private final DancingHallService dancingHallService;

    @Autowired
    public DanceHallController(DancingHallService dancingHallService) {
        this.dancingHallService = dancingHallService;
    }

    @GetMapping("/{danceHallId}")
    public String getById(Model model, @PathVariable("danceHallId") Long danceHallId) {
        model.addAttribute("dancingHall", dancingHallService.getById(danceHallId));
        return "dancing_hall";
    }

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("dancingHalls", dancingHallService.getAllByActive(true));
        model.addAttribute("inactiveDancingHalls", dancingHallService.getAllByActive(false));
        return "dancing_halls";
    }
}
