package com.artsiomtretinnikov.daoImpl;

import com.artsiomtretinnikov.dao.DancerDao;
import com.artsiomtretinnikov.entity.Dancer;
import com.artsiomtretinnikov.entity.Dancer_;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public final class DancerDaoImpl extends BaseDaoImpl<Long, Dancer> implements DancerDao {

    private static final DancerDaoImpl INSTANCE = new DancerDaoImpl(Dancer.class);

    private DancerDaoImpl(Class<Dancer> clazz) {
        super(clazz);
    }

    public static DancerDaoImpl getInstance() {
        return INSTANCE;
    }


    @Override
    public List<Dancer> findByNameAndSecondName(String name, String secondName) {
        List<Dancer> dancers;

        try (Session session = SESSION_FACTORY.openSession()) {

            CriteriaBuilder cb = session.getCriteriaBuilder();

            CriteriaQuery<Dancer> criteria = cb.createQuery(Dancer.class);
            Root<Dancer> root = criteria.from(Dancer.class);
            criteria.select(root).where(
                    cb.and(
                            cb.equal(root.get(Dancer_.name), name),
                            cb.equal(root.get(Dancer_.secondName), secondName)
                    )
            );

            dancers = session.createQuery(criteria).list();
        }

        return dancers;
    }
}
