package com.artsiomtretinnikov.daoImpl;

import com.artsiomtretinnikov.dao.RequestDao;
import com.artsiomtretinnikov.entity.Request;

public final class RequestDaoImpl extends BaseDaoImpl<Long, Request> implements RequestDao {

    private static final RequestDaoImpl INSTANCE = new RequestDaoImpl(Request.class);

    private RequestDaoImpl(Class<Request> clazz) {
        super(clazz);
    }

    public static RequestDaoImpl getInstance() {
        return INSTANCE;
    }
}
