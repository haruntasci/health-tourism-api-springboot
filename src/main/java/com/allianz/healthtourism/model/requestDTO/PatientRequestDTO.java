package com.allianz.healthtourism.model.requestDTO;

import com.allianz.healthtourism.database.entity.City;
import com.allianz.healthtourism.model.AppointmentDTO;
import com.allianz.healthtourism.util.BaseRequestDTO;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
public class PatientRequestDTO extends BaseRequestDTO {
    private String firstName;
    private String lastName;
    private String identityNumber;
    private String email;
    private String phone;
    private Integer age;
    private UUID cityUUID;
}
