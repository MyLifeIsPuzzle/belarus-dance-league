package com.artsiomtretinnikov.controller;

import com.artsiomtretinnikov.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/club")
public class ClubController {

    private final ClubService clubService;

    @Autowired
    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    @GetMapping("/{clubId}")
    public String getClubById(Model model, @PathVariable("clubId") Long clubId) {
        model.addAttribute("club", clubService.getClub(clubId));
        return "viewClub";
    }
}
