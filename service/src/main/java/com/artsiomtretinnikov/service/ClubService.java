package com.artsiomtretinnikov.service;

import com.artsiomtretinnikov.converter.ModelToDtoConverter;
import com.artsiomtretinnikov.dto.club.ClubForAllViewDto;
import com.artsiomtretinnikov.dto.club.ClubForSingleViewDto;
import com.artsiomtretinnikov.entity.Club;
import com.artsiomtretinnikov.repository.ClubRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class ClubService {

    private final ClubRepository clubRepository;

    @Autowired
    public ClubService(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    public ClubForSingleViewDto getById(Long clubId) {
        Optional<Club> club = clubRepository.findById(clubId);
        return club.map(ModelToDtoConverter::clubModelToFullDto).orElse(null);
    }

    public List<ClubForAllViewDto> getAll() {
        return ModelToDtoConverter.clubModelListToSimpleDtoList(Lists.newArrayList(clubRepository.findAll()));
    }

    public List<ClubForAllViewDto> getAllActive(boolean active) {
        return ModelToDtoConverter.clubModelListToSimpleDtoList(Lists.newArrayList(clubRepository.findAllByActive(Club.class, active)));
    }
}
