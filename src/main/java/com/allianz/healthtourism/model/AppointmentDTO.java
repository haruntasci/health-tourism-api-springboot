package com.allianz.healthtourism.model;

import com.allianz.healthtourism.util.base.BaseDTO;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AppointmentDTO extends BaseDTO {
    private PatientDTO patient;
    private DoctorDTO doctor;
    private MedicalRecordDTO medicalRecord;
    private LocalDateTime appointmentDateTime;
    private boolean isPaid;
    private boolean isConfirmed;
}
