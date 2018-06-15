package com.artsiomtretinnikov.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.GenericTypeResolver;
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
    public List<T> findAllByActive(Class<?> clazz, boolean active) {
        Class<?> aClass = GenericTypeResolver.resolveTypeArgument(clazz, ActiveRepositoryImpl.class);
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<?> query = cb.createQuery(aClass);

        Root<?> root = criteria.from(aClass);
        criteria.select(root).where(cb.equal(root.get("active"), active));

        return entityManager.createQuery(criteria).getResultList();
    }
}
