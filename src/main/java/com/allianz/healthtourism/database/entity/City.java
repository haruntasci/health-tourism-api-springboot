package com.allianz.healthtourism.database.entity;

import com.allianz.healthtourism.util.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Table
@Entity
@AttributeOverride(
        name = "uuid",
        column = @Column(
                name = "city_uuid"
        )
)
@Data
public class City extends BaseEntity {
    @Column(unique = true)
    private String name;

    @ManyToOne
    private Country country;

    @OneToMany(mappedBy = "city")
    private Set<Hospital> hospitals;

    @OneToMany(mappedBy = "city")
    private Set<Patient> patients;


}
