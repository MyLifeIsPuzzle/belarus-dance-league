package com.artsiomtretinnikov.controller;

import com.artsiomtretinnikov.dto.request.RequestDto;
import com.artsiomtretinnikov.dto.request.SaveUserRequestDto;
import com.artsiomtretinnikov.service.DancerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

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

    @PostMapping("/coach/dancer/activate_in_group")
    public String activateInGroup(@RequestParam("dancerId") Long dancerId, @RequestParam("groupId") Long groupId, @RequestHeader("Referer") String referer) {
        dancerService.isActive(dancerId, groupId, true);
        return "redirect:"+ referer;
    }

    @PostMapping("/coach/dancer/deactivate_in_group")
    public String deactivateInGroup(@RequestParam("dancerId") Long dancerId, @RequestParam("groupId") Long groupId, @RequestHeader("Referer") String referer) {
        dancerService.isActive(dancerId, groupId, false);
        return "redirect:"+ referer;
    }
}
