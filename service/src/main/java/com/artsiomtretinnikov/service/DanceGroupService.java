package com.artsiomtretinnikov.service;

import com.artsiomtretinnikov.converter.ModelToDtoConverter;
import com.artsiomtretinnikov.dto.dancegroup.DanceGroupForAllViewDto;
import com.artsiomtretinnikov.dto.dancegroup.DanceGroupForCoachViewDto;
import com.artsiomtretinnikov.dto.dancegroup.DanceGroupForSingleViewDto;
import com.artsiomtretinnikov.entity.DanceGroup;
import com.artsiomtretinnikov.repository.DanceGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@CacheConfig(cacheNames = "danceGroup")
public class DanceGroupService {

    private final DanceGroupRepository danceGroupRepository;

    @Autowired
    public DanceGroupService(DanceGroupRepository danceGroupRepository) {
        this.danceGroupRepository = danceGroupRepository;
    }

    public DanceGroupForSingleViewDto getById(Long danceGroupId) {
        Optional<DanceGroup> danceGroup = danceGroupRepository.findById(danceGroupId);
        return danceGroup.map(ModelToDtoConverter::danceGroupModelToFullDto).orElse(null);
    }

    public DanceGroupForCoachViewDto getByIdForCoach(Long danceGroupId) {
        Optional<DanceGroup> danceGroup = danceGroupRepository.findById(danceGroupId);
        return danceGroup.map(ModelToDtoConverter::danceGroupModelToFullForCoachDto).orElse(null);
    }

    public List<DanceGroupForAllViewDto> getByAccount(String email) {
      return  ModelToDtoConverter.danceGroupModelListToSimpleDtoList(danceGroupRepository.findAllByAccount(email));
    }

    public List<DanceGroupForAllViewDto> getAllByActive(boolean active) {
        return  ModelToDtoConverter.danceGroupModelListToSimpleDtoList(danceGroupRepository.findAllByActive(DanceGroup.class, active));
    }
}
