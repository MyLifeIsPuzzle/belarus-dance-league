package com.artsiomtretinnikov.repository;

import com.artsiomtretinnikov.entity.DanceGroup;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DanceGroupRepository extends CrudRepository<DanceGroup, Long>, ActiveRepository<DanceGroup> {
    @Query(nativeQuery = true, value = "SELECT DISTINCT\n" +
            "g.*\n" +
            "FROM dance_league.club c\n" +
            "INNER JOIN dance_league.club_coach cc\n" +
            "ON c.id = cc.club_id\n" +
            "INNER JOIN dance_league.coach ch\n" +
            "ON ch.coach_id = cc.coach_id\n" +
            "INNER JOIN dance_league.dance_group g\n" +
            "ON c.id = g.club_id\n" +
            "INNER JOIN dance_league.account a\n" +
            "ON ch.coach_id = a.coach_id\n" +
            "WHERE a.email = ?1 and g.active = true")
    List<DanceGroup> findAllByAccount(String email);
}
