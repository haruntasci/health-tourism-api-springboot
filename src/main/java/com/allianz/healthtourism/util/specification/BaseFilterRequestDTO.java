package com.allianz.healthtourism.util.specification;

import com.allianz.healthtourism.model.SortDTO;
import com.allianz.healthtourism.util.specification.SearchCriteria;
import lombok.Data;

import java.util.List;


@Data
public class BaseFilterRequestDTO {
    private int pageNumber;
    private int pageSize;
    private SortDTO sortDTO;
    List<SearchCriteria> searchCriteriaList;
}
