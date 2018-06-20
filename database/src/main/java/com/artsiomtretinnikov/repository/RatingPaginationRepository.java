package com.artsiomtretinnikov.repository;

import com.artsiomtretinnikov.dto.rating.RatingValidationRequestDto;
import com.artsiomtretinnikov.entity.Rating;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RatingPaginationRepository {

    Page<Rating> findAll(RatingValidationRequestDto requestDto, Pageable pageable);
}
