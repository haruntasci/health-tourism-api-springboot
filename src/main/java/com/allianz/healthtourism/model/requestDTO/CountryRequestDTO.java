package com.allianz.healthtourism.model.requestDTO;

import com.allianz.healthtourism.util.model.BaseRequestDTO;
import lombok.Data;

@Data
public class CountryRequestDTO extends BaseRequestDTO {
    private String name;
}
