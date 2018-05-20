package com.artsiomtretinnikov.daoImpl;

import com.artsiomtretinnikov.dao.RoleDao;
import com.artsiomtretinnikov.entity.Role;

public final class RoleDaoImpl  extends BaseDaoImpl<Long, Role> implements RoleDao {

    private static final RoleDaoImpl INSTANCE = new RoleDaoImpl(Role.class);

    private RoleDaoImpl(Class<Role> clazz) {
        super(clazz);
    }

    public static RoleDaoImpl getInstance() {
        return INSTANCE;
    }
}
