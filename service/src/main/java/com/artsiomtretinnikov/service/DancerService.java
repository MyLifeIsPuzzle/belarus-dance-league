package com.artsiomtretinnikov.service;

import com.artsiomtretinnikov.dto.request.SaveUserRequestDto;
import com.artsiomtretinnikov.entity.AgeCategory;
import com.artsiomtretinnikov.entity.DanceGroup;
import com.artsiomtretinnikov.entity.Dancer;
import com.artsiomtretinnikov.entity.League;
import com.artsiomtretinnikov.repository.DanceGroupRepository;
import com.artsiomtretinnikov.repository.DancerRepository;
import com.artsiomtretinnikov.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class DancerService {

    private final DancerRepository dancerRepository;
    private final DanceGroupRepository danceGroupRepository;
    private final RequestRepository requestRepository;

    @Autowired
    public DancerService(DancerRepository dancerRepository, DanceGroupRepository danceGroupRepository, RequestRepository requestRepository) {
        this.dancerRepository = dancerRepository;
        this.danceGroupRepository = danceGroupRepository;
        this.requestRepository = requestRepository;
    }

    public void save(SaveUserRequestDto createRequestDto) {
        DanceGroup danceGroup = danceGroupRepository.findById(createRequestDto.getDanceGroupId()).orElse(null);
        Set<DanceGroup> danceGroups = new HashSet<>();
        danceGroups.add(danceGroup);

        Dancer dancer = new Dancer();
        dancer.setName(createRequestDto.getName());
        dancer.setSecondName(createRequestDto.getSecondName());
        dancer.setPhoneNumber(createRequestDto.getPhoneNumber());
        dancer.setDateOfBirth(LocalDate.parse(createRequestDto.getDateOfBirth(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        dancer.setDanceGroups(danceGroups);
        dancer.setAgeCategory(AgeCategory.valueOf(createRequestDto.getAgeCategory().toUpperCase()));
        dancer.setLeague(League.valueOf(createRequestDto.getLeague().toUpperCase()));

        dancerRepository.save(dancer);

        requestRepository.deleteById(createRequestDto.getRequestId());
    }
}
