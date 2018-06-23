package com.artsiomtretinnikov.controller;

import com.artsiomtretinnikov.dto.request.RequestDto;
import com.artsiomtretinnikov.dto.request.SaveUserRequestDto;
import com.artsiomtretinnikov.service.DancerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class DancerController {

    private final DancerService dancerService;

    @Autowired
    public DancerController(DancerService dancerService) {
        this.dancerService = dancerService;
    }

    @PostMapping("/coach/dancer")
    public String save(@ModelAttribute("createRequestDto") SaveUserRequestDto createRequestDto, HttpServletRequest request, Model model) {
        dancerService.save(createRequestDto);

        model.addAttribute("requestDto" ,new RequestDto());
        String referer = request.getHeader("Referer");

        return "redirect:"+ referer;
    }
}
