package com.allianz.healthtourism.model;


import com.allianz.healthtourism.util.base.BaseDTO;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FlightDTO extends BaseDTO {
    private CityDTO departureCity;
    private CityDTO arrivalCity;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private Integer totalSeatCount;
    private Integer emptySeatCount;
}
