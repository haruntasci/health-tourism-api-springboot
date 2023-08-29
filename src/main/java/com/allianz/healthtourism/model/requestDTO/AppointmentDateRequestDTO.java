package com.allianz.healthtourism.model.requestDTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AppointmentDateRequestDTO {
    private LocalDateTime appointmentDateTime;
}
