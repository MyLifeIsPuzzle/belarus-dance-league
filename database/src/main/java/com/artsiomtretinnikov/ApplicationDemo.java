package com.artsiomtretinnikov;

import com.artsiomtretinnikov.entity.DancingHall;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ApplicationDemo {

    private static final SessionFactory FACTORY = new Configuration().configure().buildSessionFactory();

    public static void main(String[] args) {
        try (Session session = FACTORY.openSession()) {
            session.beginTransaction();

            System.out.println(session.get(DancingHall.class, 1L));

            session.getTransaction().commit();
            session.close();
        }
    }
}
