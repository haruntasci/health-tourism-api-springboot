package com.allianz.healthtourism.util;

import com.allianz.healthtourism.model.SortDTO;
import lombok.Data;

import java.util.List;


@Data
public class BaseFilterRequestDTO {
    private int pageNumber;
    private int pageSize;
    private SortDTO sortDTO;
    List<SearchCriteria> searchCriteriaList;
}
