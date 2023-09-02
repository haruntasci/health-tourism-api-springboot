package com.allianz.healthtourism.model;

import com.allianz.healthtourism.util.base.BaseDTO;
import lombok.Data;



@Data
public class HospitalDTO extends BaseDTO {
    private String name;
    private CityDTO city;
}
