package com.artsiomtretinnikov.dao;

import com.artsiomtretinnikov.entity.Dancer;

import java.util.List;

public interface DancerDao extends BaseDao<Long, Dancer>{

    List<Dancer> findByNameAndSecondName(String name, String secondName);
}
