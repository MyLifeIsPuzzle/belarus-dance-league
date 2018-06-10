package com.artsiomtretinnikov.repository;

import com.artsiomtretinnikov.entity.Dancer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DancerRepository extends CrudRepository<Dancer, Long> {

    List<Dancer> findAllByActiveTrue();

    List<Dancer> findAllByActiveFalse();

    List<Dancer> findAllBySecondNameContainsIgnoreCase(String secondName);
}
