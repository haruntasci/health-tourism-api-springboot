package com.allianz.healthtourism.database.entity;

import com.allianz.healthtourism.util.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;

@Table
@Entity
@AttributeOverride(
        name = "uuid",
        column = @Column(
                name = "travel_plan_uuid"
        )
)
@Data
public class TravelPlan extends BaseEntity {

    @OneToOne
    private Patient patient;
    @OneToOne
    private FlightBooking flightBooking;
    @OneToOne
    private HotelBooking hotelBooking;
}
