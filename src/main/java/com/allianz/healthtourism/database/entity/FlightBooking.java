package com.allianz.healthtourism.database.entity;

import com.allianz.healthtourism.util.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;

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

    @OneToOne
    private Appointment appointment;
    @ManyToOne
    private Flight departureFlight;
    @ManyToOne
    private Flight returnFlight;
    @Column
    private boolean isPaid;
    @Column
    private boolean isConfirmed;
}



