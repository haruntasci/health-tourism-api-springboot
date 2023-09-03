package com.allianz.healthtourism.model.requestDTO;

import com.allianz.healthtourism.util.model.BaseRequestDTO;
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
