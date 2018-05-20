package com.artsiomtretinnikov.dao;

import com.artsiomtretinnikov.daoImpl.BaseDaoImpl;
import com.artsiomtretinnikov.entity.Club;
import com.artsiomtretinnikov.entity.DanceGroup;
import com.artsiomtretinnikov.util.TestDataImporter;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class DanceGroupDaoTest extends BaseDaoImpl<Long, DanceGroup> {

    private static final SessionFactory SESSION_FACTORY = new Configuration().configure().buildSessionFactory();

    public DanceGroupDaoTest() {
        super(DanceGroup.class);
    }

    @BeforeClass
    public static void init() {
        TestDataImporter.getInstance().importTestData(SESSION_FACTORY);
    }

    @Test
    public void saveTest() {
        Assert.assertNotNull(save(new DanceGroup("Test name",
                SESSION_FACTORY.openSession().get(Club.class, 1L))));
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
