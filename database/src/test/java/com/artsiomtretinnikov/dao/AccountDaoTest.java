package com.artsiomtretinnikov.dao;

import com.artsiomtretinnikov.TestDataImporter;
import com.artsiomtretinnikov.daoImpl.BaseDaoImpl;
import com.artsiomtretinnikov.entity.Account;
import com.artsiomtretinnikov.entity.Coach;
import com.artsiomtretinnikov.entity.Role;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class AccountDaoTest extends BaseDaoImpl<Long, Account> {

    private static final SessionFactory SESSION_FACTORY = new Configuration().configure().buildSessionFactory();

    public AccountDaoTest() {
        super(Account.class);
    }

    @BeforeClass
    public static void init() {
        TestDataImporter.getInstance().importTestData(SESSION_FACTORY);
    }

    @Test
    public void saveTest() {
        Assert.assertNotNull(save(new Account("Test email", "Test password",
                SESSION_FACTORY.openSession().get(Coach.class, 1L), SESSION_FACTORY.openSession().get(Role.class, 2L))));
    }

    @Test
    public void findByIdTest() {
        Assert.assertNotNull(find(2L));
    }

    @AfterClass
    public static void term() {
        SESSION_FACTORY.close();
    }
}
