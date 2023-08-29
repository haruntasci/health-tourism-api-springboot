package com.allianz.healthtourism.model;

import com.allianz.healthtourism.database.entity.Doctor;
import com.allianz.healthtourism.database.entity.MedicalRecord;
import com.allianz.healthtourism.database.entity.Patient;
import com.allianz.healthtourism.util.BaseDTO;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AppointmentDTO extends BaseDTO {
    private PatientDTO patient;
    private DoctorDTO doctor;
    private MedicalRecordDTO medicalRecord;
    private LocalDateTime appointmentDateTime;
}
