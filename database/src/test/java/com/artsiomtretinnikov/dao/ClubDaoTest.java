package com.artsiomtretinnikov.dao;

import com.artsiomtretinnikov.daoImpl.BaseDaoImpl;
import com.artsiomtretinnikov.daoImpl.ClubDaoImpl;
import com.artsiomtretinnikov.entity.Club;
import com.artsiomtretinnikov.util.TestDataImporter;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class ClubDaoTest extends BaseDaoImpl<Long, Club>  {

    private static final SessionFactory SESSION_FACTORY = new Configuration().configure().buildSessionFactory();

    public ClubDaoTest() {
        super(Club.class);
    }

    @BeforeClass
    public static void init() {
        TestDataImporter.getInstance().importTestData(SESSION_FACTORY);
    }

    @Test
    public void saveTest() {
        Assert.assertNotNull(save(new Club("Test name", "Test info", true)));
    }

    @Test
    public void findByIdTest() {
        Assert.assertNotNull(find(1L));
    }

    @Test
    public void findByNameTest() {
        Assert.assertNotNull(ClubDaoImpl.getInstance().findByName("Black fox"));
    }

    @AfterClass
    public static void term() {
        SESSION_FACTORY.close();
    }
}
