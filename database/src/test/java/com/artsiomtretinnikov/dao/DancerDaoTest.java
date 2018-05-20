package com.artsiomtretinnikov.dao;

import com.artsiomtretinnikov.TestDataImporter;
import com.artsiomtretinnikov.daoImpl.BaseDaoImpl;
import com.artsiomtretinnikov.daoImpl.DancerDaoImpl;
import com.artsiomtretinnikov.entity.AgeCategory;
import com.artsiomtretinnikov.entity.BaseHumanInfoEntity;
import com.artsiomtretinnikov.entity.DanceGroup;
import com.artsiomtretinnikov.entity.Dancer;
import com.artsiomtretinnikov.entity.League;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

public class DancerDaoTest extends BaseDaoImpl<Long, Dancer> {

    private static final SessionFactory SESSION_FACTORY = new Configuration().configure().buildSessionFactory();

    public DancerDaoTest() {
        super(Dancer.class);
    }

    @BeforeClass
    public static void init() {
        TestDataImporter.getInstance().importTestData(SESSION_FACTORY);
    }

    @Test
    public void saveTest() {
        Assert.assertNotNull(save(new Dancer("Test name", "Test second name", LocalDate.now(), AgeCategory.ADULT,
                League.BEGINNER, new HashSet<>(Collections.singletonList(SESSION_FACTORY.openSession().get(DanceGroup.class, 5L))),
                "111-11-11")));
    }

    @Test
    public void findByIdTest() {
        Assert.assertNotNull(find(5L));
    }

    @Test
    public void findByNameAndSecondNameTest() {
        List<Dancer> dancers = DancerDaoImpl.getInstance().findByNameAndSecondName("Masha", "Chastnaya");
        assertThat(dancers, hasSize(1));
        List<String> secondNames = dancers.stream().map(BaseHumanInfoEntity::getSecondName).collect(toList());
        assertThat(secondNames, contains("Chastnaya"));
    }

    @AfterClass
    public static void term() {
        SESSION_FACTORY.close();
    }
}
