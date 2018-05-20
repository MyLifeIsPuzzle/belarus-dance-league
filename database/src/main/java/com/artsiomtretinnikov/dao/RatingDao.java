package com.artsiomtretinnikov.dao;

import com.artsiomtretinnikov.entity.AgeCategory;
import com.artsiomtretinnikov.entity.League;
import com.artsiomtretinnikov.entity.Rating;
import com.artsiomtretinnikov.entity.Style;

import java.util.List;

public interface RatingDao extends BaseDao<Long, Rating>{

    List<Rating> findByStyleAgeLeague(Style style, AgeCategory ageCategory, League league, int pageNumber, int limit);
}
