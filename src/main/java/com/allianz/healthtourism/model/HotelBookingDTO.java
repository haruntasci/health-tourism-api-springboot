package com.allianz.healthtourism.model;

import com.allianz.healthtourism.database.entity.Hotel;
import com.allianz.healthtourism.util.BaseDTO;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class HotelBookingDTO extends BaseDTO {
    private HotelDTO hotel;
    private boolean isPaid;
    private boolean isConfirmed;
}
