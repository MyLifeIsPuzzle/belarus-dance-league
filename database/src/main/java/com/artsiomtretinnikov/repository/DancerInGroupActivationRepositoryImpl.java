package com.artsiomtretinnikov.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
public class DancerInGroupActivationRepositoryImpl implements DancerInGroupActivationRepository {

    private final EntityManager entityManager;

    @Autowired
    public DancerInGroupActivationRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void isActive(Long dancerId, Long groupId, boolean active) {
        entityManager.createNativeQuery("UPDATE dance_league.dancer_dance_group AS dg SET dg.active = :active WHERE dg.dancer_id = :dancerId AND dg.dance_group_id = :groupId")
        .setParameter("dancerId", dancerId)
        .setParameter("groupId", groupId)
        .setParameter("active", active)
        .executeUpdate();
    }
}
