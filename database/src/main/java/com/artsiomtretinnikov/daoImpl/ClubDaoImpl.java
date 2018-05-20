package com.artsiomtretinnikov.daoImpl;

import com.artsiomtretinnikov.dao.ClubDao;
import com.artsiomtretinnikov.entity.Club;
import com.artsiomtretinnikov.entity.Club_;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public final class ClubDaoImpl extends BaseDaoImpl<Long, Club> implements ClubDao {

    private static final ClubDaoImpl INSTANCE = new ClubDaoImpl();

    private ClubDaoImpl() {
        super(Club.class);
    }

    @Override
    public Club findByName(String name) {
        Club club;

        try (Session session = SESSION_FACTORY.openSession()) {

            CriteriaBuilder cb = session.getCriteriaBuilder();

            CriteriaQuery<Club> criteria = cb.createQuery(Club.class);
            Root<Club> root = criteria.from(Club.class);

            criteria.select(root).where(cb.equal(root.get(Club_.name), "Black fox"));

            club = session.createQuery(criteria).getSingleResult();
        }

        return club;
    }

    public static ClubDaoImpl getInstance() {
        return INSTANCE;
    }
}
