package com.artsiomtretinnikov.service;

import com.artsiomtretinnikov.dto.RatingDto;
import com.artsiomtretinnikov.entity.Rating;
import com.artsiomtretinnikov.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RatingService {

    private final RatingRepository ratingRepository;

    @Autowired
    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    /**
     * TODO:
     * @return
     */
//    public List<RatingDto> findByStyleAgeLeague(String style, String ageCategory, String league, int pageNumber, int limit) {
//        List<Rating> ratings = ratingRepository.searchForCriteria(Style.valueOf(style.toUpperCase()),
//                AgeCategory.valueOf(ageCategory.toUpperCase()), League.valueOf(league.toUpperCase()), pageNumber, limit);
//
//        return modelToDto(ratings);
//    }

    public List<RatingDto> findAll() {
        List<Rating> ratings = new ArrayList<>();

        Iterable<Rating> value = ratingRepository.findAll(Sort.by(Sort.Direction.DESC, "value"));
        value.forEach(ratings::add);

        return modelToDto(ratings);
    }

    private List<RatingDto> modelToDto(List<Rating> ratings) {
        return ratings.stream().map(rating -> RatingDto.builder()
                .id(rating.getId())
                .dancer(rating.getDancer())
                .style(rating.getStyle().getName())
                .value(rating.getValue())
                .build())
                .collect(Collectors.toList());
    }
}
