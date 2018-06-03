package com.artsiomtretinnikov.controller;

import com.artsiomtretinnikov.dto.RatingDto;
import com.artsiomtretinnikov.entity.AgeCategory;
import com.artsiomtretinnikov.entity.League;
import com.artsiomtretinnikov.entity.Style;
import com.artsiomtretinnikov.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/rating")
public class RatingController {

    private final RatingService ratingService;

    @Autowired
    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @GetMapping
    public String getClubById(@RequestParam(value = "style", required = false) String style,
                              @RequestParam(value = "league", required = false) String league,
                              @RequestParam(value = "ageCategory", required = false) String ageCategory,
                              @RequestParam(value = "page", required = false) Integer page,
                              @RequestParam(value = "limit", required = false) Integer limit,
                              Model model) {
        List<RatingDto> ratings;

        /**
         * TODO: пока что только отображает полный список
         */
        // if (style == null && ageCategory == null && league == null) {
        ratings = ratingService.findAll();
        // } else {
        //ratings = ratingService.findByStyleAgeLeague(style, ageCategory, league, page, limit);
        // }

        model.addAttribute("ratings", ratings);
        model.addAttribute("styles", Arrays.stream(Style.values()).map(Style::getName).collect(Collectors.toList()));
        model.addAttribute("ageCategories", Arrays.stream(AgeCategory.values()).map(AgeCategory::getName).collect(Collectors.toList()));
        model.addAttribute("leagues", Arrays.stream(League.values()).map(League::getName).collect(Collectors.toList()));

        return "viewRating";
    }
}
