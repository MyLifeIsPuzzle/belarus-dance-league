package com.artsiomtretinnikov.controller;

import com.artsiomtretinnikov.dto.rating.RatingValidationRequestDto;
import com.artsiomtretinnikov.entity.AgeCategory;
import com.artsiomtretinnikov.entity.League;
import com.artsiomtretinnikov.entity.Rating;
import com.artsiomtretinnikov.entity.Style;
import com.artsiomtretinnikov.pagination.Pager;
import com.artsiomtretinnikov.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/rating")
public class RatingController {

    private static final int BUTTONS_TO_SHOW = 5;
    private static final int INITIAL_PAGE = 0;
    private static final int INITIAL_PAGE_SIZE = 5;
    private static final int[] PAGE_SIZES = {5, 10, 20};

    private final RatingService ratingService;

    @Autowired
    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @GetMapping
    public ModelAndView rating(@RequestParam("pageSize") Optional<Integer> pageSize,
                               @RequestParam("page") Optional<Integer> page,
                               @RequestParam("style") Optional<String> style,
                               @RequestParam("ageCategory") Optional<String> ageCategory,
                               @RequestParam("league") Optional<String> league,
                               @RequestParam("surname") Optional<String> surname) {
        ModelAndView modelAndView = new ModelAndView("rating");

        int evalPageSize = pageSize.orElse(INITIAL_PAGE_SIZE);

        int evalPage = (page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1;

        String pickedStyle = style.orElse("");
        String pickedAgeCategory = ageCategory.orElse("");
        String pickedLeague = league.orElse("");
        String typedSurname = surname.orElse("");

        Page<Rating> ratings = ratingService.findAllPageable(new RatingValidationRequestDto(ageCategory.orElse(""), style.orElse(""), league.orElse(""), surname.orElse("")),
                PageRequest.of(evalPage, evalPageSize));
        Pager pager = new Pager(ratings.getTotalPages(), ratings.getNumber(), BUTTONS_TO_SHOW);

        modelAndView.addObject("ratings", ratings);
        modelAndView.addObject("selectedPageSize", evalPageSize);
        modelAndView.addObject("selectedAgeCategory", pickedAgeCategory);
        modelAndView.addObject("selectedLeague", pickedLeague);
        modelAndView.addObject("selectedStyle", pickedStyle);
        modelAndView.addObject("inputtedSurname", typedSurname);
        modelAndView.addObject("pageSizes", PAGE_SIZES);
        modelAndView.addObject("pager", pager);
        modelAndView.addObject("styles", Arrays.stream(Style.values()).map(Style::getName).collect(Collectors.toList()));
        modelAndView.addObject("ageCategories", Arrays.stream(AgeCategory.values()).map(AgeCategory::getName).collect(Collectors.toList()));
        modelAndView.addObject("leagues", Arrays.stream(League.values()).map(League::getName).collect(Collectors.toList()));

        return modelAndView;
    }
}
