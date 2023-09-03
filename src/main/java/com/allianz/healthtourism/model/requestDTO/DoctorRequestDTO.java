package com.allianz.healthtourism.model.requestDTO;

import com.allianz.healthtourism.model.enums.SpecialtyTypeEnum;
import com.allianz.healthtourism.util.model.BaseRequestDTO;
import lombok.Data;

import java.util.UUID;

@Data
public class DoctorRequestDTO extends BaseRequestDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private SpecialtyTypeEnum specialtyTypeEnum;
    private UUID hospitalUUID;

}
