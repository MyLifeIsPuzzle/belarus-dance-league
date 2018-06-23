package com.artsiomtretinnikov.repository;

import com.artsiomtretinnikov.entity.Coach;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoachRepository extends CrudRepository<Coach, Long>, ActiveRepository<Coach> {

    List<Coach> findAllBySecondNameContainsIgnoreCase(String secondName);
}
