package com.allianz.healthtourism.database.specification;

import com.allianz.healthtourism.database.entity.Flight;
import com.allianz.healthtourism.database.entity.Hotel;
import com.allianz.healthtourism.model.enums.OperationTypeEnum;
import com.allianz.healthtourism.util.BaseSpecification;
import com.allianz.healthtourism.util.SearchCriteria;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class HotelSpecification extends BaseSpecification<Hotel> {
    private List<SearchCriteria> criteriaList;

    public List<SearchCriteria> getCriteriaList() {
        return criteriaList;
    }

    public void setCriteriaList(List<SearchCriteria> criteriaList) {
        this.criteriaList = criteriaList;
    }

    @Override
    public Predicate toPredicate(Root<Hotel> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
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
            } else if (criteria.getOperation().equals(OperationTypeEnum.AFTER_TODAY_INCLUDED)) {
                OffsetDateTime afterDateTime = OffsetDateTime.parse(criteria.getValue().toString());
                predicate = criteriaBuilder.greaterThanOrEqualTo(
                        root.<OffsetDateTime>get(criteria.getKey()), afterDateTime);
            } else if (criteria.getOperation().equals(OperationTypeEnum.BEFORE_TODAY)) {
                OffsetDateTime afterDateTime = OffsetDateTime.parse(criteria.getValue().toString());
                predicate = criteriaBuilder.lessThan(
                        root.<OffsetDateTime>get(criteria.getKey()), afterDateTime);
            } else if (criteria.getOperation().equals(OperationTypeEnum.BEFORE_TODAY_INCLUDED)) {
                OffsetDateTime afterDateTime = OffsetDateTime.parse(criteria.getValue().toString());
                predicate = criteriaBuilder.lessThanOrEqualTo(
                        root.<OffsetDateTime>get(criteria.getKey()), afterDateTime);
            } else {
                continue;
            }

            predicates.add(predicate);

        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}
