package com.artsiomtretinnikov.util;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

@Component
public class DatabaseHelper {

    private final EntityManagerFactory entityManagerFactory;

    @Autowired
    public DatabaseHelper(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public void cleanDatabase() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createQuery("delete from Rating").executeUpdate();
        entityManager.createQuery("delete from Dancer").executeUpdate();
        entityManager.createQuery("delete from Request").executeUpdate();
        entityManager.createQuery("delete from Account").executeUpdate();
        entityManager.createQuery("delete from DanceClass").executeUpdate();
        entityManager.createQuery("delete from DanceGroup").executeUpdate();
        entityManager.createQuery("delete from DancingHall").executeUpdate();
        entityManager.createQuery("delete from Club").executeUpdate();
        entityManager.createQuery("delete from Coach").executeUpdate();
        entityManager.createQuery("delete from Role").executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void prepareData() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Role adminRole = new Role("Admin");
        entityManager.persist(adminRole);
        Role coachRole = new Role("Coach");
        entityManager.persist(coachRole);
        Role userRole = new Role(false, "User");
        entityManager.persist(userRole);

        DancingHall school = new DancingHall("School 123", new Address("Minsk", "Belskogo 12", "Great hall"));
        entityManager.persist(school);
        DancingHall cdoimTanka = new DancingHall("CDOIM contact", new Address("Minsk", "M.Tanka 10", "Great hall"));
        entityManager.persist(cdoimTanka);

        Club blackFox = new Club("Black fox", "some info");
        entityManager.persist(blackFox);
        Club jungleMove = new Club("Jungle move", "some info");
        entityManager.persist(jungleMove);

        Coach daniliuk = new Coach("Irina", "Daniliuk", "Some info", new HashSet<>(Collections.singletonList(blackFox)));
        entityManager.persist(daniliuk);
        Coach akhatova = new Coach("Juliya", "Akhatova", "Some info", new HashSet<>(Collections.singletonList(jungleMove)));
        entityManager.persist(akhatova);

        Account daniliukAcc = new Account("someemail@gmail.com", "pass", daniliuk, coachRole);
        entityManager.persist(daniliukAcc);
        Account akhatovaAcc = new Account("email@tut.com", "pass", akhatova, coachRole);
        entityManager.persist(akhatovaAcc);

        DanceGroup warriors = new DanceGroup("Warriors", blackFox);
        entityManager.persist(warriors);
        DanceGroup archers = new DanceGroup("Archers", jungleMove);
        entityManager.persist(archers);

        DanceClass firstClass = new DanceClass(archers, Style.DISCO, akhatova, DayOfWeek.FRIDAY, cdoimTanka, LocalTime.now());
        entityManager.persist(firstClass);
        DanceClass secondClass = new DanceClass(warriors, Style.HIP_HOP, daniliuk, DayOfWeek.MONDAY, school, LocalTime.now());
        entityManager.persist(secondClass);
        DanceClass thirdClass = new DanceClass(archers, Style.HOUSE, akhatova, DayOfWeek.SATURDAY, cdoimTanka, LocalTime.now());
        entityManager.persist(thirdClass);

        Dancer dancerOne = new Dancer("Masha", "Chastnaya", LocalDate.now(), AgeCategory.JUNIOR, League.BEGINNER,
                new HashSet<>(Collections.singletonList(archers)), "123-12-12");
        entityManager.persist(dancerOne);
        Dancer dancerTwo = new Dancer("Masha", "Balabanovich", LocalDate.now(), AgeCategory.JUNIOR, League.BEGINNER,
                new HashSet<>(Collections.singletonList(archers)), "123-12-12");
        entityManager.persist(dancerTwo);
        Dancer dancerThree = new Dancer("Anton", "Podobed", LocalDate.now(), AgeCategory.ADULT, League.FIRST_LEAGUE,
                new HashSet<>(Arrays.asList(archers, warriors)), "123-12-12");
        entityManager.persist(dancerThree);
        Dancer dancerFour = new Dancer("Dmitriy", "Nosevich", LocalDate.now(), AgeCategory.ADULT, League.FIRST_LEAGUE,
                new HashSet<>(Collections.singletonList(warriors)), "123-12-12");
        entityManager.persist(dancerFour);
        Dancer dancerFive = new Dancer("Artsiom", "Tretinnikov", LocalDate.now(), AgeCategory.ADULT, League.FIRST_LEAGUE,
                new HashSet<>(Collections.singletonList(warriors)), "123-12-12");
        entityManager.persist(dancerFive);

        entityManager.persist(new Rating(dancerFive, 125, Style.HIP_HOP));
        entityManager.persist(new Rating(dancerFive, 122, Style.DISCO));
        entityManager.persist(new Rating(dancerFive, 121, Style.DISCO));
        entityManager.persist(new Rating(dancerFive, 128, Style.DISCO));
        entityManager.persist(new Rating(dancerFour, 123, Style.DISCO));
        entityManager.persist(new Rating(dancerFour, 121, Style.DISCO));
        entityManager.persist(new Rating(dancerFour, 126, Style.DISCO));
        entityManager.persist(new Rating(dancerThree, 1425, Style.DISCO));
        entityManager.persist(new Rating(dancerThree, 1465, Style.DISCO));
        entityManager.persist(new Rating(dancerThree, 1415, Style.DISCO));
        entityManager.persist(new Rating(false, dancerTwo, 17, Style.DISCO));
        entityManager.persist(new Rating(false, dancerTwo, 27, Style.DISCO));
        entityManager.persist(new Rating(dancerTwo, 17, Style.DISCO));
        entityManager.persist(new Rating(dancerOne, 11, Style.DISCO));
        entityManager.persist(new Rating(dancerOne, 115, Style.DISCO));
        entityManager.persist(new Rating(dancerOne, 121, Style.DISCO));
        entityManager.persist(new Rating(dancerOne, 141, Style.DISCO));

        Request request = new Request("Vladislava", "Lavrukhina", LocalDate.now(), archers, "123-12-12");
        entityManager.persist(request);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
