package com.artsiomtretinnikov.dao;

import com.artsiomtretinnikov.entity.Club;

public interface ClubDao extends BaseDao<Long, Club>{

    Club findByName(String name);
}
