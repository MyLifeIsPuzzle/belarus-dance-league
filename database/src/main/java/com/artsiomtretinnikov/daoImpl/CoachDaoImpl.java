package com.artsiomtretinnikov.daoImpl;

import com.artsiomtretinnikov.dao.CoachDao;
import com.artsiomtretinnikov.entity.Coach;
import com.artsiomtretinnikov.entity.Coach_;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public final class CoachDaoImpl extends BaseDaoImpl<Long, Coach> implements CoachDao {

    private static final CoachDaoImpl INSTANCE = new CoachDaoImpl(Coach.class);

    private CoachDaoImpl(Class<Coach> clazz) {
        super(clazz);
    }

    @Override
    public List<Coach> findByNameAndSecondName(String name, String secondName) {
        List<Coach> coaches;

        try (Session session = SESSION_FACTORY.openSession()) {

            CriteriaBuilder cb = session.getCriteriaBuilder();

            CriteriaQuery<Coach> criteria = cb.createQuery(Coach.class);
            Root<Coach> root = criteria.from(Coach.class);
            criteria.select(root).where(
                    cb.and(
                            cb.equal(root.get(Coach_.name), name),
                            cb.equal(root.get(Coach_.secondName), secondName)
                    )
            );

            coaches = session.createQuery(criteria).list();
        }

        return coaches;
    }

    public static CoachDaoImpl getInstance() {
        return INSTANCE;
    }

}
