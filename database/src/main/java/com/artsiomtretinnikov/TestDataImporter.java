package com.artsiomtretinnikov;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TestDataImporter {

    private static TestDataImporter INSTANCE = new TestDataImporter();

    public void importTestData() {
        /*Role adminRole = new Role("Admin");
        ROLE_DAO.save(adminRole);
        Role coachRole = new Role("Coach");
        ROLE_DAO.save(coachRole);

        DancingHall school = new DancingHall("School 123", new Address("Minsk", "Belskogo 12", "Great hall"));
        DANCING_HALL_DAO.save(school);
        DancingHall cdoimTanka = new DancingHall("CDOIM contact", new Address("Minsk", "M.Tanka 10", "Great hall"));
        DANCING_HALL_DAO.save(cdoimTanka);

        Club blackFox = new Club("Black fox", "some info");
        CLUB_DAO.save(blackFox);
        Club jungleMove = new Club("Jungle move", "some info");
        CLUB_DAO.save(jungleMove);

        Coach daniliuk = new Coach("Irina", "Daniliuk", "Some info", new HashSet<>(Collections.singletonList(blackFox)));
        COACH_DAO.save(daniliuk);
        Coach akhatova = new Coach("Juliya", "Akhatova", "Some info", new HashSet<>(Collections.singletonList(jungleMove)));
        COACH_DAO.save(akhatova);

        Account daniliukAcc = new Account("someemail@gmail.com", "pass", daniliuk, coachRole);
        ACCOUNT_DAO.save(daniliukAcc);
        Account akhatovaAcc = new Account("email@tut.by", "pass", akhatova, coachRole);
        ACCOUNT_DAO.save(akhatovaAcc);

        DanceGroup warriors = new DanceGroup("Warriors", blackFox);
        DANCE_GROUP_DAO.save(warriors);
        DanceGroup archers = new DanceGroup("Archers", jungleMove);
        DANCE_GROUP_DAO.save(archers);

        DanceClass firstClass = new DanceClass(archers, Style.DISCO, akhatova, DayOfWeek.FRIDAY, cdoimTanka, LocalTime.now());
        DANCE_CLASS_DAO.save(firstClass);
        DanceClass secondClass = new DanceClass(warriors, Style.HIP_HOP, daniliuk, DayOfWeek.MONDAY, school, LocalTime.now());
        DANCE_CLASS_DAO.save(secondClass);
        DanceClass thirdClass = new DanceClass(archers, Style.HOUSE, akhatova, DayOfWeek.SATURDAY, cdoimTanka, LocalTime.now());
        DANCE_CLASS_DAO.save(thirdClass);

        Dancer dancerOne = new Dancer("Masha", "Chastnaya", LocalDate.now(), AgeCategory.JUNIOR, League.BEGINNER,
                new HashSet<>(Collections.singletonList(archers)), "123-12-12");
        DANCER_DAO.save(dancerOne);
        Dancer dancerTwo = new Dancer("Masha", "Balabanovich", LocalDate.now(), AgeCategory.JUNIOR, League.BEGINNER,
                new HashSet<>(Collections.singletonList(archers)), "123-12-12");
        DANCER_DAO.save(dancerTwo);
        Dancer dancerThree = new Dancer("Anton", "Podobed", LocalDate.now(), AgeCategory.ADULT, League.FIRST_LEAGUE,
                new HashSet<>(Arrays.asList(archers, warriors)), "123-12-12");
        DANCER_DAO.save(dancerThree);
        Dancer dancerFour = new Dancer("Dmitriy", "Nosevich", LocalDate.now(), AgeCategory.ADULT, League.FIRST_LEAGUE,
                new HashSet<>(Collections.singletonList(warriors)), "123-12-12");
        DANCER_DAO.save(dancerFour);
        Dancer dancerFive = new Dancer("Artsiom", "Tretinnikov", LocalDate.now(), AgeCategory.ADULT, League.FIRST_LEAGUE,
                new HashSet<>(Collections.singletonList(warriors)), "123-12-12");
        DANCER_DAO.save(dancerFive);

        RATING_DAO.save(new Rating(dancerFive, 125, Style.HIP_HOP));
        RATING_DAO.save(new Rating(dancerFive, 122, Style.DISCO));
        RATING_DAO.save(new Rating(dancerFive, 121, Style.DISCO));
        RATING_DAO.save(new Rating(dancerFive, 128, Style.DISCO));
        RATING_DAO.save(new Rating(dancerFour, 123, Style.DISCO));
        RATING_DAO.save(new Rating(dancerFour, 121, Style.DISCO));
        RATING_DAO.save(new Rating(dancerFour, 126, Style.DISCO));
        RATING_DAO.save(new Rating(dancerThree, 1425, Style.DISCO));
        RATING_DAO.save(new Rating(dancerThree, 1465, Style.DISCO));
        RATING_DAO.save(new Rating(dancerThree, 1415, Style.DISCO));
        RATING_DAO.save(new Rating(false, dancerTwo, 17, Style.DISCO));
        RATING_DAO.save(new Rating(false, dancerTwo, 27, Style.DISCO));
        RATING_DAO.save(new Rating(dancerTwo, 17, Style.DISCO));
        RATING_DAO.save(new Rating(dancerOne, 11, Style.DISCO));
        RATING_DAO.save(new Rating(dancerOne, 115, Style.DISCO));
        RATING_DAO.save(new Rating(dancerOne, 121, Style.DISCO));
        RATING_DAO.save(new Rating(dancerOne, 141, Style.DISCO));

        Request request = new Request("Vladislava", "Lavrukhina", LocalDate.now(), archers, "123-12-12");
        REQUEST_DAO.save(request);*/
    }

    public static TestDataImporter getInstance() {
        return INSTANCE;
    }
}
