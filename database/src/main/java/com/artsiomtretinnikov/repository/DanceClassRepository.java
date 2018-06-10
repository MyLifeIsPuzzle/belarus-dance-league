package com.artsiomtretinnikov.repository;

import com.artsiomtretinnikov.entity.DanceClass;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DanceClassRepository extends CrudRepository<DanceClass, Long> {

    List<DanceClass> findAllByActiveTrue();

    List<DanceClass> findAllByActiveFalse();

    List<DanceClass> findAllByCoachId(Long id);

    List<DanceClass> findAllByDancingHallId(Long id);

    List<DanceClass> findAllByDanceGroupId(Long id);
}
