package com.allianz.healthtourism.model;

import com.allianz.healthtourism.util.model.BaseDTO;
import lombok.Data;

@Data
public class CityDTO extends BaseDTO {
    private String name;
    private CountryDTO country;
}
