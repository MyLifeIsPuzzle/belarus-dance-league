package com.artsiomtretinnikov.repository;

import com.artsiomtretinnikov.config.TestDatabaseConfig;
import com.artsiomtretinnikov.entity.DanceGroup;
import com.artsiomtretinnikov.util.DatabaseHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestDatabaseConfig.class)
@Transactional
public class DanceGroupRepositoryTest {

    @Autowired
    private DatabaseHelper databaseHelper;

    @Autowired
    private DanceGroupRepository danceGroupRepository;

    @Before
    public void init() {
        databaseHelper.cleanDatabase();
        databaseHelper.prepareData();
    }

    @Test
    public void findAllTest() {
        assertNotNull(danceGroupRepository.findAll());
    }

    @Test
    public void findAllByAccountTest() {
        List<DanceGroup> allByAccount = danceGroupRepository.findAllByAccount("email@tut.com");
        assertThat(allByAccount, hasSize(1));
    }
}
