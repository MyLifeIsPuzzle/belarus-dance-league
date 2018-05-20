package com.artsiomtretinnikov.dao;

import com.artsiomtretinnikov.daoImpl.BaseDaoImpl;
import com.artsiomtretinnikov.entity.Coach;
import com.artsiomtretinnikov.entity.DanceClass;
import com.artsiomtretinnikov.entity.DanceGroup;
import com.artsiomtretinnikov.entity.DancingHall;
import com.artsiomtretinnikov.entity.Style;
import com.artsiomtretinnikov.util.TestDataImporter;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class DanceClassDaoTest extends BaseDaoImpl<Long, DanceClass> {

    private static final SessionFactory SESSION_FACTORY = new Configuration().configure().buildSessionFactory();

    public DanceClassDaoTest() {
        super(DanceClass.class);
    }

    @BeforeClass
    public static void init() {
        TestDataImporter.getInstance().importTestData(SESSION_FACTORY);
    }

    @Test
    public void saveTest() {
        Assert.assertNotNull(save(new DanceClass(SESSION_FACTORY.openSession().get(DanceGroup.class, 1L),
                Style.DISCO, SESSION_FACTORY.openSession().get(Coach.class, 1L),
                DayOfWeek.FRIDAY, SESSION_FACTORY.openSession().get(DancingHall.class, 1L), LocalTime.now())));
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
