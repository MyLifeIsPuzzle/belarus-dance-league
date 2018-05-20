package com.artsiomtretinnikov.dao;

import com.artsiomtretinnikov.TestDataImporter;
import com.artsiomtretinnikov.daoImpl.BaseDaoImpl;
import com.artsiomtretinnikov.entity.Role;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class RoleDaoImplTest extends BaseDaoImpl<Long, Role> {

    private static final SessionFactory SESSION_FACTORY = new Configuration().configure().buildSessionFactory();

    public RoleDaoImplTest() {
        super(Role.class);
    }

    @BeforeClass
    public static void init() {
        TestDataImporter.getInstance().importTestData(SESSION_FACTORY);
    }

    @Test
    public void saveTest() {
        Assert.assertNotNull(save(new Role("Test role")));
    }

    @Test
    public void findByIdTest() {
        Assert.assertNotNull(find(1L));
    }

    @AfterClass
    public static void term() {
        SESSION_FACTORY.close();
    }
}
