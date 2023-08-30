package com.allianz.healthtourism.database.entity;

import com.allianz.healthtourism.util.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Table
@Entity
@AttributeOverride(
        name = "uuid",
        column = @Column(
                name = "appointment_uuid"
        )
)
@Data
public class Appointment extends BaseEntity {

    @ManyToOne
    private Patient patient;

    @ManyToOne
    private Doctor doctor;

    @OneToOne
    private MedicalRecord medicalRecord;

    @Column
    private LocalDateTime appointmentDateTime = LocalDateTime.now();

    @Column
    private boolean isPaid;

    @Column
    private boolean isConfirmed;
}
