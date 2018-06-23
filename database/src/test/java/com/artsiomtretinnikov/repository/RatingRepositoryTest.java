package com.artsiomtretinnikov.repository;

import com.artsiomtretinnikov.config.TestDatabaseConfig;
import com.artsiomtretinnikov.dto.rating.RatingValidationRequestDto;
import com.artsiomtretinnikov.entity.Rating;
import com.artsiomtretinnikov.util.DatabaseHelper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestDatabaseConfig.class)
@Transactional
public class RatingRepositoryTest {

    @Autowired
    private DatabaseHelper databaseHelper;

    @Autowired
    private RatingRepository ratingRepository;

    @Before
    public void init() {
        databaseHelper.cleanDatabase();
        databaseHelper.prepareData();
    }

    @Test
    public void pagingTest() {
        Page<Rating> result = ratingRepository.findAll(PageRequest.of(1, 5, Sort.Direction.ASC, "value"));

        assertEquals(result.getTotalElements(), 17);
        assertEquals(result.getTotalPages(), 4);
    }

    @Test
    public void findAll_BY_STYLE() {
        RatingValidationRequestDto dto = RatingValidationRequestDto.builder()
                .ageCategory("")
                .league("")
                .style("Disco")
                .surname("")
                .build();

        Page<Rating> result = ratingRepository.findAll(dto, PageRequest.of(0, 5));
        Assert.assertEquals(result.getTotalElements(), 16);
    }

    @Test
    public void findAll_BY_AGE_CATEGORY() {
        RatingValidationRequestDto dto = RatingValidationRequestDto.builder()
                .ageCategory("Adult")
                .league("")
                .style("")
                .surname("")
                .build();

        Page<Rating> result = ratingRepository.findAll(dto, PageRequest.of(0, 5));
        Assert.assertEquals(result.getTotalElements(), 10);
    }

    @Test
    public void findAll_BY_LEAGUE() {
        RatingValidationRequestDto dto = RatingValidationRequestDto.builder()
                .ageCategory("")
                .league("Beginner")
                .style("")
                .surname("")
                .build();

        Page<Rating> result = ratingRepository.findAll(dto, PageRequest.of(0, 5));
        Assert.assertEquals(result.getTotalElements(), 7);
    }

    @Test
    public void findAll_BY_SURNAME() {
        RatingValidationRequestDto dto = RatingValidationRequestDto.builder()
                .ageCategory("")
                .league("")
                .style("")
                .surname("pod")
                .build();

        Page<Rating> result = ratingRepository.findAll(dto, PageRequest.of(0, 5));
        Assert.assertEquals(result.getTotalElements(), 3);
    }
}
