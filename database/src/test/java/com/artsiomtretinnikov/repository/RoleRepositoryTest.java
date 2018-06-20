package com.artsiomtretinnikov.repository;

import com.artsiomtretinnikov.config.TestDatabaseConfig;
import com.artsiomtretinnikov.entity.Role;
import com.artsiomtretinnikov.util.DatabaseHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestDatabaseConfig.class)
@Transactional
public class RoleRepositoryTest {

    @Autowired
    private DatabaseHelper databaseHelper;

    @Autowired
    private RoleRepository roleRepository;

    @Before
    public void init() {
        databaseHelper.cleanDatabase();
        databaseHelper.prepareData();
    }

    @Test
    public void saveTest() {
        assertNotNull(roleRepository.save(new Role("Test role")));
    }

    @Test
    public void deleteTest() {
        Optional<Role> role = roleRepository.findById(3L);
        assertTrue(role.isPresent());
        roleRepository.delete(role.get());
        assertFalse(roleRepository.findById(3L).isPresent());

    }

    @Test
    public void updateTest() {
        Optional<Role> roleOpt = roleRepository.findById(3L);
        if (roleOpt.isPresent()) {
            Role role = roleOpt.get();

            assertNotEquals(role.getName(), "Changed name");

            role.setName("Changed name");
            roleRepository.save(role);

            assertEquals(roleRepository.findById(3L).get().getName(), "Changed name");
        } else {
            fail();
        }
    }

    @Test
    public void findAllTest() {
        List<Role> result = new ArrayList<>();
        Iterable<Role> all = roleRepository.findAll();
        all.forEach(result::add);

        assertThat(result, hasSize(3));
    }

    @Test
    public void findByIdTest() {
        Optional<Role> result = roleRepository.findById(3L);

        assertTrue(result.isPresent());
    }

    @Test
    public void findAllActiveTest() {
        List<Role> active = roleRepository.findAllByActive(Role.class, true);

        assertThat(active, hasSize(2));

    }

    @Test
    public void findAllInactiveTest() {
        List<Role> active = roleRepository.findAllByActive(Role.class, false);

        assertThat(active, hasSize(1));

    }
}
