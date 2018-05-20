package com.artsiomtretinnikov.daoImpl;

import com.artsiomtretinnikov.dao.BaseDao;
import com.artsiomtretinnikov.entity.BaseEntity;
import com.artsiomtretinnikov.entity.BaseEntity_;
import com.artsiomtretinnikov.manager.SessionFactoryManager;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

public class BaseDaoImpl<PK extends Serializable, T extends BaseEntity<PK>> implements BaseDao<PK, T> {

    protected static final SessionFactory SESSION_FACTORY = SessionFactoryManager.getSessionFactory();

    protected Class<T> clazz;

    public BaseDaoImpl(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    @SuppressWarnings("unchecked")
    public PK save(T object) {
        try (Session session = SESSION_FACTORY.openSession()) {
            session.beginTransaction();
            Serializable id = session.save(object);
            session.getTransaction().commit();

            return (PK) id;
        }
    }

    @Override
    public T find(PK id) {
        try (Session session = SESSION_FACTORY.openSession()) {
            return session.find(clazz, id);
        }
    }

    @Override
    public List<T> findAll() {
        try (Session session = SESSION_FACTORY.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<T> criteria = cb.createQuery(clazz);
            criteria.from(clazz);

            return session.createQuery(criteria).list();
        }
    }

    @Override
    public void update(T object) {
        try (Session session = SESSION_FACTORY.openSession()) {
            session.beginTransaction();
            session.update(object);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(T object) {
        try (Session session = SESSION_FACTORY.openSession()) {
            session.beginTransaction();
            session.delete(object);
            session.getTransaction().commit();
        }
    }

    @Override
    public List<T> findAllActive() {
        return returnActiveOrInactive(true);
    }

    @Override
    public List<T> findAllInactive() {
        return returnActiveOrInactive(false);
    }

    private List<T> returnActiveOrInactive(boolean active) {
        List<T> result;

        try (Session session = SESSION_FACTORY.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();

            CriteriaQuery<T> criteria = cb.createQuery(clazz);
            Root<T> root = criteria.from(clazz);

            criteria.select(root).where(cb.equal(root.get(BaseEntity_.active), active));

            result = session.createQuery(criteria).list();
        }

        return result;
    }
}
