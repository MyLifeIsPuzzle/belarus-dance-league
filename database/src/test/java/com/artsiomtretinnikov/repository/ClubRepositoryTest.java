package com.artsiomtretinnikov.repository;

import com.artsiomtretinnikov.config.TestDatabaseConfig;
import com.artsiomtretinnikov.entity.Club;
import com.artsiomtretinnikov.util.DatabaseHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestDatabaseConfig.class)
@Transactional
public class ClubRepositoryTest {

    @Autowired
    private DatabaseHelper databaseHelper;

    @Autowired
    private ClubRepository clubRepository;

    @Before
    public void init() {
        databaseHelper.cleanDatabase();
        databaseHelper.prepareData();
    }

    @Test
    public void findAllTest() {
        assertNotNull(clubRepository.findAll());
    }

    @Test
    public void findAllByNameContainsTest() {
        List<Club> jungle_move = clubRepository.findAllByNameContains("Jun");
        List<String> names = jungle_move.stream().map(Club::getName).collect(Collectors.toList());
        assertThat(names, contains("Jungle move"));
    }
}
