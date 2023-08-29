package com.allianz.healthtourism.model.requestDTO;

import com.allianz.healthtourism.util.BaseRequestDTO;
import lombok.Data;

import java.util.UUID;

@Data
public class FlightBookingRequestDTO extends BaseRequestDTO {
    private UUID departureFlightUUID;
    private UUID returnFlightUUID;
    private Boolean isPaid;
}
