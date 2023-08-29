package com.allianz.healthtourism.model;

import com.allianz.healthtourism.model.enums.SpecialtyTypeEnum;
import com.allianz.healthtourism.util.BaseDTO;
import lombok.Data;


@Data
public class DoctorDTO extends BaseDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private SpecialtyTypeEnum specialtyTypeEnum;
    private HospitalDTO hospital;
}
