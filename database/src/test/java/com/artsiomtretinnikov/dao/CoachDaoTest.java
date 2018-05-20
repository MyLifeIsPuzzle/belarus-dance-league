package com.artsiomtretinnikov.dao;

import com.artsiomtretinnikov.daoImpl.BaseDaoImpl;
import com.artsiomtretinnikov.daoImpl.CoachDaoImpl;
import com.artsiomtretinnikov.entity.BaseHumanInfoEntity;
import com.artsiomtretinnikov.entity.Club;
import com.artsiomtretinnikov.entity.Coach;
import com.artsiomtretinnikov.util.TestDataImporter;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

public class CoachDaoTest extends BaseDaoImpl<Long, Coach> {

    private static final SessionFactory SESSION_FACTORY = new Configuration().configure().buildSessionFactory();

    public CoachDaoTest() {
        super(Coach.class);
    }

    @BeforeClass
    public static void init() {
        TestDataImporter.getInstance().importTestData(SESSION_FACTORY);
    }

    @Test
    public void saveTest() {
        Assert.assertNotNull(save(new Coach("Test name", "Test surname", "Test info",
                new HashSet<>(Collections.singletonList(SESSION_FACTORY.openSession().get(Club.class, 1L))))));
    }

    @Test
    public void findByIdTest() {
        Assert.assertNotNull(find(1L));
    }

    @Test
    public void findByNameAndSecondNameTest() {
        List<Coach> coaches = CoachDaoImpl.getInstance().findByNameAndSecondName("Irina", "Daniliuk");
        assertThat(coaches, hasSize(1));
        List<String> secondNames = coaches.stream().map(BaseHumanInfoEntity::getSecondName).collect(toList());
        assertThat(secondNames, contains("Daniliuk"));
    }

    @AfterClass
    public static void term() {
        SESSION_FACTORY.close();
    }
}
