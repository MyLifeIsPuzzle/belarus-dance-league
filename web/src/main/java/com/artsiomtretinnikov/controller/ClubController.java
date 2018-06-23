package com.artsiomtretinnikov.controller;

import com.artsiomtretinnikov.dto.club.ClubForSingleViewDto;
import com.artsiomtretinnikov.dto.dancegroup.DanceGroupForAllViewDto;
import com.artsiomtretinnikov.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        ClubForSingleViewDto club = clubService.getById(clubId);

        Set<DanceGroupForAllViewDto> activeGroups = new HashSet<>();
        List<DanceGroupForAllViewDto> inactiveGroups = new ArrayList<>();

        club.getDanceGroups().stream().filter(DanceGroupForAllViewDto::isActive).forEach(activeGroups::add);
        club.getDanceGroups().stream().filter(group -> !group.isActive()).forEach(inactiveGroups::add);

        club.setDanceGroups(activeGroups);

        model.addAttribute("clubModel", club);
        model.addAttribute("inacitveGroups", inactiveGroups);

        return "club";
    }

    @GetMapping
    public String getClubs(Model model) {
        model.addAttribute("clubsList", clubService.getAllActive(true));
        model.addAttribute("inactiveClubsList", clubService.getAllActive(false));
        return "clubs";
    }
}
