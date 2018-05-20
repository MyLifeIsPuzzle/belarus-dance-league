package com.artsiomtretinnikov.dao;

import com.artsiomtretinnikov.entity.DanceClass;

import java.util.List;

public interface DanceClassDao extends BaseDao<Long, DanceClass>{

    List<DanceClass> findByCoach(Long coachId);

    List<DanceClass> findByDanceGroup(Long danceGroupId);

    List<DanceClass> findByDancingHall(Long dancingHallId);
}
