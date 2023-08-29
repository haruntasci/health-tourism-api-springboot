package com.allianz.healthtourism.model;


import com.allianz.healthtourism.util.BaseDTO;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FlightDTO extends BaseDTO {
    private CityDTO departureCity;
    private CityDTO arrivalCity;
    private LocalDateTime departureTime;
    private LocalDateTime landingTime;
    private Integer totalSeatCount;
    private Integer emptySeatCount;
}
