package com.allianz.healthtourism.database.entity;

import com.allianz.healthtourism.util.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Table
@Entity
@AttributeOverride(
        name = "uuid",
        column = @Column(
                name = "country_uuid"
        )
)
@Data
public class Country extends BaseEntity {
    @Column(unique = true)
    private String name;
    @OneToMany(mappedBy = "country")
    private Set<City> cities;
}
