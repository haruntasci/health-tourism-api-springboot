package com.allianz.healthtourism.controller;

import com.allianz.healthtourism.database.entity.HotelBooking;
import com.allianz.healthtourism.database.repository.HotelBookingRepository;
import com.allianz.healthtourism.database.specification.HotelBookingSpecification;
import com.allianz.healthtourism.mapper.HotelBookingMapper;
import com.allianz.healthtourism.model.HotelBookingDTO;
import com.allianz.healthtourism.model.requestDTO.HotelBookingRequestDTO;
import com.allianz.healthtourism.service.HotelBookingService;
import com.allianz.healthtourism.util.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hotel-booking")
public class HotelBookingController extends BaseController<HotelBooking, HotelBookingDTO, HotelBookingRequestDTO,
        HotelBookingMapper, HotelBookingRepository, HotelBookingSpecification, HotelBookingService> {

    public HotelBookingController(HotelBookingService service) {
        super(service);
    }
}
