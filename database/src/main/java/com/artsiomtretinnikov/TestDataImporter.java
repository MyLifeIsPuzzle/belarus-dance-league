package com.artsiomtretinnikov;

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
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestDataImporter {

    private static TestDataImporter INSTANCE = new TestDataImporter();

    public void importTestData(SessionFactory sessionFactory) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Role adminRole = new Role("Admin");
            session.save(adminRole);
            Role coachRole = new Role("Coach");
            session.save(coachRole);

            DancingHall school = new DancingHall("School 123", new Address("Minsk", "Belskogo 12", "Great hall"));
            session.save(school);
            DancingHall cdoimTanka = new DancingHall("CDOIM contact", new Address("Minsk", "M.Tanka 10", "Great hall"));
            session.save(cdoimTanka);

            Club blackFox = new Club("Black fox", "some info");
            session.save(blackFox);
            Club jungleMove = new Club("Jungle move", "some info");
            session.save(jungleMove);

            Coach daniliuk = new Coach("Irina", "Daniliuk", "Some info", new HashSet<>(Collections.singletonList(blackFox)));
            session.save(daniliuk);
            Coach akhatova = new Coach("Juliya", "Akhatova", "Some info", new HashSet<>(Collections.singletonList(jungleMove)));
            session.save(akhatova);

            Account daniliukAcc = new Account("someemail@gmail.com", "pass", daniliuk, coachRole);
            session.save(daniliukAcc);
            Account akhatovaAcc = new Account("email@tut.by", "pass", akhatova, coachRole);
            session.save(akhatovaAcc);

            DanceGroup warriors = new DanceGroup("Warriors", blackFox);
            session.save(warriors);
            DanceGroup archers = new DanceGroup("Archers", jungleMove);
            session.save(archers);

            DanceClass firstClass = new DanceClass(archers, Style.DISCO, akhatova, DayOfWeek.FRIDAY, cdoimTanka, LocalTime.now());
            session.save(firstClass);
            DanceClass secondClass = new DanceClass(warriors, Style.HIP_HOP, daniliuk, DayOfWeek.MONDAY, school, LocalTime.now());
            session.save(secondClass);
            DanceClass thirdClass = new DanceClass(archers, Style.HOUSE, akhatova, DayOfWeek.SATURDAY, cdoimTanka, LocalTime.now());
            session.save(thirdClass);

            Dancer dancerOne = new Dancer("Masha", "Chastnaya", LocalDate.now(), AgeCategory.JUNIOR, League.BEGINNER,
                    new HashSet<>(Collections.singletonList(archers)), "123-12-12");
            session.save(dancerOne);
            Dancer dancerTwo = new Dancer("Masha", "Balabanovich", LocalDate.now(), AgeCategory.JUNIOR, League.BEGINNER,
                    new HashSet<>(Collections.singletonList(archers)), "123-12-12");
            session.save(dancerTwo);
            Dancer dancerThree = new Dancer("Anton", "Podobed", LocalDate.now(), AgeCategory.ADULT, League.FIRST_LEAGUE,
                    new HashSet<>(Arrays.asList(archers, warriors)), "123-12-12");
            session.save(dancerThree);
            Dancer dancerFour = new Dancer("Dmitriy", "Nosevich", LocalDate.now(), AgeCategory.ADULT, League.FIRST_LEAGUE,
                    new HashSet<>(Collections.singletonList(warriors)), "123-12-12");
            session.save(dancerFour);
            Dancer dancerFive = new Dancer("Artsiom", "Tretinnikov", LocalDate.now(), AgeCategory.ADULT, League.FIRST_LEAGUE,
                    new HashSet<>(Collections.singletonList(warriors)), "123-12-12");
            session.save(dancerFive);

            session.save(new Rating(dancerFive, 125, Style.HIP_HOP));
            session.save(new Rating(dancerFive, 122, Style.DISCO));
            session.save(new Rating(dancerFive, 121, Style.DISCO));
            session.save(new Rating(dancerFive, 128, Style.DISCO));
            session.save(new Rating(dancerFour, 123, Style.DISCO));
            session.save(new Rating(dancerFour, 121, Style.DISCO));
            session.save(new Rating(dancerFour, 126, Style.DISCO));
            session.save(new Rating(dancerThree, 1425, Style.DISCO));
            session.save(new Rating(dancerThree, 1465, Style.DISCO));
            session.save(new Rating(dancerThree, 1415, Style.DISCO));
            session.save(new Rating(false, dancerTwo, 17, Style.DISCO));
            session.save(new Rating(false, dancerTwo, 27, Style.DISCO));
            session.save(new Rating(dancerTwo, 17, Style.DISCO));
            session.save(new Rating(dancerOne, 11, Style.DISCO));
            session.save(new Rating(dancerOne, 115, Style.DISCO));
            session.save(new Rating(dancerOne, 121, Style.DISCO));
            session.save(new Rating(dancerOne, 141, Style.DISCO));

            Request request = new Request("Vladislava", "Lavrukhina", LocalDate.now(), archers, "123-12-12");
            session.save(request);

            session.getTransaction().commit();
        }
    }

    public static TestDataImporter getInstance() {
        return INSTANCE;
    }
}
