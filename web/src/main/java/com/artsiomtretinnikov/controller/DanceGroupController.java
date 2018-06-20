package com.artsiomtretinnikov.controller;

import com.artsiomtretinnikov.dto.danceclass.DanceClassDto;
import com.artsiomtretinnikov.dto.dancegroup.DanceGroupForCoachViewDto;
import com.artsiomtretinnikov.dto.dancegroup.DanceGroupForSingleViewDto;
import com.artsiomtretinnikov.dto.dancer.DancerForAllViewDto;
import com.artsiomtretinnikov.dto.dancer.DancerForSingleViewDto;
import com.artsiomtretinnikov.dto.request.RequestDto;
import com.artsiomtretinnikov.service.DanceGroupService;
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
@RequestMapping("/danceGroup")
public class DanceGroupController {

    private final DanceGroupService danceGroupService;

    @Autowired
    public DanceGroupController(DanceGroupService danceGroupService) {
        this.danceGroupService = danceGroupService;
    }

    @GetMapping("/{danceGroupId}")
    public String getById(Model model, @PathVariable("danceGroupId") Long danceGroupId) {
        DanceGroupForSingleViewDto group = danceGroupService.getById(danceGroupId);

        List<DancerForAllViewDto> inactiveDancers = new ArrayList<>();
        Set<DancerForAllViewDto> activeDancers = new HashSet<>();
        Set<DanceClassDto> activeDanceClasses = new HashSet<>();
        List<DanceClassDto> inactiveDanceClasses = new ArrayList<>();
        List<RequestDto> inactiveRequests = new ArrayList<>();
        Set<RequestDto> activeRequests = new HashSet<>();

        group.getDanceClasses().stream().filter(danceClass -> !danceClass.isActive()).forEach(inactiveDanceClasses::add);
        group.getDanceClasses().stream().filter(DanceClassDto::isActive).forEach(activeDanceClasses::add);

        group.getDancers().stream().filter(dancer -> !dancer.isActive()).forEach(inactiveDancers::add);
        group.getDancers().stream().filter(DancerForAllViewDto::isActive).forEach(activeDancers::add);

        group.getRequests().stream().filter(request -> !request.isActive()).forEach(inactiveRequests::add);
        group.getRequests().stream().filter(RequestDto::isActive).forEach(activeRequests::add);

        group.setDancers(activeDancers);
        group.setDanceClasses(activeDanceClasses);
        group.setRequests(activeRequests);

        model.addAttribute("danceGroup", group);
        model.addAttribute("inactiveDancers", inactiveDancers);
        model.addAttribute("inactiveDanceClasses", inactiveDanceClasses);
        model.addAttribute("inactiveRequests", inactiveRequests);

        return "dance_group";
    }

    @GetMapping("/{danceGroupId}/viewForCoach")
    public String getByIdForCoach(Model model, @PathVariable("danceGroupId") Long danceGroupId) {
        DanceGroupForCoachViewDto group = danceGroupService.getByIdForCoach(danceGroupId);

        List<DancerForSingleViewDto> inactiveDancers = new ArrayList<>();
        Set<DancerForSingleViewDto> activeDancers = new HashSet<>();
        Set<DanceClassDto> activeDanceClasses = new HashSet<>();
        List<DanceClassDto> inactiveDanceClasses = new ArrayList<>();
        List<RequestDto> inactiveRequests = new ArrayList<>();
        Set<RequestDto> activeRequests = new HashSet<>();

        group.getDanceClasses().stream().filter(danceClass -> !danceClass.isActive()).forEach(inactiveDanceClasses::add);
        group.getDanceClasses().stream().filter(DanceClassDto::isActive).forEach(activeDanceClasses::add);

        group.getDancers().stream().filter(dancer -> !dancer.isActive()).forEach(inactiveDancers::add);
        group.getDancers().stream().filter(DancerForSingleViewDto::isActive).forEach(activeDancers::add);

        group.getRequests().stream().filter(request -> !request.isActive()).forEach(inactiveRequests::add);
        group.getRequests().stream().filter(RequestDto::isActive).forEach(activeRequests::add);

        group.setDancers(activeDancers);
        group.setDanceClasses(activeDanceClasses);
        group.setRequests(activeRequests);

        model.addAttribute("danceGroup", group);
        model.addAttribute("inactiveDancers", inactiveDancers);
        model.addAttribute("inactiveDanceClasses", inactiveDanceClasses);
        model.addAttribute("inactiveRequests", inactiveRequests);
        return "dance_group_for_coach";
    }

    @GetMapping("/{email}/groups")
    public String getByAccount(Model model, @PathVariable("email") String email) {
        model.addAttribute("danceGroups", danceGroupService.getByAccount(email));
        return "coach_personal_page";
    }
}
