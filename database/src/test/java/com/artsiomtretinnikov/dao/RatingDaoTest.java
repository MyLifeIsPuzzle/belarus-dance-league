package com.artsiomtretinnikov.dao;

import com.artsiomtretinnikov.TestDataImporter;
import com.artsiomtretinnikov.daoImpl.BaseDaoImpl;
import com.artsiomtretinnikov.daoImpl.RatingDaoImpl;
import com.artsiomtretinnikov.entity.AgeCategory;
import com.artsiomtretinnikov.entity.Dancer;
import com.artsiomtretinnikov.entity.League;
import com.artsiomtretinnikov.entity.Rating;
import com.artsiomtretinnikov.entity.Style;
import org.hamcrest.Matchers;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

public class RatingDaoTest extends BaseDaoImpl<Long, Rating> {

    private static final SessionFactory SESSION_FACTORY = new Configuration().configure().buildSessionFactory();

    public RatingDaoTest() {
        super(Rating.class);
    }

    @BeforeClass
    public static void init() {
        TestDataImporter.getInstance().importTestData(SESSION_FACTORY);
    }

    @Test
    public void saveTest() {
        Rating rating = new Rating(SESSION_FACTORY.openSession().get(Dancer.class, 1L),
                125, Style.HIP_HOP);

        Assert.assertNotNull(save(rating));

        delete(rating);
    }

    @Test
    public void findByIdTest() {
        Assert.assertNotNull(find(1L));
    }

    @Test
    public void findAllTest() {
        Assert.assertEquals(17, findAll().size());
    }

    @Test
    public void updateTest() {
        Rating rating = find(1L);
        rating.setValue(5);
        update(rating);

        Assert.assertEquals(5, find(1L).getValue());

        rating.setValue(125);
        update(rating);
    }

    @Test
    public void deleteTest() {
        Rating rating = find(6L);
        delete(rating);

        Assert.assertNull(SESSION_FACTORY.openSession().get(Rating.class, 6L));

        save(rating);
    }

    @Test
    public void findByStyleAgeLeagueTest() {
        List<Rating> ratings = RatingDaoImpl.getInstance().findByStyleAgeLeague(Style.DISCO, AgeCategory.JUNIOR, League.BEGINNER, 1, 5);
        assertThat(ratings, hasSize(5));
        List<Integer> values = ratings.stream().map(Rating::getValue).collect(Collectors.toList());
        assertThat(values, Matchers.contains(141, 121, 115, 27, 17));

        ratings = RatingDaoImpl.getInstance().findByStyleAgeLeague(Style.DISCO, AgeCategory.JUNIOR, League.BEGINNER, 2, 2);
        assertThat(ratings, hasSize(2));
        values = ratings.stream().map(Rating::getValue).collect(Collectors.toList());
        assertThat(values, Matchers.contains(115, 27));
    }

    @Test
    public void findAllActiveTest() {
        List<Rating> ratings = RatingDaoImpl.getInstance().findAllActive();
        assertThat(ratings, hasSize(15));
        List<Integer> values = ratings.stream().map(Rating::getValue).collect(Collectors.toList());
        assertThat(values, Matchers.contains(125, 122, 121, 128, 123, 121, 126, 1425, 1465, 1415, 17, 11, 115, 121, 141));
    }

    @Test
    public void findAllInactiveTest() {
        List<Rating> ratings = RatingDaoImpl.getInstance().findAllInactive();
        assertThat(ratings, hasSize(2));
        List<Integer> values = ratings.stream().map(Rating::getValue).collect(Collectors.toList());
        assertThat(values, Matchers.contains(17, 27));
    }

    @AfterClass
    public static void term() {
        SESSION_FACTORY.close();
    }
}
