package com.artsiomtretinnikov;

import com.artsiomtretinnikov.manager.SessionFactoryManager;
import org.hibernate.SessionFactory;

public class ApplicationDemo {

    public static void main(String[] args) {
        SessionFactory sessionFactory = SessionFactoryManager.getSessionFactory();
        TestDataImporter.getInstance().importTestData(sessionFactory);
      //  Session session = sessionFactory.openSession();
      //  session.get(Coach.class, 1L);
      // session.close();
        sessionFactory.close();
    }
}
