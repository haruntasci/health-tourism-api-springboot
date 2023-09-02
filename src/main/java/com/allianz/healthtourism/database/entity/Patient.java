package com.allianz.healthtourism.database.entity;

import com.allianz.healthtourism.util.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Table
@Entity
@AttributeOverride(
        name = "uuid",
        column = @Column(
                name = "patient_uuid"
        )
)
@Data
public class Patient extends BaseEntity {
    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String identityNumber;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String phone;

    @Column
    private Integer age;

    @ManyToOne
    private City city;

    @OneToMany(mappedBy = "patient")
    private Set<Appointment> appointments;


}
