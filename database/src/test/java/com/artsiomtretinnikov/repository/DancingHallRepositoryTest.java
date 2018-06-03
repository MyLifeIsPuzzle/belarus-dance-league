package com.artsiomtretinnikov.repository;

import com.artsiomtretinnikov.config.TestDatabaseConfig;
import com.artsiomtretinnikov.entity.DancingHall;
import com.artsiomtretinnikov.util.DatabaseHelper;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestDatabaseConfig.class)
@Transactional
public class DancingHallRepositoryTest {

    @Autowired
    private DatabaseHelper databaseHelper;

    @Autowired
    private DancingHallRepository dancingHallRepository;

    @Before
    public void init() {
        databaseHelper.cleanDatabase();
        databaseHelper.prepareData();
    }

    @Test
    public void findByStreetTest() {
        List<DancingHall> result = dancingHallRepository.findAllByAddressStreetContainsIgnoreCase("Belsk");
        assertThat(result, Matchers.hasSize(1));
    }
}
