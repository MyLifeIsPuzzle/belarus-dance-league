package com.artsiomtretinnikov.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Component
public class ActiveRepositoryImpl<T> implements ActiveRepository<T> {

    private final EntityManager entityManager;

    @Autowired
    public ActiveRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<T> findAllByActive(Class<T> clazz, boolean active) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = cb.createQuery(clazz);
        Root<T> root = query.from(clazz);
        query.select(root).where(cb.equal(root.get("active"), active));

        return entityManager.createQuery(query).getResultList();
    }
}
