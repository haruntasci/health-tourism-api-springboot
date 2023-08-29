package com.allianz.healthtourism.model;

import com.allianz.healthtourism.database.entity.FlightBooking;
import com.allianz.healthtourism.database.entity.HotelBooking;
import com.allianz.healthtourism.database.entity.Patient;
import com.allianz.healthtourism.util.BaseDTO;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
public class TravelPlanDTO extends BaseDTO {
    private PatientDTO patient;
    private FlightBookingDTO flightBooking;
    private HotelBookingDTO hotelBooking;
}
