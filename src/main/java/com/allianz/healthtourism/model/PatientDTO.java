package com.allianz.healthtourism.model;


import com.allianz.healthtourism.util.base.BaseDTO;
import lombok.Data;


@Data
public class PatientDTO extends BaseDTO {
    private String firstName;
    private String lastName;
    private String identityNumber;
    private String email;
    private String phone;
    private Integer age;
    private CityDTO city;
}
