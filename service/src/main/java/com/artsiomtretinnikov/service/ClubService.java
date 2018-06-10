package com.artsiomtretinnikov.service;

import com.artsiomtretinnikov.dto.ClubForViewDto;
import com.artsiomtretinnikov.entity.Club;
import com.artsiomtretinnikov.repository.ClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClubService {

    private final ClubRepository clubRepository;

    @Autowired
    public ClubService(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    public ClubForViewDto getClub(Long clubId) {
        Optional<Club> club = clubRepository.findById(clubId);
        return club.map(club1 -> new ClubForViewDto(club1.getId(), club1.getName(), club1.getInfo())).orElse(null);
    }
}
