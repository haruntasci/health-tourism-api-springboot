package com.allianz.healthtourism.model.requestDTO;

import com.allianz.healthtourism.database.entity.Doctor;
import com.allianz.healthtourism.database.entity.MedicalRecord;
import com.allianz.healthtourism.util.BaseRequestDTO;
import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class AppointmentRequestDTO extends BaseRequestDTO {
    private UUID patientUUID;
    private UUID doctorUUID;
    private LocalDateTime appointmentDateTime;
    private boolean isPaid;
}
