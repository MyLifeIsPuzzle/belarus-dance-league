package com.artsiomtretinnikov.repository;

import com.artsiomtretinnikov.entity.Rating;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends CrudRepository<Rating, Long>, PagingAndSortingRepository<Rating, Long> {

    List<Rating> findAllByActiveTrue();

    List<Rating> findAllByActiveFalse();

    /**
     * TODO: Переделаю на нормальный лад через критерии
     */
//    @Query(value = "SELECT * FROM dance_league.rating r INNER JOIN dance_league.dancer d ON r.dancer_id = d.dancer_id WHERE d.age_category = :ageCategory AND d.league = :league AND r.style = :style limit :limmit offset :page", nativeQuery = true)
//    List<Rating> searchForCriteria(@Param("style") Style style, @Param("ageCategory") AgeCategory ageCategory,@Param("league") League league, @Param("limmit") int limmit, @Param("page") int page);
}
