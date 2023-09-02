package com.allianz.healthtourism.model.requestDTO;

import com.allianz.healthtourism.util.base.BaseRequestDTO;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class FlightRequestDTO extends BaseRequestDTO {
    private UUID departureCityUUID;
    private UUID arrivalCityUUID;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private Integer totalSeatCount;
    private Integer emptySeatCount;
}
