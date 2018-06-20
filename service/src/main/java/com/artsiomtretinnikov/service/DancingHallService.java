package com.artsiomtretinnikov.service;

import com.artsiomtretinnikov.converter.ModelToDtoConverter;
import com.artsiomtretinnikov.dto.dancehall.DanceHallForAllViewDto;
import com.artsiomtretinnikov.dto.dancehall.DanceHallForSingleViewDto;
import com.artsiomtretinnikov.entity.DancingHall;
import com.artsiomtretinnikov.repository.DancingHallRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.artsiomtretinnikov.converter.ModelToDtoConverter.danceHallModelListToSimpleDtoList;

@Service
public class DancingHallService {

    private final DancingHallRepository dancingHallRepository;

    @Autowired
    public DancingHallService(DancingHallRepository dancingHallRepository) {
        this.dancingHallRepository = dancingHallRepository;
    }

    public List<DanceHallForAllViewDto> getAll() {
        return danceHallModelListToSimpleDtoList(Lists.newArrayList(dancingHallRepository.findAll()));
    }

    public List<DanceHallForAllViewDto> getAllByActive(boolean active) {
        return danceHallModelListToSimpleDtoList(Lists.newArrayList(dancingHallRepository.findAllByActive(DancingHall.class, active)));
    }

    public DanceHallForSingleViewDto getById(Long danceHallId) {
        Optional<DancingHall> dancingHall = dancingHallRepository.findById(danceHallId);
        return dancingHall.map(ModelToDtoConverter::danceHallModelToFullDto).orElse(null);
    }
}
