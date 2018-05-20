package com.artsiomtretinnikov.daoImpl;

import com.artsiomtretinnikov.dao.DanceGroupDao;
import com.artsiomtretinnikov.entity.DanceGroup;

public final class DanceGroupDaoImpl extends BaseDaoImpl<Long, DanceGroup> implements DanceGroupDao {

    private static final DanceGroupDaoImpl INSTANCE = new DanceGroupDaoImpl(DanceGroup.class);

    private DanceGroupDaoImpl(Class<DanceGroup> clazz) {
        super(clazz);
    }

    public static DanceGroupDaoImpl getInstance() {
        return INSTANCE;
    }
}
