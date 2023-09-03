package com.allianz.healthtourism.database.entity;

import com.allianz.healthtourism.model.enums.SpecialtyTypeEnum;
import com.allianz.healthtourism.util.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Table
@Entity
@AttributeOverride(
        name = "uuid",
        column = @Column(
                name = "doctor_uuid"
        )
)
@Data
public class Doctor extends BaseEntity {
    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String phone;

    @Enumerated(EnumType.STRING)
    private SpecialtyTypeEnum specialtyTypeEnum = SpecialtyTypeEnum.DERMATOLOGY;

    @ManyToOne
    private Hospital hospital;

    @OneToMany(mappedBy = "doctor")
    private Set<Appointment> appointments;


}
