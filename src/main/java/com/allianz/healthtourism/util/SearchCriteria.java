package com.allianz.healthtourism.util;

import com.allianz.healthtourism.model.enums.OperationTypeEnum;
import lombok.Data;

@Data
public class SearchCriteria {
    private String key;
    private OperationTypeEnum operation;
    private Object value;
}
