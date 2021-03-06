package com.artsiomtretinnikov.repository;

import com.artsiomtretinnikov.config.TestDatabaseConfig;
import com.artsiomtretinnikov.util.DatabaseHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestDatabaseConfig.class)
@Transactional
public class AccountRepositoryTest {

    @Autowired
    private DatabaseHelper databaseHelper;

    @Autowired
    private AccountRepository accountRepository;

    @Before
    public void init() {
        databaseHelper.cleanDatabase();
        databaseHelper.prepareData();
    }

    @Test
    public void findAllTest() {
            assertNotNull(accountRepository.findAll());
    }

    @Test
    public void findByEmailTest() {
        assertNotNull(accountRepository.findByEmail("email@tut.com").orElse(null));
    }
}
