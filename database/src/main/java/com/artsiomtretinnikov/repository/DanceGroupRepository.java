package com.artsiomtretinnikov.repository;

import com.artsiomtretinnikov.entity.DanceGroup;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface DanceGroupRepository extends CrudRepository<DanceGroup, Long>, ActiveRepository<DanceGroup> {
    @Query(nativeQuery = true, /*value = "SELECT\n" +
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
            "WHERE a.email = ?1 and g.active = true")*/
    value = "select * from belarus_dance_league.dance_league.dance_group AS g\n" +
            "INNER JOIN belarus_dance_league.dance_league.dance_class dc\n" +
            "ON g.id = dc.dance_group_id\n" +
            "INNER JOIN belarus_dance_league.dance_league.coach c\n" +
            "ON dc.coach_id = c.coach_id\n" +
            "INNER JOIN belarus_dance_league.dance_league.account ac\n" +
            "ON c.coach_id = ac.coach_id\n" +
            "WHERE ac.email = ?1 AND g.active = true;")
    Set<DanceGroup> findAllByAccount(String email);
}
