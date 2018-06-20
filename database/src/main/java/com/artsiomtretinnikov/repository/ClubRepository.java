package com.artsiomtretinnikov.repository;

import com.artsiomtretinnikov.entity.Club;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClubRepository extends CrudRepository<Club, Long>, ActiveRepository<Club> {

    List<Club> findAllByNameContains(String name);
}
