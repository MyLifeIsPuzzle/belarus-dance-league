package com.artsiomtretinnikov.dao;

import com.artsiomtretinnikov.daoImpl.BaseDaoImpl;
import com.artsiomtretinnikov.entity.DanceGroup;
import com.artsiomtretinnikov.entity.Request;
import com.artsiomtretinnikov.util.TestDataImporter;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;

public class RequestDaoTest extends BaseDaoImpl<Long, Request> {

    private static final SessionFactory SESSION_FACTORY = new Configuration().configure().buildSessionFactory();

    public RequestDaoTest() {
        super(Request.class);
    }

    @BeforeClass
    public static void init() {
        TestDataImporter.getInstance().importTestData(SESSION_FACTORY);
    }

    @Test
    public void saveTest(){
        Assert.assertNotNull(save(new Request("Name", "Second name", LocalDate.now(),
                SESSION_FACTORY.openSession().find(DanceGroup.class, 1L), "111-11-11")));
    }

    @Test
    public void findByIdTest(){
        Assert.assertNotNull(find(1L));
    }

    @AfterClass
    public static void term() {
        SESSION_FACTORY.close();
    }
}
