package com.allianz.healthtourism.model;

import com.allianz.healthtourism.util.model.BaseDTO;
import lombok.Data;

@Data
public class FlightBookingDTO extends BaseDTO {
    private FlightDTO departureFlight;
    private FlightDTO returnFlight;
    private boolean isPaid;
    private boolean isConfirmed;
    private AppointmentDTO appointment;
}
