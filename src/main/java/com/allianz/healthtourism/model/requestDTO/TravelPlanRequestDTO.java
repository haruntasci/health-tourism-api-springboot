package com.allianz.healthtourism.model.requestDTO;

import com.allianz.healthtourism.database.entity.FlightBooking;
import com.allianz.healthtourism.database.entity.HotelBooking;
import com.allianz.healthtourism.database.entity.Patient;
import com.allianz.healthtourism.util.BaseRequestDTO;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.util.UUID;

@Data
public class TravelPlanRequestDTO extends BaseRequestDTO {
    private UUID patientUUID;
    private UUID flightBookingUUID;
    private UUID hotelBookingUUID;
}
