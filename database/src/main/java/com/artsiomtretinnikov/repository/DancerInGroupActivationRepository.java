package com.artsiomtretinnikov.repository;

public interface DancerInGroupActivationRepository {

    void isActive(Long dancerId, Long groupId, boolean active);
}
