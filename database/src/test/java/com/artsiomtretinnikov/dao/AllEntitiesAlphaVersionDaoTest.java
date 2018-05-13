package com.artsiomtretinnikov.dao;

import com.artsiomtretinnikov.entity.Account;
import com.artsiomtretinnikov.entity.Address;
import com.artsiomtretinnikov.entity.AgeCategory;
import com.artsiomtretinnikov.entity.Club;
import com.artsiomtretinnikov.entity.Coach;
import com.artsiomtretinnikov.entity.DanceClass;
import com.artsiomtretinnikov.entity.DanceGroup;
import com.artsiomtretinnikov.entity.Dancer;
import com.artsiomtretinnikov.entity.DancingHall;
import com.artsiomtretinnikov.entity.League;
import com.artsiomtretinnikov.entity.Rating;
import com.artsiomtretinnikov.entity.Request;
import com.artsiomtretinnikov.entity.Role;
import com.artsiomtretinnikov.entity.Style;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.HashSet;

public class AllEntitiesAlphaVersionDaoTest {

    private static final SessionFactory FACTORY = new Configuration().configure().buildSessionFactory();

    @Test
    public void test() {
        try (Session session = FACTORY.openSession()) {
            session.beginTransaction();

            Role role = new Role("Admin");
            Serializable savedRole = session.save(role);

            DancingHall dancingHall = new DancingHall("School 123", new Address("Minsk", "Belskogo 12", "Great hall"), null, true);
            Serializable savedDancingHall = session.save(dancingHall);

            Account account = new Account("Artsiom", "Tretinnikov", true, null, session.get(Role.class, savedRole));
            Serializable savedAccount = session.save(account);

            Coach coach = new Coach("Irina", "Daniliuk", true, "Some info", null, session.get(Account.class, savedAccount), null, null);
            Serializable savedCoach = session.save(coach);

            Club club = new Club("Black fox", "some info", true, null, null, new HashSet<>(Collections.singletonList(coach)));
            Serializable savedClub = session.save(club);

            DanceGroup danceGroup = new DanceGroup("Tascheri", session.get(Club.class, savedClub), null, null, null, null, true);
            Serializable savedGroup = session.save(danceGroup);

            DanceClass danceClass = new DanceClass(session.get(DanceGroup.class, savedGroup), Style.DISCO,
                    session.get(Coach.class, savedCoach), DayOfWeek.FRIDAY,
                    session.get(DancingHall.class, savedDancingHall), LocalTime.now(), true);
            session.save(danceClass);

            Dancer dancer = new Dancer("Masha", "Chastnaya", true, LocalDate.now(), AgeCategory.ADULT, League.BEGINNER,
                    new HashSet<>(Collections.singletonList(session.get(DanceGroup.class, savedGroup))), null,
                    null, "123-12-12");
            Serializable savedDancer = session.save(dancer);

            Rating rating = new Rating(session.get(Dancer.class, savedDancer), Style.DISCO, 125);
            session.save(rating);

            Request request = new Request("Masha", "Balabanovich", LocalDate.now(),
                    session.get(DanceGroup.class, savedGroup), "123-12-12", true);
            session.save(request);

            session.getTransaction().commit();
        }
        try (Session session = FACTORY.openSession()) {
            session.beginTransaction();

            Account account = session.get(Account.class, 1L);
            Club club = session.get(Club.class, 1L);
            Coach coach = session.get(Coach.class, 1L);
            DanceClass danceClass = session.get(DanceClass.class, 1L);
            DanceGroup danceGroup = session.get(DanceGroup.class, 1L);
            Dancer dancer = session.get(Dancer.class, 2L);
            DancingHall dancingHall = session.get(DancingHall.class, 1L);
            Rating rating = session.get(Rating.class, 1L);
            Request request = session.get(Request.class, 1L);
            Role role = session.get(Role.class, 1L);

            session.getTransaction().commit();
        }
    }
}
