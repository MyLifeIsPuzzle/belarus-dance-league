package com.artsiomtretinnikov.dao;

import com.artsiomtretinnikov.daoImpl.BaseDaoImpl;
import com.artsiomtretinnikov.daoImpl.DanceClassDaoImpl;
import com.artsiomtretinnikov.entity.Address;
import com.artsiomtretinnikov.entity.BaseEntity;
import com.artsiomtretinnikov.entity.DanceClass;
import com.artsiomtretinnikov.entity.DancingHall;
import com.artsiomtretinnikov.util.TestDataImporter;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class DancingHallDaoTest extends BaseDaoImpl<Long, DancingHall> {

    private static final SessionFactory SESSION_FACTORY = new Configuration().configure().buildSessionFactory();

    public DancingHallDaoTest() {
        super(DancingHall.class);
    }

    @BeforeClass
    public static void init() {
        TestDataImporter.getInstance().importTestData(SESSION_FACTORY);
    }

    @Test
    public void saveTest() {
        assertNotNull(save(new DancingHall("Test hall", new Address("Minsk", "Belskogo 12", "Great hall"))));
    }

    @Test
    public void findByIdTest() {
        assertNotNull(find(1L));
    }

    @Test
    public void findByCoachTest() {
        List<DanceClass> classes = DanceClassDaoImpl.getInstance().findByCoach(2L);
        assertThat(classes, hasSize(2));
        List<Long> classesId = classes.stream().map(BaseEntity::getId).collect(toList());
        assertThat(classesId, contains(1L, 3L));
    }

    @Test
    public void findByDanceGroupTest() {
        List<DanceClass> classes = DanceClassDaoImpl.getInstance().findByDanceGroup(2L);
        assertThat(classes, hasSize(2));
        List<Long> classesId = classes.stream().map(BaseEntity::getId).collect(toList());
        assertThat(classesId, contains(1L, 3L));
    }

    @Test
    public void findByDancingHallTest() {
        List<DanceClass> classes = DanceClassDaoImpl.getInstance().findByDancingHall(2L);
        assertThat(classes, hasSize(2));
        List<Long> classesId = classes.stream().map(BaseEntity::getId).collect(toList());
        assertThat(classesId, contains(1L, 3L));
    }

    @AfterClass
    public static void term() {
        SESSION_FACTORY.close();
    }
}
