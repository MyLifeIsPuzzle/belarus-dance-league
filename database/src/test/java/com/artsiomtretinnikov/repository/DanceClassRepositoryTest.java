package com.artsiomtretinnikov.repository;

import com.artsiomtretinnikov.config.TestDatabaseConfig;
import com.artsiomtretinnikov.entity.BaseEntity;
import com.artsiomtretinnikov.entity.DanceClass;
import com.artsiomtretinnikov.util.DatabaseHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestDatabaseConfig.class)
@Transactional
public class DanceClassRepositoryTest {

    @Autowired
    private DatabaseHelper databaseHelper;

    @Autowired
    private DanceClassRepository danceClassRepository;

    @Before
    public void init() {
        databaseHelper.cleanDatabase();
        databaseHelper.prepareData();
    }

    @Test
    public void findByCoachTest() {
        danceClassRepository.findAll();
        List<DanceClass> classes = danceClassRepository.findAllByCoachId(16L);
        assertThat(classes, hasSize(2));
        List<Long> classesId = classes.stream().map(BaseEntity::getId).collect(toList());
        assertThat(classesId, contains(7L, 9L));
    }

    @Test
    public void findByDanceGroupTest() {
        List<DanceClass> classes = danceClassRepository.findAllByDanceGroupId(2L);
        assertThat(classes, hasSize(2));
        List<Long> classesId = classes.stream().map(BaseEntity::getId).collect(toList());
        assertThat(classesId, contains(1L, 3L));
    }

    @Test
    public void findByDancingHallTest() {
        danceClassRepository.findAll();
        List<DanceClass> classes = danceClassRepository.findAllByDancingHallId(4L);
        assertThat(classes, hasSize(2));
        List<Long> classesId = classes.stream().map(BaseEntity::getId).collect(toList());
        assertThat(classesId, contains(4L, 6L));
    }
}
