package com.allianz.healthtourism.database.entity;

import com.allianz.healthtourism.util.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;

@Table
@Entity
@AttributeOverride(
        name = "uuid",
        column = @Column(
                name = "hotel_booking_uuid"
        )
)
@Data
public class HotelBooking extends BaseEntity {

    @OneToOne
    private Appointment appointment;
    @ManyToOne
    private Hotel hotel;
    @Column
    private boolean isPaid;
    @Column
    private boolean isConfirmed;
}

