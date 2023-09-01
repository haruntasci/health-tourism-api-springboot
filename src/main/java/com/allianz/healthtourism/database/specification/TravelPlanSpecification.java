package com.allianz.healthtourism.database.specification;

import com.allianz.healthtourism.database.entity.*;
import com.allianz.healthtourism.model.enums.OperationTypeEnum;
import com.allianz.healthtourism.util.BaseSpecification;
import com.allianz.healthtourism.util.SearchCriteria;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class TravelPlanSpecification extends BaseSpecification<TravelPlan> {
    private List<SearchCriteria> criteriaList;

    public List<SearchCriteria> getCriteriaList() {
        return criteriaList;
    }

    public void setCriteriaList(List<SearchCriteria> criteriaList) {
        this.criteriaList = criteriaList;
    }

    @Override
    public Predicate toPredicate(Root<TravelPlan> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
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

                if (criteria.getKey().equals("patient")) {
                    Join<TravelPlan, Patient> joinPatient = root.join("patient");
                    predicate = criteriaBuilder.equal(joinPatient.get("identityNumber"), criteria.getValue());
                }  else {
                    predicate = criteriaBuilder.equal(
                            root.<String>get(criteria.getKey()), criteria.getValue().toString());
                }

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
            }  else {
                continue;
            }

            predicates.add(predicate);

        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}
