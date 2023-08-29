package com.allianz.healthtourism.model;


import com.allianz.healthtourism.database.entity.Appointment;
import com.allianz.healthtourism.database.entity.City;
import com.allianz.healthtourism.database.entity.Doctor;
import com.allianz.healthtourism.util.BaseDTO;
import lombok.Data;

import java.util.Set;


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
