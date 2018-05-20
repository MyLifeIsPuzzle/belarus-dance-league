package com.artsiomtretinnikov.daoImpl;

import com.artsiomtretinnikov.dao.RatingDao;
import com.artsiomtretinnikov.entity.AgeCategory;
import com.artsiomtretinnikov.entity.Dancer;
import com.artsiomtretinnikov.entity.Dancer_;
import com.artsiomtretinnikov.entity.League;
import com.artsiomtretinnikov.entity.Rating;
import com.artsiomtretinnikov.entity.Rating_;
import com.artsiomtretinnikov.entity.Style;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

public final class RatingDaoImpl extends BaseDaoImpl<Long, Rating> implements RatingDao {

    private static final RatingDaoImpl INSTANCE = new RatingDaoImpl(Rating.class);

    private RatingDaoImpl(Class<Rating> clazz) {
        super(clazz);
    }

    /**
     * Right now works only with proper values
     */
    @Override
    public List<Rating> findByStyleAgeLeague(Style style, AgeCategory ageCategory, League league, int pageNumber, int limit) {
        List<Rating> ratings;
        pageNumber -= 1;

        try (Session session = SESSION_FACTORY.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();

            CriteriaQuery<Rating> criteria = cb.createQuery(Rating.class);
            Root<Rating> root = criteria.from(Rating.class);
            Join<Rating, Dancer> dancerJoin = root.join(Rating_.dancer);

            criteria.select(root).where(
                    cb.and(
                            cb.equal(root.get(Rating_.style), style),
                            cb.equal(dancerJoin.get(Dancer_.ageCategory), ageCategory),
                            cb.equal(dancerJoin.get(Dancer_.league), league)
                    )
            ).orderBy(cb.desc(root.get(Rating_.value)));
            ratings = session.createQuery(criteria).setFirstResult(pageNumber * limit)
                    .setMaxResults(limit).list();
        }

        return ratings;
    }

    public static RatingDaoImpl getInstance() {
        return INSTANCE;
    }
}
