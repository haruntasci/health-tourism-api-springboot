package com.allianz.healthtourism.database.entity;

import com.allianz.healthtourism.util.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Table
@Entity
@AttributeOverride(
        name = "uuid",
        column = @Column(
                name = "hospital_uuid"
        )
)
@Data
public class Hospital extends BaseEntity {

    @Column
    private String name;

    @ManyToOne
    private City city;

    @OneToMany(mappedBy = "hospital",cascade = CascadeType.ALL)
    private Set<Doctor> doctors;

}
