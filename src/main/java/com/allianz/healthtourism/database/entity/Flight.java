package com.allianz.healthtourism.database.entity;

import com.allianz.healthtourism.util.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Table
@Entity
@AttributeOverride(
        name = "uuid",
        column = @Column(
                name = "flight_uuid"
        )
)
@Data
public class Flight extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "departure_city_id")
    private City departureCity;
    @ManyToOne
    @JoinColumn(name = "arrival_city_id")
    private City arrivalCity;
    @Column
    private LocalDateTime departureTime;
    @Column
    private LocalDateTime arrivalTime;
    @Column
    private Integer totalSeatCount;
    @Column
    private Integer emptySeatCount;
}
