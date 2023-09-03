package com.allianz.healthtourism.database.entity;

import com.allianz.healthtourism.util.entity.BaseEntity;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Table
@Entity
@AttributeOverride(
        name = "uuid",
        column = @Column(
                name = "medical_record_uuid"
        )
)
@Data
public class MedicalRecord extends BaseEntity {

    @Column
    private String report = "Has not been examined yet.";
}
