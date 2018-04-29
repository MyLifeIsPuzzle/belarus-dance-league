package com.artsiomtretinnikov.dao;

import com.artsiomtretinnikov.entity.Club;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClubDao {

    private static final ClubDao INSTANCE = new ClubDao();

    private static final SessionFactory FACTORY = new Configuration().configure().buildSessionFactory();

    public Optional<Club> getClubById(Long clubId) {
        Optional<Club> club;

        try (Session session = FACTORY.openSession()) {
            club = Optional.of(session.find(Club.class, 1L));
        }

        return club;
    }

    public static ClubDao getInstance() {
        return INSTANCE;
    }
}
