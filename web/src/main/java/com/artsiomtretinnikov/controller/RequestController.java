package com.artsiomtretinnikov.controller;

import com.artsiomtretinnikov.dto.request.CreateRequestDto;
import com.artsiomtretinnikov.dto.request.RequestDto;
import com.artsiomtretinnikov.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RequestController {

    private final RequestService requestService;

    @Autowired
    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @PostMapping("/request")
    public String saveRequest(@ModelAttribute("requestModel") CreateRequestDto requestModel, Model model, @RequestHeader("Referer") String referer) {
        model.addAttribute("requestModel", new CreateRequestDto());
        requestService.save(requestModel);

        return "redirect:"+ referer;
    }

    @PostMapping("/coach/request/delete")
    public String deleteRequest(@RequestParam("requestId") Long requestId, @RequestHeader("Referer") String referer) {
        requestService.delete(requestId);
        return "redirect:"+ referer;
    }

    @PostMapping("/coach/request/activate")
    public String activateRequest(@ModelAttribute("requestDto") RequestDto requestDto, @RequestHeader("Referer") String referer, Model model) {
        model.addAttribute("requestDto", new RequestDto());
        requestService.activation(requestDto, true);
        return "redirect:"+ referer;
    }

    @PostMapping("/coach/request/deactivate")
    public String deactivateRequest(@ModelAttribute("requestDto") RequestDto requestDto, @RequestHeader("Referer") String referer, Model model) {
        model.addAttribute("requestDto", new RequestDto());
        requestService.activation(requestDto, false);
        return "redirect:"+ referer;
    }
}
