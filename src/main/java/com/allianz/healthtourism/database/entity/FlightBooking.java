package com.allianz.healthtourism.database.entity;

import com.allianz.healthtourism.util.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Table
@Entity
@AttributeOverride(
        name = "uuid",
        column = @Column(
                name = "flight_booking_uuid"
        )
)
@Data
public class FlightBooking extends BaseEntity {

    @ManyToOne
    private Flight departureFlight;
    @ManyToOne
    private Flight returnFlight;
    @Column
    private Boolean isPaid;
    @Column
    private Boolean isConfirmed;
}



