package com.allianz.healthtourism.model.requestDTO;

import com.allianz.healthtourism.util.BaseRequestDTO;
import lombok.Data;

import java.util.UUID;

@Data
public class HotelBookingRequestDTO extends BaseRequestDTO {
    private UUID hotelUUID;
    private boolean isPaid;
}
