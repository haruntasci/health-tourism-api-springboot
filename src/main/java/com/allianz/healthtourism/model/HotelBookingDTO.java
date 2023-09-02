package com.allianz.healthtourism.model;

import com.allianz.healthtourism.util.base.BaseDTO;
import lombok.Data;

@Data
public class HotelBookingDTO extends BaseDTO {
    private HotelDTO hotel;
    private boolean isPaid;
    private boolean isConfirmed;
    private AppointmentDTO appointment;
}
