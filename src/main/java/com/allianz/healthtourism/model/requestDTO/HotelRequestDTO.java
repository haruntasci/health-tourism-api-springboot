package com.allianz.healthtourism.model.requestDTO;

import com.allianz.healthtourism.util.BaseRequestDTO;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class HotelRequestDTO extends BaseRequestDTO {
    private UUID cityUUID;
    private LocalDateTime checkIn;
    private LocalDateTime checkOut;
    private Integer totalRoomCount;
    private Integer emptyRoomCount;
}
