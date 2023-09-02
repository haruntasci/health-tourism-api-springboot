package com.allianz.healthtourism.model.requestDTO;

import com.allianz.healthtourism.util.base.BaseRequestDTO;
import lombok.Data;

@Data
public class MedicalRecordRequestDTO extends BaseRequestDTO {
    private String report;
}
