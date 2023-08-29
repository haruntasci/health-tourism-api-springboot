package com.allianz.healthtourism.model.requestDTO;

import com.allianz.healthtourism.database.entity.City;
import com.allianz.healthtourism.util.BaseRequestDTO;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class FlightRequestDTO extends BaseRequestDTO {
    private UUID departureCityUUID;
    private UUID arrivalCityUUID;
    private LocalDateTime departureTime;
    private LocalDateTime landingTime;
    private Integer totalSeatCount;
    private Integer emptySeatCount;
}
