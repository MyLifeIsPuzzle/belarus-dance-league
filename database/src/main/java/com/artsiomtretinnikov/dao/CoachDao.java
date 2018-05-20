package com.artsiomtretinnikov.dao;

import com.artsiomtretinnikov.entity.Coach;

import java.util.List;

public interface CoachDao extends BaseDao<Long, Coach>{

    List<Coach> findByNameAndSecondName(String name, String secondName);
}
