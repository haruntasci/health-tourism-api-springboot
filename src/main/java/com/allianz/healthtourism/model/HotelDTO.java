package com.allianz.healthtourism.model;

import com.allianz.healthtourism.util.model.BaseDTO;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class HotelDTO extends BaseDTO {
    private CityDTO city;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
    private Integer totalRoomCount;
    private Integer emptyRoomCount;
}
