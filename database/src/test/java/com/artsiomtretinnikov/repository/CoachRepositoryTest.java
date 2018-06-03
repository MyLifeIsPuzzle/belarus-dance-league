package com.artsiomtretinnikov.repository;

import com.artsiomtretinnikov.config.TestDatabaseConfig;
import com.artsiomtretinnikov.entity.Coach;
import com.artsiomtretinnikov.util.DatabaseHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestDatabaseConfig.class)
@Transactional
public class CoachRepositoryTest {

    @Autowired
    private DatabaseHelper databaseHelper;

    @Autowired
    private CoachRepository coachRepository;

    @Before
    public void init() {
        databaseHelper.cleanDatabase();
        databaseHelper.prepareData();
    }

    @Test
    public void findBySecondName() {
        List<Coach> result = coachRepository.findAllBySecondNameContainsIgnoreCase("akha");
        assertThat(result, hasSize(1));
    }
}
