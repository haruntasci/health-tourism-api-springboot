package com.allianz.healthtourism.util;

import com.allianz.healthtourism.model.enums.OperationTypeEnum;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseSpecification<Entity extends BaseEntity> implements Specification<Entity> {

    private List<SearchCriteria> criteriaList;

    public List<SearchCriteria> getCriteriaList() {
        return criteriaList;
    }

    public void setCriteriaList(List<SearchCriteria> criteriaList) {
        this.criteriaList = criteriaList;
    }

    @Override
    public Predicate toPredicate(Root<Entity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        for (SearchCriteria criteria : criteriaList) {

            Predicate predicate = null;

            if (criteria.getOperation().equals(OperationTypeEnum.GREATER_THAN)) {
                predicate = criteriaBuilder.greaterThan(
                        root.<String>get(criteria.getKey()), criteria.getValue().toString());
            } else if (criteria.getOperation().equals(OperationTypeEnum.LESS_THAN)) {
                predicate = criteriaBuilder.lessThan(
                        root.<String>get(criteria.getKey()), criteria.getValue().toString());
            } else if (criteria.getOperation().equals(OperationTypeEnum.GREATER_THAN_EQUAL)) {
                predicate = criteriaBuilder.greaterThanOrEqualTo(
                        root.<String>get(criteria.getKey()), criteria.getValue().toString());
            } else if (criteria.getOperation().equals(OperationTypeEnum.LESS_THAN_EQUAL)) {
                predicate = criteriaBuilder.lessThanOrEqualTo(
                        root.<String>get(criteria.getKey()), criteria.getValue().toString());
            } else if (criteria.getOperation().equals(OperationTypeEnum.EQUAL)) {
                predicate = criteriaBuilder.equal(
                        root.<String>get(criteria.getKey()), criteria.getValue().toString());
            } else if (criteria.getOperation().equals(OperationTypeEnum.LIKE)) {
                if (root.get(criteria.getKey()).getJavaType() == String.class) {
                    predicate = criteriaBuilder.like(
                            criteriaBuilder.lower(root.<String>get(criteria.getKey())), "%" +
                                    criteria.getValue().toString().toLowerCase() + "%"
                    );
                } else {
                    predicate = criteriaBuilder.equal(
                            root.<String>get(criteria.getKey()), criteria.getValue().toString());
                }
            }
            else if (criteria.getOperation().equals(OperationTypeEnum.AFTER_TODAY)) {
                OffsetDateTime afterDateTime = OffsetDateTime.parse(criteria.getValue().toString());
                predicate = criteriaBuilder.greaterThan(
                        root.<OffsetDateTime>get(criteria.getKey()), afterDateTime);
            }
            else if (criteria.getOperation().equals(OperationTypeEnum.AFTER_TODAY_INCLUDED)) {
                OffsetDateTime afterDateTime = OffsetDateTime.parse(criteria.getValue().toString());
                predicate = criteriaBuilder.greaterThanOrEqualTo(
                        root.<OffsetDateTime>get(criteria.getKey()), afterDateTime);
            }
            else if (criteria.getOperation().equals(OperationTypeEnum.BEFORE_TODAY)) {
                OffsetDateTime afterDateTime = OffsetDateTime.parse(criteria.getValue().toString());
                predicate = criteriaBuilder.lessThan(
                        root.<OffsetDateTime>get(criteria.getKey()), afterDateTime);
            }
            else if (criteria.getOperation().equals(OperationTypeEnum.BEFORE_TODAY_INCLUDED)) {
                OffsetDateTime afterDateTime = OffsetDateTime.parse(criteria.getValue().toString());
                predicate = criteriaBuilder.lessThanOrEqualTo(
                        root.<OffsetDateTime>get(criteria.getKey()), afterDateTime);
            }

            else {
                continue;
            }

            predicates.add(predicate);

        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}
