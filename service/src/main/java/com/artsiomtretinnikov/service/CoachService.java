package com.artsiomtretinnikov.service;

import com.artsiomtretinnikov.converter.ModelToDtoConverter;
import com.artsiomtretinnikov.dto.coach.CoachForAllViewDto;
import com.artsiomtretinnikov.dto.coach.CoachForSingleViewDto;
import com.artsiomtretinnikov.entity.Coach;
import com.artsiomtretinnikov.repository.CoachRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CoachService {

    private final CoachRepository coachRepository;

    @Autowired
    public CoachService(CoachRepository coachRepository) {
        this.coachRepository = coachRepository;
    }

    public CoachForSingleViewDto getById(Long coachId) {
        Optional<Coach> coach = coachRepository.findById(coachId);
        return coach.map(ModelToDtoConverter::coachModelToFullDto).orElse(null);
    }

    public List<CoachForAllViewDto> getAll() {
        return ModelToDtoConverter.coachModelListToSimpleDtoList(Lists.newArrayList(coachRepository.findAll()));
    }

    public List<CoachForAllViewDto> getAllByActive(boolean active) {
        return ModelToDtoConverter.coachModelListToSimpleDtoList(Lists.newArrayList(coachRepository.findAllByActive(Coach.class, active)));
    }
}
