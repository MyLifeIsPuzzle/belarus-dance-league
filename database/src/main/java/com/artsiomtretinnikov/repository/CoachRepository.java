package com.artsiomtretinnikov.repository;

import com.artsiomtretinnikov.entity.Coach;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoachRepository extends CrudRepository<Coach, Long> {

    List<Coach> findAllByActiveTrue();

    List<Coach> findAllByActiveFalse();

    List<Coach> findAllBySecondNameContainsIgnoreCase(String secondName);
}
