package com.artsiomtretinnikov.repository;

import com.artsiomtretinnikov.entity.DancingHall;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DancingHallRepository extends CrudRepository<DancingHall, Long>, ActiveRepository<DancingHall> {

    List<DancingHall> findAllByAddressStreetContainsIgnoreCase(String name);
}
