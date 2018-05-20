package com.artsiomtretinnikov.daoImpl;

import com.artsiomtretinnikov.dao.DanceClassDao;
import com.artsiomtretinnikov.entity.Coach;
import com.artsiomtretinnikov.entity.Coach_;
import com.artsiomtretinnikov.entity.DanceClass;
import com.artsiomtretinnikov.entity.DanceClass_;
import com.artsiomtretinnikov.entity.DanceGroup;
import com.artsiomtretinnikov.entity.DanceGroup_;
import com.artsiomtretinnikov.entity.DancingHall;
import com.artsiomtretinnikov.entity.DancingHall_;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

public final class DanceClassDaoImpl extends BaseDaoImpl<Long, DanceClass> implements DanceClassDao {

    private static final DanceClassDaoImpl INSTANCE = new DanceClassDaoImpl(DanceClass.class);

    private DanceClassDaoImpl(Class<DanceClass> clazz) {
        super(clazz);
    }

    @Override
    public List<DanceClass> findByCoach(Long coachId) {
        List<DanceClass> danceClass;

        try (Session session = SESSION_FACTORY.openSession()) {

            CriteriaBuilder cb = session.getCriteriaBuilder();

            CriteriaQuery<DanceClass> criteria = cb.createQuery(DanceClass.class);
            Root<DanceClass> root = criteria.from(DanceClass.class);
            Join<DanceClass, Coach> coachJoin = root.join(DanceClass_.coach);

            criteria.select(root).where(cb.equal(coachJoin.get(Coach_.id), coachId));

            danceClass = session.createQuery(criteria).list();
        }

        return danceClass;
    }

    @Override
    public List<DanceClass> findByDanceGroup(Long danceGroupId) {
        List<DanceClass> danceClass;

        try (Session session = SESSION_FACTORY.openSession()) {

            CriteriaBuilder cb = session.getCriteriaBuilder();

            CriteriaQuery<DanceClass> criteria = cb.createQuery(DanceClass.class);
            Root<DanceClass> root = criteria.from(DanceClass.class);
            Join<DanceClass, DanceGroup> danceGroupJoin = root.join(DanceClass_.danceGroup);

            criteria.select(root).where(cb.equal(danceGroupJoin.get(DanceGroup_.id), danceGroupId));

            danceClass = session.createQuery(criteria).list();
        }

        return danceClass;
    }

    @Override
    public List<DanceClass> findByDancingHall(Long dancingHallId) {
        List<DanceClass> danceClass;

        try (Session session = SESSION_FACTORY.openSession()) {

            CriteriaBuilder cb = session.getCriteriaBuilder();

            CriteriaQuery<DanceClass> criteria = cb.createQuery(DanceClass.class);
            Root<DanceClass> root = criteria.from(DanceClass.class);
            Join<DanceClass, DancingHall> dancingHallJoin = root.join(DanceClass_.dancingHall);

            criteria.select(root).where(cb.equal(dancingHallJoin.get(DancingHall_.id), dancingHallId));

            danceClass = session.createQuery(criteria).list();
        }

        return danceClass;
    }

    public static DanceClassDaoImpl getInstance() {
        return INSTANCE;
    }
}
