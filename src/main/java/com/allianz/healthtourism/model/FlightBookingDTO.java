package com.allianz.healthtourism.model;

import com.allianz.healthtourism.database.entity.Flight;
import com.allianz.healthtourism.util.BaseDTO;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class FlightBookingDTO extends BaseDTO {
    private FlightDTO departureFlight;
    private FlightDTO returnFlight;
    private boolean isPaid;
    private boolean isConfirmed;
}
