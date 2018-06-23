package com.artsiomtretinnikov.service;

import com.artsiomtretinnikov.dto.rating.RatingValidationRequestDto;
import com.artsiomtretinnikov.entity.Rating;
import com.artsiomtretinnikov.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RatingService {

    private final RatingRepository ratingRepository;

    @Autowired
    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

/*    public Page<Rating> findAllPageable(Pageable pageable) {
        return ratingRepository.findAll(pageable);
    }*/

    public Page<Rating> findAllPageable(RatingValidationRequestDto requestDto, Pageable pageable) {
        return ratingRepository.findAll(requestDto, pageable);
    }
}
