package com.artsiomtretinnikov.repository;

import com.artsiomtretinnikov.entity.DanceGroup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DanceGroupRepository extends CrudRepository<DanceGroup, Long> {

    List<DanceGroup> findAllByActiveTrue();

    List<DanceGroup> findAllByActiveFalse();
}
