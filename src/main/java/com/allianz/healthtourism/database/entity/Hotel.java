package com.allianz.healthtourism.database.entity;

import com.allianz.healthtourism.util.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Table
@Entity
@AttributeOverride(
        name = "uuid",
        column = @Column(
                name = "hotel_uuid"
        )
)
@Data
public class Hotel extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;
    @Column
    private LocalDateTime checkIn;
    @Column
    private LocalDateTime checkOut;
    @Column
    private Integer totalRoomCount;
    @Column
    private Integer emptyRoomCount;
}

