package com.artsiomtretinnikov.daoImpl;

import com.artsiomtretinnikov.dao.DancingHallDao;
import com.artsiomtretinnikov.entity.DancingHall;

public final class DancingHallDaoImpl extends BaseDaoImpl<Long, DancingHall> implements DancingHallDao {

    private static final DancingHallDaoImpl INSTANCE = new DancingHallDaoImpl(DancingHall.class);

    private DancingHallDaoImpl(Class<DancingHall> clazz) {
        super(clazz);
    }

    public static DancingHallDaoImpl getInstance() {
        return INSTANCE;
    }
}
