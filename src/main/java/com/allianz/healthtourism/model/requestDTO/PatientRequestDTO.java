package com.allianz.healthtourism.model.requestDTO;

import com.allianz.healthtourism.util.base.BaseRequestDTO;
import lombok.Data;

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
