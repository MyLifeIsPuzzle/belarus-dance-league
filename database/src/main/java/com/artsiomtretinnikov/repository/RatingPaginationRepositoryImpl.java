package com.artsiomtretinnikov.repository;

import com.artsiomtretinnikov.dto.rating.RatingValidationRequestDto;
import com.artsiomtretinnikov.entity.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.List;

import static org.springframework.util.StringUtils.isEmpty;

@Component
public class RatingPaginationRepositoryImpl implements RatingPaginationRepository {

    private final EntityManager entityManager;

    @Autowired
    public RatingPaginationRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Page<Rating> findAll(RatingValidationRequestDto requestDto, Pageable pageable) {
        StringBuilder queryStringBuilder = new StringBuilder();
        StringBuilder capacityStringBuilder = new StringBuilder();

        addInitialQueryParamsForCapacity(capacityStringBuilder);
        addInitialQueryParams(queryStringBuilder);

        addSortingParams(requestDto ,capacityStringBuilder);
        addSortingParams(requestDto, queryStringBuilder);

        addPaginationParams(pageable, queryStringBuilder);

        Query query = entityManager.createNativeQuery(queryStringBuilder.toString(), Rating.class);

        Object capacity = entityManager.createNativeQuery(capacityStringBuilder.toString()).getSingleResult();

        List<Rating> resultList = (List<Rating>) query.getResultList();

        return new PageImpl<>(resultList, pageable, ((BigInteger) capacity).intValue());
    }

    private void addInitialQueryParams(StringBuilder stringBuilder) {
        stringBuilder.append("SELECT * FROM dance_league.rating AS r INNER JOIN dance_league.dancer d ON r.dancer_id = d.dancer_id INNER JOIN dance_league.human_base_info bi ON d.dancer_id = bi.id ");
    }

    private void addInitialQueryParamsForCapacity(StringBuilder stringBuilder) {
        stringBuilder.append("SELECT count(r) FROM dance_league.rating AS r INNER JOIN dance_league.dancer d ON r.dancer_id = d.dancer_id INNER JOIN dance_league.human_base_info bi ON d.dancer_id = bi.id ");
    }

    private void addPaginationParams(Pageable pageable, StringBuilder stringBuilder) {
        stringBuilder.append("ORDER BY r.value DESC LIMIT ")
                .append(pageable.getPageSize())
                .append(" OFFSET ")
                .append(pageable.getPageNumber() * pageable.getPageSize());
    }

    private void addSortingParams(RatingValidationRequestDto requestDto, StringBuilder stringBuilder) {
        if (!isEmpty(requestDto.getAgeCategory()) || !isEmpty(requestDto.getLeague()) || !isEmpty(requestDto.getStyle()) || !isEmpty(requestDto.getSurname())) {
            stringBuilder.append("WHERE ");
            if (!isEmpty(requestDto.getStyle())) {
                stringBuilder.append("r.style = '")
                        .append(requestDto.getStyle().toUpperCase())
                        .append("' ");
            }

            if (!isEmpty(requestDto.getLeague())) {
                if (!isEmpty(requestDto.getStyle())) {
                    stringBuilder.append("AND ")
                            .append("d.league = '")
                            .append(requestDto.getLeague().toUpperCase())
                            .append("' ");
                } else {
                    stringBuilder.append("d.league = '")
                            .append(requestDto.getLeague().toUpperCase())
                            .append("' ");
                }
            }

            if (!isEmpty(requestDto.getAgeCategory())) {
                if (!isEmpty(requestDto.getLeague()) || !isEmpty(requestDto.getStyle())) {
                    stringBuilder.append("AND ")
                            .append("d.age_category = '")
                            .append(requestDto.getAgeCategory().toUpperCase())
                            .append("' ");
                } else {
                    stringBuilder.append("d.age_category = '")
                            .append(requestDto.getAgeCategory().toUpperCase())
                            .append("' ");
                }
            }

            if (!isEmpty(requestDto.getSurname())) {
                if (!isEmpty(requestDto.getLeague()) || !isEmpty(requestDto.getStyle()) || !isEmpty(requestDto.getAgeCategory())) {
                    stringBuilder.append("AND ")
                            .append("UPPER(bi.second_name) LIKE '")
                            .append(requestDto.getSurname().toUpperCase())
                            .append("%' ");
                } else {
                    stringBuilder.append("UPPER(bi.second_name) LIKE '")
                            .append(requestDto.getSurname().toUpperCase())
                            .append("%' ");
                }
            }
        }
    }
}
