package com.artsiomtretinnikov.repository;

import com.artsiomtretinnikov.entity.Rating;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends CrudRepository<Rating, Long>,
        PagingAndSortingRepository<Rating, Long>,
        ActiveRepository<Rating>,
        RatingPaginationRepository {
}
